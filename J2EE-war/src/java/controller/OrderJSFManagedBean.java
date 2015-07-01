/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

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
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import utils.SendEmail;

/**
 *
 * @author Royal
 */
@ManagedBean
@RequestScoped
public class OrderJSFManagedBean implements Serializable {

    @EJB
    private InvoiceDetailFacade invoiceDetailFacade;
    @EJB
    private InvoiceFacade invoiceFacade;
    @EJB
    private ProductFacade productFacade;
    @EJB
    private CustomerOrderFacade customerOrderFacade;
    @EJB
    private CustomerOrderDetailFacade customerOrderDetailFacade;

    public List<CustomerOrder> customerOrder = new ArrayList<CustomerOrder>();

    private Integer paymentID;

    private boolean isUpdateOrder;

    public boolean isIsUpdateOrder() {
        return isUpdateOrder;
    }

    public void setIsUpdateOrder(boolean isUpdateOrder) {
        this.isUpdateOrder = isUpdateOrder;
    }

    public Integer getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(Integer paymentID) {
        this.paymentID = paymentID;
    }

    public List<CustomerOrder> getCustomerOrder() {
        return customerOrder;
    }

    public void setCustomerOrder(List<CustomerOrder> customerOrder) {
        this.customerOrder = customerOrder;
    }

    public static CustomerOrder objectCustomerOrder = new CustomerOrder();

    public CustomerOrder getObjectCustomerOrder() {
        return objectCustomerOrder;
    }

    public void setObjectCustomerOrder(CustomerOrder objectCustomerOrder) {
        objectCustomerOrder = objectCustomerOrder;
    }

    public OrderJSFManagedBean() {
    }

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


    /*      Managed Dashboard */
    public List<CustomerOrder> findAllOrder() {

        return this.customerOrderFacade.findAll();
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
                invoice.setInvoiceName("Invoice of Sale");
                invoice.setInvoicePaymentID(objectCustomerOrder.getCustomerOrderPaymentID());
                invoice.setInvoiceState("Invoice not paid");
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

    /*  Chon Loai Don Hang */
    public String selectOrderByStatus(int id) {

        String status = "Cho Duyet Don Hang";
        switch (id) {
            case 1:
                status = "Cho Duyet Don Hang";
                break;
            case 2:
                status = "Dang Giao Hang";
                break;
            case 3:
                status = "Hoan Tat";
                break;
            case 4:
                status = "Huy Don Hang";
                break;
        }
        try {
            this.customerOrder = customerOrderFacade.findWithQuery("SELECT c FROM CustomerOrder c WHERE c.customerOrderState = '" + status + "'");
            return "managedOrder";

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Can not update Invoice (Cause this order not have a Invoice)", e.toString()));
            return "managedOrder";
        }

    }

    @PostConstruct
    public void init() {
        this.customerOrder = customerOrderFacade.findAll();
    }
}
