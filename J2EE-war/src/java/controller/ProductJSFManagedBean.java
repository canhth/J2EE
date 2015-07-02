/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import com.sun.xml.messaging.saaj.util.ByteInputStream;
import dao.*;
import entity.*;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.*;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import javax.faces.validator.ValidatorException;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.event.CloseEvent;

/**
 *
 * @author Royal
 */
@ManagedBean
@SessionScoped
public class ProductJSFManagedBean implements Serializable{
    @EJB
    private ProductFacade productFacade;
    private boolean isAddNewProductSucsses;
    private Product sp = new Product();
    private StreamedContent image;      

    
    public StreamedContent getImage() {
       FacesContext context = FacesContext.getCurrentInstance();

        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            // So, we're rendering the view. Return a stub StreamedContent so that it will generate right URL.
            return new DefaultStreamedContent();
        }
        else {
            // So, browser is requesting the image. Get ID value from actual request param.
            String id = context.getExternalContext().getRequestParameterMap().get("id");
            Product image = productFacade.find(Integer.valueOf(id));
            return new DefaultStreamedContent(new ByteArrayInputStream(image.getImageFile()));
        }
    }

    public void setImage(StreamedContent image) {
        this.image = image;
    }

    public boolean isIsAddNewProductSucsses() {
        return isAddNewProductSucsses;
    }

    public void setIsAddNewProductSucsses(boolean isAddNewProductSucsses) {
        this.isAddNewProductSucsses = isAddNewProductSucsses;
    }
    
    public List<Product> listProducs;

    public List<Product> getListProducs() {
        return listProducs;
    }
    
    public List<Product> convertProducts;
    
    public List<Product> productOrder = new ArrayList<Product>();  

    public List<Product> getProductOrder() {
        return productOrder;
    }
    
    public void setProductOrder(List<Product> productOrder) {
        this.productOrder = productOrder;
    }

    public Product getSp() {
        return sp;
    }

    public void setSp(Product sp) {
        this.sp = sp;
    }
      
    public ProductJSFManagedBean() {
        isAddNewProductSucsses = false;
    }
    
    public List<Product> danhsach()
    {
        this.listProducs = this.productFacade.findAll();
        return this.listProducs;
    }
    public void deleteProduct(Product sp)
    {
        this.productFacade.remove(sp);
    }
    
    public String addProduct()
    {         
        this.productFacade.create(this.sp);
        isAddNewProductSucsses = true;
        return "addproduct";
    }
    
    public String updateProduct(Product sp)
    {
        this.sp = sp;
        return "updateproduct?faces-redirect=true";
    }
    
    public String update()
    {
        this.productFacade.edit(this.sp);
        return "dashboard?faces-redirect=true";
    }  
    
    /* Add product to Order */
    public String createOderDetail(Product product) {
        try {
            if (LoginJSFManagedBean.customer != null) {
                this.sp = product;
                product.setProductQuantity(1);
                this.productOrder.add(product);
                return "index";
            } else {
                return "login?faces-redirect=true";
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Can not delete Customer ", e.toString()));
            return "index";
        }
    }
    
    public void clear(Product product)
    {
        this.sp = product;
        productOrder.remove(product);
    }
    
    public String insertProduct()
    {    
        this.convertProducts = productOrder;
        return "listorder?faces-redirect=true";
    }
    
    public String countCharge(int charge)
    {      
        double price = (double)charge;
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        return formatter.format(price) + " VNĐ";       
    }
    
    @PostConstruct
    public void init()
    {
        this.listProducs = productFacade.findAll();
        
    } 
     
    public String backIndex()
    {
        this.productOrder.clear();      
        return "index?faces-redirect=true";
    }
    
   
    
    public String findProductNameByID(int productID)
    {
        return this.productFacade.find(productID).getProductName();
    }
    
    public String findProductByCategory(int id)
    {
        this.listProducs = productFacade.findWithQuery("SELECT p FROM Product p WHERE p.productCategoryID = '"+id+"'");
        return "dashboard";
    }
    
    /*File Upload*/
    private UploadedFile file;
 
    public UploadedFile getFile() {
        return file;
    }
 
    public void setFile(UploadedFile file) {
        this.file = file;
    }
     
    public void upload() {
        if(file != null) {
            FacesMessage message = new FacesMessage("Succesful", file.getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
    public void destroyWorld() {
        addMessage("System Error", "Please try again later.");
    }
     
    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
