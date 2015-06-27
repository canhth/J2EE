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
@Table(name = "CustomerOrderDetail")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CustomerOrderDetail.findAll", query = "SELECT c FROM CustomerOrderDetail c"),
    @NamedQuery(name = "CustomerOrderDetail.findByDetailID", query = "SELECT c FROM CustomerOrderDetail c WHERE c.detailID = :detailID"),
    @NamedQuery(name = "CustomerOrderDetail.findByCustomerOrderID", query = "SELECT c FROM CustomerOrderDetail c WHERE c.customerOrderID = :customerOrderID"),
    @NamedQuery(name = "CustomerOrderDetail.findByProductID", query = "SELECT c FROM CustomerOrderDetail c WHERE c.productID = :productID"),
    @NamedQuery(name = "CustomerOrderDetail.findByPrice", query = "SELECT c FROM CustomerOrderDetail c WHERE c.price = :price"),
    @NamedQuery(name = "CustomerOrderDetail.findByQuantity", query = "SELECT c FROM CustomerOrderDetail c WHERE c.quantity = :quantity")})
public class CustomerOrderDetail implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Detail_ID")
    private Integer detailID;
    @Column(name = "CustomerOrder_ID")
    private Integer customerOrderID;
    @Column(name = "ProductID")
    private Integer productID;
    @Column(name = "Price")
    private Integer price;
    @Column(name = "Quantity")
    private Integer quantity;

    public CustomerOrderDetail() {
    }

    public CustomerOrderDetail(Integer detailID) {
        this.detailID = detailID;
    }

    public Integer getDetailID() {
        return detailID;
    }

    public void setDetailID(Integer detailID) {
        this.detailID = detailID;
    }

    public Integer getCustomerOrderID() {
        return customerOrderID;
    }

    public void setCustomerOrderID(Integer customerOrderID) {
        this.customerOrderID = customerOrderID;
    }

    public Integer getProductID() {
        return productID;
    }

    public void setProductID(Integer productID) {
        this.productID = productID;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
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
        hash += (detailID != null ? detailID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CustomerOrderDetail)) {
            return false;
        }
        CustomerOrderDetail other = (CustomerOrderDetail) object;
        if ((this.detailID == null && other.detailID != null) || (this.detailID != null && !this.detailID.equals(other.detailID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.CustomerOrderDetail[ detailID=" + detailID + " ]";
    }
    
}
