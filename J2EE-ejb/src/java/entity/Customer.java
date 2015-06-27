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
@Table(name = "Customer")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Customer.findAll", query = "SELECT c FROM Customer c"),
    @NamedQuery(name = "Customer.findByCustomerID", query = "SELECT c FROM Customer c WHERE c.customerID = :customerID"),
    @NamedQuery(name = "Customer.findByCustomerType", query = "SELECT c FROM Customer c WHERE c.customerType = :customerType"),
    @NamedQuery(name = "Customer.findByCustomerName", query = "SELECT c FROM Customer c WHERE c.customerName = :customerName"),
    @NamedQuery(name = "Customer.findByCustomerPhone", query = "SELECT c FROM Customer c WHERE c.customerPhone = :customerPhone"),
    @NamedQuery(name = "Customer.findByCustomerAddres", query = "SELECT c FROM Customer c WHERE c.customerAddres = :customerAddres"),
    @NamedQuery(name = "Customer.findByCustomerEmail", query = "SELECT c FROM Customer c WHERE c.customerEmail = :customerEmail"),
    @NamedQuery(name = "Customer.findByCustomerPasswords", query = "SELECT c FROM Customer c WHERE c.customerPasswords = :customerPasswords")})
public class Customer implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Customer_ID")
    private Integer customerID;
    @Size(max = 15)
    @Column(name = "Customer_Type")
    private String customerType;
    @Size(max = 50)
    @Column(name = "Customer_Name")
    private String customerName;
    @Size(max = 12)
    @Column(name = "Customer_Phone")
    private String customerPhone;
    @Size(max = 100)
    @Column(name = "Customer_Addres")
    private String customerAddres;
    @Size(max = 50)
    @Column(name = "Customer_Email")
    private String customerEmail;
    @Size(max = 50)
    @Column(name = "Customer_Passwords")
    private String customerPasswords;

    public Customer() {
    }

    public Customer(Integer customerID) {
        this.customerID = customerID;
    }

    public Integer getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Integer customerID) {
        this.customerID = customerID;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCustomerAddres() {
        return customerAddres;
    }

    public void setCustomerAddres(String customerAddres) {
        this.customerAddres = customerAddres;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerPasswords() {
        return customerPasswords;
    }

    public void setCustomerPasswords(String customerPasswords) {
        this.customerPasswords = customerPasswords;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (customerID != null ? customerID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Customer)) {
            return false;
        }
        Customer other = (Customer) object;
        if ((this.customerID == null && other.customerID != null) || (this.customerID != null && !this.customerID.equals(other.customerID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Customer[ customerID=" + customerID + " ]";
    }
    
}
