/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import static controller.OrderJSFManagedBean.objectCustomerOrder;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import dao.*;
import entity.*;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Royal
 */
@ManagedBean
@SessionScoped
public class InvoiceJSFManagedBean implements Serializable {

    @EJB
    private InvoiceFacade invoiceFacade;

    public List<Invoice> listInvoice = new ArrayList<Invoice>();

    public static Invoice objectInvoice = new Invoice();
   
   
    public Invoice getObjectInvoice() {
        return objectInvoice;
    }

    public void setObjectInvoice(Invoice objectInvoice) {
        this.objectInvoice = objectInvoice;
    }

    public List<Invoice> getListInvoice() {
        return listInvoice;
    }

    public void setListInvoice(List<Invoice> listInvoice) {
        this.listInvoice = listInvoice;
    }

    public List<Invoice> getAll() {
        this.listInvoice = this.invoiceFacade.findAll();
        return this.listInvoice;
    }

    public InvoiceJSFManagedBean() {
  
    }
  
    public String updateInvoice() {
        try {
            this.invoiceFacade.edit(objectInvoice);
            return "managedinvoice";
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Can not delete Customer ", e.toString()));
            return "managedinvoice";
        }
    }
    
    public String deleteInvoice()
    {
        try {
            this.invoiceFacade.remove(objectInvoice);
            return "managedinvoice";
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Can not delete Customer ", e.toString()));
            return "managedinvoice";
        }
        
    }
    
    public String selectInvoiceByStatus(int id) {
        String status = "";
        switch (id) {
            case 1:
                status = "Invoice not paid";
                break;
            case 2:
                status = "Invoice already paid";
                break;
        }
        try {
            this.listInvoice = invoiceFacade.findWithQuery("SELECT i FROM Invoice i WHERE i.invoiceState = '" + status + "'");
            return "managedinvoice";
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Can not update Invoice (Cause this order not have a Invoice)", e.toString()));
            return "managedinvoice";
        }
    }

    public String checkInvoice(Invoice invoice) {
        objectInvoice = invoice;
        return "updateinvoice?redirect=true";
    }

    @PostConstruct
    public void init() {
        this.listInvoice = invoiceFacade.findAll();
    }
}
