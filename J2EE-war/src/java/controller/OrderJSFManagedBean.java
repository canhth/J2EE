/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import dao.*;
import entity.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
/**
 *
 * @author Royal
 */
@ManagedBean
@SessionScoped
public class OrderJSFManagedBean {
    @EJB
    private CustomerOrderFacade customerOrderFacade;
    @EJB
    private CustomerOrderDetailFacade customerOrderDetailFacade;
    
    public CustomerOrder customOder = new CustomerOrder();
   
    public int cusOrderID;
    public int cusOrderDetailID;
    
    public OrderJSFManagedBean() {
    }
     
    public String importOrder(List<Product> productList)
    {
        List<CustomerOrder> listOder = this.customerOrderFacade.findAll();
        if(listOder.size() > 0)
        {
             this.customOder = listOder.get(listOder.size() - 1); 
             this.cusOrderID = this.customOder.getCustomerOrderID() + 1;
        } 
        else {
            this.cusOrderID = 1;
        } 
                    
        
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");     
        Calendar cal = Calendar.getInstance();       
        CustomerOrder newCustomerOrder = new CustomerOrder();
        newCustomerOrder.setCustomerOrderID(this.cusOrderID);
        newCustomerOrder.setCustomerID(LoginJSFManagedBean.customer.getCustomerID());
        newCustomerOrder.setCustomerOrderDate(dateFormat.format(cal.getTime()));
        newCustomerOrder.setCustomerOrderName("Mua Hang");
        newCustomerOrder.setCustomerOrderState("Cho Duyet Don Hang");
        newCustomerOrder.setCustomerOrderPaymentID(1);
        this.customerOrderFacade.create(newCustomerOrder);
        
        List<CustomerOrderDetail> listOderDetails = this.customerOrderDetailFacade.findAll();
        if(listOderDetails.size() > 0)
        {            
             this.cusOrderDetailID = listOderDetails.get(listOder.size() - 1).getDetailID() + 1; 
        } 
        else {
            this.cusOrderDetailID = 1;
        }
        for (Product product : productList)
        {
            CustomerOrderDetail orderDetails = new CustomerOrderDetail();
            orderDetails.setDetailID(this.cusOrderDetailID);
            orderDetails.setCustomerOrderID(this.cusOrderID);
            orderDetails.setProductID(product.getProductID());
            orderDetails.setQuantity(product.getProductQuantity());
            orderDetails.setPrice(product.getProductPrice());
            customerOrderDetailFacade.create(orderDetails);
            this.cusOrderDetailID += 1;
        }
        this.customOder = null;
        return "checkout";
    }
    
    public double countCharge(List<Product> productList)
    {
        double charge = 0;
        for (Product product : productList)
        {
            charge += product.getProductPrice();
        }
        return charge;
    }
    
    
    
}
