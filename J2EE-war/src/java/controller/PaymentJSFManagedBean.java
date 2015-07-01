/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import dao.*;
import entity.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
/**
 *
 * @author Royal
 */
@ManagedBean
@RequestScoped
public class PaymentJSFManagedBean implements Serializable{
    @EJB
    private PaymentFacade paymentFacade;
    
    
    public PaymentJSFManagedBean() {
    }
    
     public Payment findPaymentByID(Integer idPayment)
    {           
        return this.paymentFacade.find(idPayment);
    }
    
    public String findPaymentName(Integer order)
    {           
        return this.paymentFacade.find(order).getPaymentName();
    }
}
