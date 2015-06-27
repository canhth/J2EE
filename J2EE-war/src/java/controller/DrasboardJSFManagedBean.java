/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Royal
 */
@ManagedBean
@SessionScoped
public class DrasboardJSFManagedBean {

    /**
     * Creates a new instance of DrasboardJSFManagedBean
     */
    public DrasboardJSFManagedBean() {
    }
    
    public String addProduct()
    {
        return "admin/addproduct";
    }
    
}
