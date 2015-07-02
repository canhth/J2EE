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
import java.util.*;
import javax.ejb.EJB;


@ManagedBean (name="discount")
@SessionScoped
public class DiscountJSFManagedBean {
    @EJB
    private DiscountFacade discountFacade;

    public Discount discount = new Discount();
    public List<Discount> listDiscount = new ArrayList<Discount>();

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    public List<Discount> getListDiscount() {
        return listDiscount;
    }

    public void setListDiscount(List<Discount> listDiscount) {
        this.listDiscount = listDiscount;
    }
    
    public DiscountJSFManagedBean() {
    }
    
    public List<Discount> getAllDiscount()
    {
        return this.discountFacade.findAll();
    }
    public String addDiscount()
    {
        this.discountFacade.create(this.discount);
        return "manageddiscount";
    }
    public String removeDiscount()
    {
        this.discountFacade.remove(this.discount);
        return "manageddiscount";
    }
    
}
