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
 * @author blue
 */
@Entity
@Table(name = "ReturnProduct")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReturnProduct.findAll", query = "SELECT r FROM ReturnProduct r"),
    @NamedQuery(name = "ReturnProduct.findByReturnProductID", query = "SELECT r FROM ReturnProduct r WHERE r.returnProductID = :returnProductID"),
    @NamedQuery(name = "ReturnProduct.findByInvoiceID", query = "SELECT r FROM ReturnProduct r WHERE r.invoiceID = :invoiceID"),
    @NamedQuery(name = "ReturnProduct.findByCustomerName", query = "SELECT r FROM ReturnProduct r WHERE r.customerName = :customerName"),
    @NamedQuery(name = "ReturnProduct.findByCustomerEmail", query = "SELECT r FROM ReturnProduct r WHERE r.customerEmail = :customerEmail"),
    @NamedQuery(name = "ReturnProduct.findByCustomerAddress", query = "SELECT r FROM ReturnProduct r WHERE r.customerAddress = :customerAddress"),
    @NamedQuery(name = "ReturnProduct.findByDayReturn", query = "SELECT r FROM ReturnProduct r WHERE r.dayReturn = :dayReturn"),
    @NamedQuery(name = "ReturnProduct.findByReturnReason", query = "SELECT r FROM ReturnProduct r WHERE r.returnReason = :returnReason"),
    @NamedQuery(name = "ReturnProduct.findByReturnStatus", query = "SELECT r FROM ReturnProduct r WHERE r.returnStatus = :returnStatus")})
public class ReturnProduct implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ReturnProduct_ID")
    private Integer returnProductID;
    @Column(name = "InvoiceID")
    private Integer invoiceID;
    @Size(max = 250)
    @Column(name = "CustomerName")
    private String customerName;
    @Size(max = 250)
    @Column(name = "CustomerEmail")
    private String customerEmail;
    @Size(max = 250)
    @Column(name = "CustomerAddress")
    private String customerAddress;
    @Column(name = "DayReturn")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dayReturn;
    @Size(max = 250)
    @Column(name = "ReturnReason")
    private String returnReason;
    @Size(max = 250)
    @Column(name = "ReturnStatus")
    private String returnStatus;

    public ReturnProduct() {
    }

    public ReturnProduct(Integer returnProductID) {
        this.returnProductID = returnProductID;
    }

    public Integer getReturnProductID() {
        return returnProductID;
    }

    public void setReturnProductID(Integer returnProductID) {
        this.returnProductID = returnProductID;
    }

    public Integer getInvoiceID() {
        return invoiceID;
    }

    public void setInvoiceID(Integer invoiceID) {
        this.invoiceID = invoiceID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public Date getDayReturn() {
        return dayReturn;
    }

    public void setDayReturn(Date dayReturn) {
        this.dayReturn = dayReturn;
    }

    public String getReturnReason() {
        return returnReason;
    }

    public void setReturnReason(String returnReason) {
        this.returnReason = returnReason;
    }

    public String getReturnStatus() {
        return returnStatus;
    }

    public void setReturnStatus(String returnStatus) {
        this.returnStatus = returnStatus;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (returnProductID != null ? returnProductID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReturnProduct)) {
            return false;
        }
        ReturnProduct other = (ReturnProduct) object;
        if ((this.returnProductID == null && other.returnProductID != null) || (this.returnProductID != null && !this.returnProductID.equals(other.returnProductID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.ReturnProduct[ returnProductID=" + returnProductID + " ]";
    }
    
}
