/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import dao.*;
import entity.*;
import java.io.Serializable;
import java.util.*;
import javax.ejb.EJB;

/**
 *
 * @author Royal
 */
@ManagedBean
@SessionScoped
public class InvoiceDetailJSFManagedBean implements Serializable{
    @EJB
    private InvoiceDetailFacade invoiceDetailFacade;

    public List<InvoiceDetail> listInvoidceDetail = new ArrayList<InvoiceDetail>();
    
    public InvoiceDetail invoiceDetail = new InvoiceDetail();

    public List<InvoiceDetail> getListInvoidceDetail() {
        return listInvoidceDetail;
    }

    public void setListInvoidceDetail(List<InvoiceDetail> listInvoidceDetail) {
        this.listInvoidceDetail = listInvoidceDetail;
    }

    public InvoiceDetail getInvoiceDetail() {
        return invoiceDetail;
    }

    public void setInvoiceDetail(InvoiceDetail invoiceDetail) {
        this.invoiceDetail = invoiceDetail;
    }
    
    
    public InvoiceDetailJSFManagedBean() {
    }
    
    public List<InvoiceDetail> getInvoiceByID(Invoice invoice)
    {
        return this.invoiceDetailFacade.findWithQuery("SELECT i FROM InvoiceDetail i WHERE i.invoiceID = '"+invoice.getInvoiceID()+"'");
    }

}
