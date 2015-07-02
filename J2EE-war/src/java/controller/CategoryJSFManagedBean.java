/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import static controller.OrderJSFManagedBean.objectCustomerOrder;
import dao.*;
import entity.*;
import java.io.Serializable;
import java.util.*;
import javax.annotation.PostConstruct;
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
public class CategoryJSFManagedBean implements Serializable {

    @EJB
    private CategoryFacade categoryFacade;

    private String categoryName;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    private List<Category> listCategory = new ArrayList<Category>();

    public List<Category> getListCategory() {
        return listCategory;
    }

    public void setListCategory(List<Category> listCategory) {
        this.listCategory = listCategory;
    }

    public CategoryJSFManagedBean() {

    }

    public List<Category> getAllCategory() {
        return this.categoryFacade.findAll();
    }

    public String insertCategory() {
        Category newCategory = new Category();
        try {
            newCategory.setCategoryName(categoryName);
            this.categoryFacade.create(newCategory);

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Can not update Invoice (Cause this order not have a Invoice)", e.toString()));
            return "managercategory";
        }
        return "managercategory";
    }

    public boolean updateCategory(Category category) {

        return true;
    }

    public String deleteCategory(Category category) {
        this.categoryFacade.remove(category);
        return "managercategory";
    }

    @PostConstruct
    public void init() {
        this.listCategory = categoryFacade.findAll();
    }
}
