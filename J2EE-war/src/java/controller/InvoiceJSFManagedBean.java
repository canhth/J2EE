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
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
/**
 *
 * @author Royal
 */
@ManagedBean
@SessionScoped
public class InvoiceJSFManagedBean implements Serializable{
    @EJB
    private InvoiceFacade invoiceFacade;

    public List<Invoice> listInvoice = new ArrayList<Invoice>();
    
    public Invoice objectInvoice = new Invoice();
    
    public List<Invoice> getAll()
    {
        return this.invoiceFacade.findAll();
    }
    
    public InvoiceJSFManagedBean() {
    }
    
    @PostConstruct
    public void init()
    {
        this.listInvoice = invoiceFacade.findAll();
    } 
}
