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
    /*
     public String importOrder(List<Product> productList) {
     try {
     DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
     Calendar cal = Calendar.getInstance();
     CustomerOrder newCustomerOrder = new CustomerOrder();
     newCustomerOrder.setCustomerID(LoginJSFManagedBean.customer.getCustomerID());
     newCustomerOrder.setCustomerOrderDate(dateFormat.format(cal.getTime()));
     newCustomerOrder.setCustomerOrderName("Mua Hang");
     newCustomerOrder.setCustomerOrderState("Cho Duyet Don Hang");
     newCustomerOrder.setCustomerOrderPaymentID(this.paymentID);
     this.customerOrderFacade.create(newCustomerOrder);

     for (Product product : productList) {
     CustomerOrderDetail orderDetails = new CustomerOrderDetail();
     orderDetails.setCustomerOrderID(newCustomerOrder.getCustomerOrderID());
     orderDetails.setProductID(product.getProductID());
     orderDetails.setQuantity(product.getProductQuantity());
     orderDetails.setPrice(product.getProductPrice());
     customerOrderDetailFacade.create(orderDetails);

     Product productOld = new Product();
     productOld = this.productFacade.find(product.getProductID());
     productOld.setProductQuantity(productOld.getProductQuantity() - product.getProductQuantity());
     this.productFacade.edit(productOld);
     }

     // Send Email
     //SendEmail.sendEmail(LoginJSFManagedBean.customer.getCustomerEmail());
     return "checkout";

     } catch (RuntimeException e) {
     throw new FacesException(e.getMessage(), e);
     }
     }


   
     public String checkOutOrder(CustomerOrder order) {
     OrderJSFManagedBean.objectCustomerOrder = order;
     return "updateorder?redirect=true";
     }

     public String updateOrder() {
     try {
     this.customerOrderFacade.edit(objectCustomerOrder);
     String status = objectCustomerOrder.getCustomerOrderState();
     Invoice invoice = new Invoice();
     InvoiceDetail invoiceDetail = new InvoiceDetail();
     List<CustomerOrderDetail> listOrderDetail = this.customerOrderDetailFacade.findWithQuery("SELECT c FROM CustomerOrderDetail c WHERE c.customerOrderID = '" + objectCustomerOrder.getCustomerOrderID() + "'");
     if (status.equals("Dang Giao Hang")) {
     Calendar cal = Calendar.getInstance();
     invoice.setCustomerID(objectCustomerOrder.getCustomerID());
     invoice.setInvoiceDate(cal.getTime());
     invoice.setInvoiceName("Hoa Don Ban Hang");
     invoice.setInvoicePaymentID(objectCustomerOrder.getCustomerOrderPaymentID());
     invoice.setInvoiceState("Dang Cho Thanh Toan");
     Integer charge = 0;
     for (CustomerOrderDetail productOrder : listOrderDetail) {
     charge += productOrder.getPrice() * productOrder.getQuantity();
     }
     invoice.setInvoiceCost(charge);
     invoice.setOrderID(objectCustomerOrder.getCustomerOrderID());

     this.invoiceFacade.create(invoice);
     for (CustomerOrderDetail orderDetail : listOrderDetail) {
     invoiceDetail.setInvoiceID(invoice.getInvoiceID());
     invoiceDetail.setProductID(orderDetail.getProductID());
     invoiceDetail.setProductPrice(orderDetail.getPrice());
     invoiceDetail.setQuantity(orderDetail.getQuantity());
     this.invoiceDetailFacade.create(invoiceDetail);
     }

     } else if (status.equals("Huy Bo")) {
     try {
     for (CustomerOrderDetail orderDetail : listOrderDetail) {
     this.customerOrderDetailFacade.remove(orderDetail);
     }
     this.customerOrderFacade.remove(objectCustomerOrder);

     } catch (Exception e) {
     FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Can not update Invoice (Cause this order not have a Invoice)", e.toString()));
     return "managedOrder?faces-redirect=true";
     }
     }

     this.isUpdateOrder = true;
     return "managedOrder?faces-redirect=true";

     } catch (RuntimeException e) {
     throw new FacesException(e.getMessage(), e);
     }

     }

     */

    public String updateInvoice()
    {
        this.invoiceFacade.edit(objectInvoice);
        return "managedinvoice";
    }
    
    public String deleteInvoice()
    {
        this.invoiceFacade.remove(objectInvoice);
        return "managedinvoice";
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
