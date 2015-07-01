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
public class CategoryJSFManagedBean implements Serializable{
    
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
    
    public List<Category> getAllCategory()
    {
        return this.categoryFacade.findAll();
    }
  
    public String insertCategory( )
    {
        Category newCategory = new Category();
        newCategory.setCategoryName(categoryName);
        this.categoryFacade.create(newCategory);
      /*  else 
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Can not insert new Category.", "Please enter correct Category Name."));*/
        return "managercategory";
    }
 
    public boolean updateCategory( Category category)
    {
    
        return true;
    }
       
    public String deleteCategory(Category category)
    {       
        this.categoryFacade.remove(category);       
        return "managercategory";
    }
    
    @PostConstruct
    public void init()
    {
        this.listCategory = categoryFacade.findAll();
    } 
}
