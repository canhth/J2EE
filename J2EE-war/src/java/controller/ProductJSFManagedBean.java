/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import com.oracle.jrockit.jfr.Producer;
import dao.*;
import entity.*;
import java.io.IOException;
import java.util.*;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.Part;
import java.io.Serializable;
import java.text.DecimalFormat;
/**
 *
 * @author Royal
 */
@ManagedBean
@SessionScoped
public class ProductJSFManagedBean implements Serializable{
    @EJB
    private CategoryFacade categoryFacade;
    @EJB
    private ProductFacade productFacade;
    private boolean isAddNewProductSucsses;
    private Product sp = new Product();

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
    
    public List<Category> listCategory;
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
        return this.productFacade.findAll();
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
    
    public String createOderDetail(Product product)
    {
        if(LoginJSFManagedBean.customer != null)
        {
           this.sp = product;
           this.productOrder.add(product);
           return "index";
        }
        else 
            return "login?faces-redirect=true";
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
        listCategory = categoryFacade.findAll();
        listProducs = productFacade.findAll();
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
    
}
