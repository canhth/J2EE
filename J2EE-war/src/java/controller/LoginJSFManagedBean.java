/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.CustomerFacade;
import dao.ManagerFacade;
import entity.Customer;
import entity.Manager;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.FacesException;
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
public class LoginJSFManagedBean implements Serializable {

    @EJB
    private CustomerFacade customerFacade;

    @EJB
    private ManagerFacade managerFacade;

    public static Customer customer = new Customer();
    public Manager manager = new Manager();

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public String username;
    public String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LoginJSFManagedBean() {
        this.manager = null;
        this.customer = null;
    }

    public String checkLoginManager() {
        List<Manager> listMng = this.managerFacade.findAll();
        boolean isManager = false;
        try {
            for (Manager mgn : listMng) {
                if (mgn.getUserName().equals(this.username) && mgn.getPassWords().equals(this.password)) {
                    this.manager = mgn;
                    isManager = true;
                    return "admin/dashboard?faces-redirect=true";
                }
            }

            if (!isManager) {
                List<Customer> listCtm = this.customerFacade.findAll();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Incorrect Username and Passowrd", "Please enter correct username and Password"));
                for (Customer ctm : listCtm) {
                    if (ctm.getCustomerEmail().equals(this.username) && ctm.getCustomerPasswords().equals(this.password)) {
                        this.customer = ctm;
                        return "index?faces-redirect=true";
                    } else {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Incorrect Username and Passowrd", "Please enter correct username and Password"));
                        return "login?faces-redirect=true";
                    }
                }
            }
            return null;
        } catch (RuntimeException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Can't login", "Please enter correct username and Password"));
            throw new FacesException(e.getMessage(), e);
        }

    }

}
