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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Royal
 */
@Entity
@Table(name = "CustomerOrder")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CustomerOrder.findAll", query = "SELECT c FROM CustomerOrder c"),
    @NamedQuery(name = "CustomerOrder.findByCustomerOrderID", query = "SELECT c FROM CustomerOrder c WHERE c.customerOrderID = :customerOrderID"),
    @NamedQuery(name = "CustomerOrder.findByCustomerOrderName", query = "SELECT c FROM CustomerOrder c WHERE c.customerOrderName = :customerOrderName"),
    @NamedQuery(name = "CustomerOrder.findByCustomerOrderDate", query = "SELECT c FROM CustomerOrder c WHERE c.customerOrderDate = :customerOrderDate"),
    @NamedQuery(name = "CustomerOrder.findByCustomerOrderState", query = "SELECT c FROM CustomerOrder c WHERE c.customerOrderState = :customerOrderState"),
    @NamedQuery(name = "CustomerOrder.findByCustomerOrderPaymentID", query = "SELECT c FROM CustomerOrder c WHERE c.customerOrderPaymentID = :customerOrderPaymentID"),
    @NamedQuery(name = "CustomerOrder.findByCustomerID", query = "SELECT c FROM CustomerOrder c WHERE c.customerID = :customerID")})
public class CustomerOrder implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CustomerOrder_ID")
    private Integer customerOrderID;
    @Size(max = 50)
    @Column(name = "CustomerOrder_Name")
    private String customerOrderName;
    @Size(max = 50)
    @Column(name = "CustomerOrder_Date")
    private String customerOrderDate;
    @Size(max = 50)
    @Column(name = "CustomerOrder_State")
    private String customerOrderState;
    @Column(name = "CustomerOrder_PaymentID")
    private Integer customerOrderPaymentID;
    @Column(name = "CustomerID")
    private Integer customerID;

    public CustomerOrder() {
    }

    public CustomerOrder(Integer customerOrderID) {
        this.customerOrderID = customerOrderID;
    }

    public Integer getCustomerOrderID() {
        return customerOrderID;
    }

    public void setCustomerOrderID(Integer customerOrderID) {
        this.customerOrderID = customerOrderID;
    }

    public String getCustomerOrderName() {
        return customerOrderName;
    }

    public void setCustomerOrderName(String customerOrderName) {
        this.customerOrderName = customerOrderName;
    }

    public String getCustomerOrderDate() {
        return customerOrderDate;
    }

    public void setCustomerOrderDate(String customerOrderDate) {
        this.customerOrderDate = customerOrderDate;
    }

    public String getCustomerOrderState() {
        return customerOrderState;
    }

    public void setCustomerOrderState(String customerOrderState) {
        this.customerOrderState = customerOrderState;
    }

    public Integer getCustomerOrderPaymentID() {
        return customerOrderPaymentID;
    }

    public void setCustomerOrderPaymentID(Integer customerOrderPaymentID) {
        this.customerOrderPaymentID = customerOrderPaymentID;
    }

    public Integer getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Integer customerID) {
        this.customerID = customerID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (customerOrderID != null ? customerOrderID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CustomerOrder)) {
            return false;
        }
        CustomerOrder other = (CustomerOrder) object;
        if ((this.customerOrderID == null && other.customerOrderID != null) || (this.customerOrderID != null && !this.customerOrderID.equals(other.customerOrderID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.CustomerOrder[ customerOrderID=" + customerOrderID + " ]";
    }
    
}
