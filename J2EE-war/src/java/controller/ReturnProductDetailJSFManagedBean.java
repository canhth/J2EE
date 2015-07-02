/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import dao.*;
import entity.*;
import java.io.Serializable;
import java.util.*;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Royal
 */

@ManagedBean (name="returnProductDetail")
@RequestScoped
public class ReturnProductDetailJSFManagedBean implements Serializable{
    @EJB
    private ProductFacade productFacade;
    
    @EJB
    private ReturnProductDetailFacade returnProductDetailFacade;
    
    public static List<ReturnProductDetail> listDetail = new ArrayList<ReturnProductDetail>();
    
    public Integer idProduct;
    public String state;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
    
    public Integer getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Integer idProduct) {
        this.idProduct = idProduct;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    public Integer quantity;
    
    public ReturnProductDetail objectDetail = new ReturnProductDetail();

    public List<ReturnProductDetail> getListDetail() {
        return listDetail;
    }

    public void setListDetail(List<ReturnProductDetail> listDetail) {
        listDetail = listDetail;
    }

    public ReturnProductDetail getObjectDetail() {
        return objectDetail;
    }

    public void setObjectDetail(ReturnProductDetail objectDetail) {
        this.objectDetail = objectDetail;
    }
    
    
    public ReturnProductDetailJSFManagedBean() {
    }
    
    public void addProductToList()
    {
        ReturnProductDetail newDetail = new ReturnProductDetail();
        newDetail.setProductID(idProduct);
        newDetail.setQuantity(quantity);
        try {
            listDetail.add(newDetail);         
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Can not add product to List ", e.toString()));
        }
 
       // return "productreturn";
    }
    public String backIndex()
    {
        listDetail.clear();      
        return "index?faces-redirect=true";
    }
    
    public String updateListReturnProduct(ReturnProductDetail detail)
    {
        try {
            detail.setStatus(state);
            this.returnProductDetailFacade.edit(detail);
            if (detail.getStatus().equals("Success"))
            {
                Product product = new Product();
                product = this.productFacade.find(detail.getProductID());
                product.setProductStatus("Waste");
                product.setProductQuantity(detail.getQuantity());
                this.productFacade.create(product);
            }
            
            return "managedreturnproduct";
        }
        catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Can not add product to List ", e.toString()));
            return "managedreturnproduct";
        }
        
    }
    
    public String deleteReturnProductDetail(ReturnProductDetail object)
    {
        listDetail.remove(object);
        this.returnProductDetailFacade.remove(object);
        return "managedreturnproductdetail";
    }
    
    /* Managed Dashboard */
    
    public List<ReturnProductDetail> getAllDetail(ReturnProduct object)
    {
        this.listDetail = this.returnProductDetailFacade.findWithQuery("SELECT r FROM ReturnProductDetail r WHERE r.returnProductID = '"+object.getReturnProductID()+"'");
        return this.listDetail;
    }
    
    
}
