/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import dao.*;
import entity.*;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.*;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultStreamedContent;

/**
 *
 * @author Royal
 */
@ManagedBean
@ViewScoped
public class InvoiceDetailJSFManagedBean implements Serializable{
    @EJB
    private InvoiceFacade invoiceFacade;
    @EJB
    private InvoiceDetailFacade invoiceDetailFacade;
    public List<InvoiceDetail> listInvoidceDetail = new ArrayList<InvoiceDetail>();  
    public InvoiceDetail invoiceDetail = new InvoiceDetail();  
    public Integer quantity = 1;

    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }  
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
    
    public List<InvoiceDetail> getAllInvoiceDetail()
    {
        Invoice invoice = new Invoice();
        invoice = InvoiceJSFManagedBean.objectInvoice;
        this.listInvoidceDetail = this.invoiceDetailFacade.findWithQuery("SELECT i FROM InvoiceDetail i WHERE i.invoiceID = '"+invoice.getInvoiceID()+"'");
        return this.listInvoidceDetail;
    }
    
    public String countCharge()
    {
        int id = InvoiceJSFManagedBean.objectInvoice.getInvoiceID();
        List<InvoiceDetail> invoiceDetailList = invoiceDetailFacade.findWithQuery("SELECT i FROM InvoiceDetail i WHERE i.invoiceID = '"+id+"'");
        double charge = 0;
        for (InvoiceDetail invoiceDetail : invoiceDetailList)
        {
            charge += invoiceDetail.getProductPrice()* invoiceDetail.getQuantity();
        }
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        return formatter.format(charge);      
    }
    public String countCharge(Invoice invoice) {
        List<InvoiceDetail> invoiceDetailList = invoiceDetailFacade.findWithQuery("SELECT i FROM InvoiceDetail i WHERE i.invoiceID = '" + invoice.getInvoiceID() + "'");
        int charge = 0;
        if (invoiceDetailList.size() > 0) {
            for (InvoiceDetail invoiceDetail : invoiceDetailList) {
                charge += invoiceDetail.getProductPrice() * invoiceDetail.getQuantity();
            }
        }
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        return formatter.format(charge);
    }
    public String converMoney(int charge)
    {      
        double price = (double)charge;
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        return formatter.format(price) + " VNĐ";       
    }
    
    public String deleteInvoiceDetail(InvoiceDetail invoiceDetail)
    {
        this.invoiceDetailFacade.remove(invoiceDetail);
        return "updateinvoice";
    }
    
    public String updateColumnInvoiceDetail(InvoiceDetail invoiceDetail)
    {
        invoiceDetail.setQuantity(this.quantity);
        this.invoiceDetailFacade.edit(invoiceDetail);
        
        Invoice invoice = new Invoice();
        invoice = this.invoiceFacade.find(invoiceDetail.getInvoiceID());
        int charge = 0;
        this.listInvoidceDetail = this.invoiceDetailFacade.findWithQuery("SELECT i FROM InvoiceDetail i WHERE i.invoiceID = '"+invoice.getInvoiceID()+"'");
        for (InvoiceDetail details : this.listInvoidceDetail)
        {
            charge += details.getProductPrice() * details.getQuantity();
        }
        invoice.setInvoiceCost(charge);
        this.invoiceFacade.edit(invoice);
        return "updateinvoice";
    }
    @PostConstruct
    public void init() {
        this.listInvoidceDetail = invoiceDetailFacade.findAll();
    }
    public void chooseCar() {
        RequestContext.getCurrentInstance().openDialog("testUpdate");
    }
     
    public void onCarChosen(SelectEvent event) {
        Product car = (Product) event.getObject();
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Car Selected", "Id:" + car.getProductID());
         
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
