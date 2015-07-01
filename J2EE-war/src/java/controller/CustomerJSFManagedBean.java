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
    
    private Customer cus = new Customer();
    private boolean isAddnewuser;

    public Customer findCustomer = new Customer();
    
    public List<Customer> listcustomer;

    public Customer getFindCustomer() {
        return findCustomer;
    }

    public void setFindCustomer(Customer findCustomer) {
        this.findCustomer = findCustomer;
    }
    
    /**
     * @return the cus
     */
    public Customer getCus() {
        return cus;
    }

    /**
     * @param cus the cus to set
     */
    public void setCus(Customer cus) {
        this.cus = cus;
    }
    
    public CustomerJSFManagedBean() {
        isAddnewuser =false;
    }
    public Customer findCustomerByID(Integer idCus)
    {           
        return this.customerFacade.find(idCus);
    }
    
    public String findCustomerName(CustomerOrder order)
    {           
        return this.customerFacade.find(order.getCustomerID()).getCustomerName();
    }
    public String findCustomerPhone(CustomerOrder order)
    {           
        return this.customerFacade.find(order.getCustomerID()).getCustomerPhone();
    }
    public String findCustomerEmail(CustomerOrder order)
    {           
        return this.customerFacade.find(order.getCustomerID()).getCustomerEmail();
    }
    public String findCustomerAddres(CustomerOrder order)
    {           
        return this.customerFacade.find(order.getCustomerID()).getCustomerAddres();
    }
    public List<Customer> getallCustomer()
    {
        this.listcustomer=this.customerFacade.findAll();
        return this.listcustomer;
    }
    public void deleteCustomer(Customer cus)
    {
        this.customerFacade.remove(cus);
    }
    public String updateCustomer(Customer cus)
    {
        this.cus=cus;
        return "updateuser";
    }
    public String update()
    {
        this.customerFacade.edit(this.cus);
        return "manageduser";
    }
    public String newuser()
    {
        this.cus.setCustomerAddres("");
        this.cus.setCustomerEmail("");
        this.cus.setCustomerName("");
        this.cus.setCustomerPhone("");
        this.cus.setCustomerType("");
        return "adduser";
    }
    public String adduser()
    {
        this.customerFacade.create(this.cus);
        setIsAddnewuser(true);
        return "adduser";
    }

    /**
     * @return the isAddnewuser
     */
    public boolean isIsAddnewuser() {
        return isAddnewuser;
    }

    /**
     * @param isAddnewuser the isAddnewuser to set
     */
    public void setIsAddnewuser(boolean isAddnewuser) {
        this.isAddnewuser = isAddnewuser;
    }

   
}
