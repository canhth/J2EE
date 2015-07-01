/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Royal
 */
@Entity
@Table(name = "ReturnProductDetail")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReturnProductDetail.findAll", query = "SELECT r FROM ReturnProductDetail r"),
    @NamedQuery(name = "ReturnProductDetail.findByReturnProductDetailID", query = "SELECT r FROM ReturnProductDetail r WHERE r.returnProductDetailID = :returnProductDetailID"),
    @NamedQuery(name = "ReturnProductDetail.findByReturnProductID", query = "SELECT r FROM ReturnProductDetail r WHERE r.returnProductID = :returnProductID"),
    @NamedQuery(name = "ReturnProductDetail.findByProductID", query = "SELECT r FROM ReturnProductDetail r WHERE r.productID = :productID"),
    @NamedQuery(name = "ReturnProductDetail.findByQuantity", query = "SELECT r FROM ReturnProductDetail r WHERE r.quantity = :quantity")})
public class ReturnProductDetail implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ReturnProductDetail_ID")
    private Integer returnProductDetailID;
    @Column(name = "ReturnProduct_ID")
    private Integer returnProductID;
    @Column(name = "ProductID")
    private Integer productID;
    @Column(name = "Quantity")
    private Integer quantity;

    public ReturnProductDetail() {
    }

    public ReturnProductDetail(Integer returnProductDetailID) {
        this.returnProductDetailID = returnProductDetailID;
    }

    public Integer getReturnProductDetailID() {
        return returnProductDetailID;
    }

    public void setReturnProductDetailID(Integer returnProductDetailID) {
        this.returnProductDetailID = returnProductDetailID;
    }

    public Integer getReturnProductID() {
        return returnProductID;
    }

    public void setReturnProductID(Integer returnProductID) {
        this.returnProductID = returnProductID;
    }

    public Integer getProductID() {
        return productID;
    }

    public void setProductID(Integer productID) {
        this.productID = productID;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (returnProductDetailID != null ? returnProductDetailID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReturnProductDetail)) {
            return false;
        }
        ReturnProductDetail other = (ReturnProductDetail) object;
        if ((this.returnProductDetailID == null && other.returnProductDetailID != null) || (this.returnProductDetailID != null && !this.returnProductDetailID.equals(other.returnProductDetailID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.ReturnProductDetail[ returnProductDetailID=" + returnProductDetailID + " ]";
    }
    
}
