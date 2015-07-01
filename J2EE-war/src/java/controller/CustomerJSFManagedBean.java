/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import dao.*;
import entity.*;
import java.io.Serializable;
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
public class CustomerJSFManagedBean implements Serializable{
    @EJB
    private CustomerFacade customerFacade;

    public Customer findCustomer = new Customer();

    public Customer getFindCustomer() {
        return findCustomer;
    }

    public void setFindCustomer(Customer findCustomer) {
        this.findCustomer = findCustomer;
    }
    
    
    public CustomerJSFManagedBean() {
    }
    public Customer findCustomerByID(Integer idCus)
    {           
        return this.customerFacade.find(idCus);
    }
    
    public String findCustomerName(int order)
    {           
        return this.customerFacade.find(order).getCustomerName();
    }
    public String findCustomerPhone(int order)
    {           
        return this.customerFacade.find(order).getCustomerPhone();
    }
    public String findCustomerEmail(int order)
    {           
        return this.customerFacade.find(order).getCustomerEmail();
    }
    public String findCustomerAddres(int order)
    {           
        return this.customerFacade.find(order).getCustomerAddres();
    }
    
}
