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
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import utils.SendEmail;
/**
 *
 * @author Royal
 */
@ManagedBean
@RequestScoped
public class OrderJSFManagedBean implements Serializable{
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
        this.objectCustomerOrder = objectCustomerOrder;
    }

    
    public OrderJSFManagedBean() {
    }
     
    public String importOrder(List<Product> productList)
    {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");     
        Calendar cal = Calendar.getInstance();       
        CustomerOrder newCustomerOrder = new CustomerOrder();
        newCustomerOrder.setCustomerID(LoginJSFManagedBean.customer.getCustomerID());
        newCustomerOrder.setCustomerOrderDate(dateFormat.format(cal.getTime()));
        newCustomerOrder.setCustomerOrderName("Mua Hang");
        newCustomerOrder.setCustomerOrderState("Cho Duyet Don Hang");
        newCustomerOrder.setCustomerOrderPaymentID(this.paymentID);
        this.customerOrderFacade.create(newCustomerOrder);
                 
        for (Product product : productList)
        {
            CustomerOrderDetail orderDetails = new CustomerOrderDetail();
            orderDetails.setCustomerOrderID(newCustomerOrder.getCustomerOrderID());
            orderDetails.setProductID(product.getProductID());
            orderDetails.setQuantity(1);
            orderDetails.setPrice(product.getProductPrice());
            customerOrderDetailFacade.create(orderDetails);
        }
        
        // Send Email
        SendEmail.sendEmail(LoginJSFManagedBean.customer.getCustomerEmail());
        return "checkout";
    }


    /*      Managed Dashboard */
    public List<CustomerOrder> findAllOrder() {

        return this.customerOrderFacade.findAll();
    }

    public String checkOutOrder(CustomerOrder order) {
        OrderJSFManagedBean.objectCustomerOrder = order;
        return "updateorder?redirect=true";
    }

    public String updateOrder()
    {  
        this.customerOrderFacade.edit(this.objectCustomerOrder);
        this.isUpdateOrder = true;
        return "managedOrder?faces-redirect=true";
    }
    
    @PostConstruct
    public void init() {
        this.customerOrder = customerOrderFacade.findAll();
    }
}
