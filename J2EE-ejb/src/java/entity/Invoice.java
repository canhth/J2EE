/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Royal
 */
@Entity
@Table(name = "Invoice")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Invoice.findAll", query = "SELECT i FROM Invoice i"),
    @NamedQuery(name = "Invoice.findByInvoiceID", query = "SELECT i FROM Invoice i WHERE i.invoiceID = :invoiceID"),
    @NamedQuery(name = "Invoice.findByCustomerID", query = "SELECT i FROM Invoice i WHERE i.customerID = :customerID"),
    @NamedQuery(name = "Invoice.findByInvoiceName", query = "SELECT i FROM Invoice i WHERE i.invoiceName = :invoiceName"),
    @NamedQuery(name = "Invoice.findByInvoiceDate", query = "SELECT i FROM Invoice i WHERE i.invoiceDate = :invoiceDate"),
    @NamedQuery(name = "Invoice.findByInvoiceState", query = "SELECT i FROM Invoice i WHERE i.invoiceState = :invoiceState"),
    @NamedQuery(name = "Invoice.findByInvoicePaymentID", query = "SELECT i FROM Invoice i WHERE i.invoicePaymentID = :invoicePaymentID"),
    @NamedQuery(name = "Invoice.findByInvoiceCost", query = "SELECT i FROM Invoice i WHERE i.invoiceCost = :invoiceCost"),
    @NamedQuery(name = "Invoice.findByOrderID", query = "SELECT i FROM Invoice i WHERE i.orderID = :orderID")})
public class Invoice implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Invoice_ID")
    private Integer invoiceID;
    @Column(name = "CustomerID")
    private Integer customerID;
    @Size(max = 250)
    @Column(name = "InvoiceName")
    private String invoiceName;
    @Column(name = "InvoiceDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date invoiceDate;
    @Size(max = 250)
    @Column(name = "InvoiceState")
    private String invoiceState;
    @Column(name = "InvoicePaymentID")
    private Integer invoicePaymentID;
    @Column(name = "InvoiceCost")
    private Integer invoiceCost;
    @Column(name = "OrderID")
    private Integer orderID;

    public Invoice() {
    }

    public Invoice(Integer invoiceID) {
        this.invoiceID = invoiceID;
    }

    public Integer getInvoiceID() {
        return invoiceID;
    }

    public void setInvoiceID(Integer invoiceID) {
        this.invoiceID = invoiceID;
    }

    public Integer getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Integer customerID) {
        this.customerID = customerID;
    }

    public String getInvoiceName() {
        return invoiceName;
    }

    public void setInvoiceName(String invoiceName) {
        this.invoiceName = invoiceName;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getInvoiceState() {
        return invoiceState;
    }

    public void setInvoiceState(String invoiceState) {
        this.invoiceState = invoiceState;
    }

    public Integer getInvoicePaymentID() {
        return invoicePaymentID;
    }

    public void setInvoicePaymentID(Integer invoicePaymentID) {
        this.invoicePaymentID = invoicePaymentID;
    }

    public Integer getInvoiceCost() {
        return invoiceCost;
    }

    public void setInvoiceCost(Integer invoiceCost) {
        this.invoiceCost = invoiceCost;
    }

    public Integer getOrderID() {
        return orderID;
    }

    public void setOrderID(Integer orderID) {
        this.orderID = orderID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (invoiceID != null ? invoiceID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Invoice)) {
            return false;
        }
        Invoice other = (Invoice) object;
        if ((this.invoiceID == null && other.invoiceID != null) || (this.invoiceID != null && !this.invoiceID.equals(other.invoiceID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Invoice[ invoiceID=" + invoiceID + " ]";
    }
    
}
