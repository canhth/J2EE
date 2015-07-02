package controller;

import static controller.OrderJSFManagedBean.objectCustomerOrder;
import dao.*;
import entity.*;
import java.io.Serializable;
import java.util.*;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "returnProduct")
@SessionScoped
public class ReturnProductJSFManagedBean implements Serializable {

    @EJB
    private ReturnProductDetailFacade returnProductDetailFacade;
    @EJB
    private ReturnProductFacade returnProductFacade;

    public ReturnProduct objectReturn = new ReturnProduct();
    public List<ReturnProduct> listProductReturn = new ArrayList<ReturnProduct>();
    public String state;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
    public List<ReturnProduct> getListProduct() {
        return listProductReturn;
    }

    public void setListProduct(List<ReturnProduct> listProduct) {
        this.listProductReturn = listProduct;
    }
    
    public ReturnProduct getObjectReturn() {
        return objectReturn;
    }

    public void setObjectReturn(ReturnProduct objectReturn) {
        this.objectReturn = objectReturn;
    }

    public ReturnProductJSFManagedBean() {
    }
    
    public List<ReturnProduct> getAllReturnProduct()
    {
        this.listProductReturn = this.returnProductFacade.findAll();
        return this.listProductReturn;
    }

    public String importProductReturn() {
        try {
            Calendar cal = Calendar.getInstance();
            this.objectReturn.setDayReturn(cal.getTime());
            this.objectReturn.setReturnStatus("Waiting");
            this.returnProductFacade.create(this.objectReturn);
             
            for (ReturnProductDetail detail : ReturnProductDetailJSFManagedBean.listDetail) {
                detail.setReturnProductID(this.objectReturn.getReturnProductID());
                detail.setStatus("Waiting");
                this.returnProductDetailFacade.create(detail);
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Can not update Invoice (Cause this order not have a Invoice)", e.toString()));
            return "index?faces-redirect=true";
        }
        return "returnproductsuccess?faces-redirect=true";
    }

    
    
    public String check(ReturnProduct object) 
    {
        this.objectReturn = object;
        return "managedreturnproductdetail";
    }
    
    public String updateReturnProduct(ReturnProduct object)
    {
        try {
            this.returnProductFacade.edit(object);   
            return "managedreturnproduct";
        } catch (RuntimeException e) {
            throw new FacesException(e.getMessage(), e);
        }      
    }
    
    public String updateReturnProduct()
    {
        return "managedreturnproduct";    
    }
    
    public String selectByStatus(int id) {
        String status = "";
        switch (id) {
            case 1:
                status = "Waiting";
                break;
            case 2:
                status = "Success";
                break;
            case 3:
                status = "Cancel";
                break;
        }
        try {
            this.listProductReturn = returnProductFacade.findWithQuery("SELECT r FROM ReturnProduct r WHERE r.returnStatus = '" + status + "'");
            return "managedreturnproduct";
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Can not update Invoice (Cause this order not have a Invoice)", e.toString()));
            return "managedreturnproduct";
        }
    }
    @PostConstruct
    public void init() {
        this.listProductReturn = this.returnProductFacade.findAll();
    }
}
