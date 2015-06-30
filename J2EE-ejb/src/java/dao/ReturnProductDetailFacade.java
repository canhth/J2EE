/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import entity.ReturnProductDetail;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Royal
 */
@Stateless
public class ReturnProductDetailFacade extends AbstractFacade<ReturnProductDetail> {
    @PersistenceContext(unitName = "J2EE-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ReturnProductDetailFacade() {
        super(ReturnProductDetail.class);
    }
    
}
