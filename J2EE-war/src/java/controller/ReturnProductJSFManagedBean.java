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
import javax.ejb.EJB;


@ManagedBean
@RequestScoped
public class ReturnProductJSFManagedBean implements Serializable{
    @EJB
    private ReturnProductFacade returnProductFacade;

    /**
     * Creates a new instance of ReturnProductJSFManagedBean
     */
    
    private int InvoiceId;
    private String CustomerName;
    private String CustomerAddress;
    private String CustomerMail;
    public ReturnProductJSFManagedBean() {
    }

    /**
     * @return the InvoiceId
     */
    public int getInvoiceId() {
        return InvoiceId;
    }

    /**
     * @param InvoiceId the InvoiceId to set
     */
    public void setInvoiceId(int InvoiceId) {
        this.InvoiceId = InvoiceId;
    }

    /**
     * @return the CustomerName
     */
    public String getCustomerName() {
        return CustomerName;
    }

    /**
     * @param CustomerName the CustomerName to set
     */
    public void setCustomerName(String CustomerName) {
        this.CustomerName = CustomerName;
    }

    /**
     * @return the CustomerAddress
     */
    public String getCustomerAddress() {
        return CustomerAddress;
    }

    /**
     * @param CustomerAddress the CustomerAddress to set
     */
    public void setCustomerAddress(String CustomerAddress) {
        this.CustomerAddress = CustomerAddress;
    }

    /**
     * @return the CustomerMail
     */
    public String getCustomerMail() {
        return CustomerMail;
    }

    /**
     * @param CustomerMail the CustomerMail to set
     */
    public void setCustomerMail(String CustomerMail) {
        this.CustomerMail = CustomerMail;
    }
    public String ImportProductReturn()
    {
        ReturnProduct newReturnproduct = new ReturnProduct();
        newReturnproduct.setInvoiceID(this.InvoiceId);
        newReturnproduct.setCustomerName(this.CustomerName);
        newReturnproduct.setCustomerAddress(this.CustomerAddress);
        newReturnproduct.setCustomerEmail(this.CustomerMail);
        this.returnProductFacade.create(newReturnproduct);
        return "true";
    }
    public String check()
    {
        return "managedreturnproductdetail";
    }
    
}
