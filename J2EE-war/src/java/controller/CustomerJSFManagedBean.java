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
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Royal
 */
@ManagedBean
@SessionScoped
public class CustomerJSFManagedBean implements Serializable {

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

    public Customer getCus() {
        return cus;
    }

    public void setCus(Customer cus) {
        this.cus = cus;
    }

    public CustomerJSFManagedBean() {
        isAddnewuser = false;
    }

    public Customer findCustomerByID(Integer idCus) {
        return this.customerFacade.find(idCus);
    }

    public String findCustomerName(int order) {
        return this.customerFacade.find(order).getCustomerName();
    }

    public String findCustomerPhone(int order) {
        return this.customerFacade.find(order).getCustomerPhone();
    }

    public String findCustomerEmail(int order) {
        return this.customerFacade.find(order).getCustomerEmail();
    }

    public String findCustomerAddres(int order) {
        return this.customerFacade.find(order).getCustomerAddres();
    }

    public List<Customer> getallCustomer() {
        this.listcustomer = this.customerFacade.findAll();
        return this.listcustomer;
    }

    public void deleteCustomer(Customer cus) {
        try {
            this.customerFacade.remove(cus);
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Can not delete Customer ", e.toString()));
        }
    }

    public String updateCustomer(Customer cus) {
        this.cus = cus;
        return "updateuser";
    }

    public String update() {
        try {
            this.customerFacade.edit(this.cus);
            return "manageduser";
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Can not delete Customer ", e.toString()));
            return "manageduser";
        }
    }

    public String newuser() {
        this.cus.setCustomerAddres("");
        this.cus.setCustomerEmail("");
        this.cus.setCustomerName("");
        this.cus.setCustomerPhone("");
        this.cus.setCustomerType("");
        return "adduser";
    }

    public String adduser() {
        try {
            this.customerFacade.create(this.cus);
            setIsAddnewuser(true);
            return "adduser";
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Can not delete Customer ", e.toString()));
            return "adduser";
        }
    }

    public boolean isIsAddnewuser() {
        return isAddnewuser;
    }

  
    public void setIsAddnewuser(boolean isAddnewuser) {
        this.isAddnewuser = isAddnewuser;
    }

}
