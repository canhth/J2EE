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
@Table(name = "InvoiceDetail")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InvoiceDetail.findAll", query = "SELECT i FROM InvoiceDetail i"),
    @NamedQuery(name = "InvoiceDetail.findByInvoiceDetailID", query = "SELECT i FROM InvoiceDetail i WHERE i.invoiceDetailID = :invoiceDetailID"),
    @NamedQuery(name = "InvoiceDetail.findByInvoiceID", query = "SELECT i FROM InvoiceDetail i WHERE i.invoiceID = :invoiceID"),
    @NamedQuery(name = "InvoiceDetail.findByProductID", query = "SELECT i FROM InvoiceDetail i WHERE i.productID = :productID"),
    @NamedQuery(name = "InvoiceDetail.findByProductPrice", query = "SELECT i FROM InvoiceDetail i WHERE i.productPrice = :productPrice"),
    @NamedQuery(name = "InvoiceDetail.findByQuantity", query = "SELECT i FROM InvoiceDetail i WHERE i.quantity = :quantity")})
public class InvoiceDetail implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "InvoiceDetail_ID")
    private Integer invoiceDetailID;
    @Column(name = "Invoice_ID")
    private Integer invoiceID;
    @Column(name = "ProductID")
    private Integer productID;
    @Column(name = "ProductPrice")
    private Integer productPrice;
    @Column(name = "Quantity")
    private Integer quantity;

    public InvoiceDetail() {
    }

    public InvoiceDetail(Integer invoiceDetailID) {
        this.invoiceDetailID = invoiceDetailID;
    }

    public Integer getInvoiceDetailID() {
        return invoiceDetailID;
    }

    public void setInvoiceDetailID(Integer invoiceDetailID) {
        this.invoiceDetailID = invoiceDetailID;
    }

    public Integer getInvoiceID() {
        return invoiceID;
    }

    public void setInvoiceID(Integer invoiceID) {
        this.invoiceID = invoiceID;
    }

    public Integer getProductID() {
        return productID;
    }

    public void setProductID(Integer productID) {
        this.productID = productID;
    }

    public Integer getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Integer productPrice) {
        this.productPrice = productPrice;
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
        hash += (invoiceDetailID != null ? invoiceDetailID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InvoiceDetail)) {
            return false;
        }
        InvoiceDetail other = (InvoiceDetail) object;
        if ((this.invoiceDetailID == null && other.invoiceDetailID != null) || (this.invoiceDetailID != null && !this.invoiceDetailID.equals(other.invoiceDetailID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.InvoiceDetail[ invoiceDetailID=" + invoiceDetailID + " ]";
    }
    
}
