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
import javax.persistence.Lob;
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
@Table(name = "Product")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Product.findAll", query = "SELECT p FROM Product p"),
    @NamedQuery(name = "Product.findByProductID", query = "SELECT p FROM Product p WHERE p.productID = :productID"),
    @NamedQuery(name = "Product.findByProductName", query = "SELECT p FROM Product p WHERE p.productName = :productName"),
    @NamedQuery(name = "Product.findByProductPrice", query = "SELECT p FROM Product p WHERE p.productPrice = :productPrice"),
    @NamedQuery(name = "Product.findByProductQuantity", query = "SELECT p FROM Product p WHERE p.productQuantity = :productQuantity"),
    @NamedQuery(name = "Product.findByProductStatus", query = "SELECT p FROM Product p WHERE p.productStatus = :productStatus"),
    @NamedQuery(name = "Product.findByProductDescription", query = "SELECT p FROM Product p WHERE p.productDescription = :productDescription"),
    @NamedQuery(name = "Product.findByProductProductionDate", query = "SELECT p FROM Product p WHERE p.productProductionDate = :productProductionDate"),
    @NamedQuery(name = "Product.findByProductCategoryID", query = "SELECT p FROM Product p WHERE p.productCategoryID = :productCategoryID"),
    @NamedQuery(name = "Product.findByProductImage", query = "SELECT p FROM Product p WHERE p.productImage = :productImage")})
public class Product implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Product_ID")
    private Integer productID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "Product_Name")
    private String productName;
    @Column(name = "Product_Price")
    private Integer productPrice;
    @Column(name = "Product_Quantity")
    private Integer productQuantity;
    @Size(max = 255)
    @Column(name = "Product_Status")
    private String productStatus;
    @Size(max = 255)
    @Column(name = "Product_Description")
    private String productDescription;
    @Column(name = "Product_ProductionDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date productProductionDate;
    @Column(name = "Product_CategoryID")
    private Integer productCategoryID;
    @Size(max = 255)
    @Column(name = "Product_Image")
    private String productImage;
    @Lob
    @Column(name = "ImageFile")
    private byte[] imageFile;

    public Product() {
    }

    public Product(Integer productID) {
        this.productID = productID;
    }

    public Product(Integer productID, String productName) {
        this.productID = productID;
        this.productName = productName;
    }

    public Integer getProductID() {
        return productID;
    }

    public void setProductID(Integer productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Integer productPrice) {
        this.productPrice = productPrice;
    }

    public Integer getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(Integer productQuantity) {
        this.productQuantity = productQuantity;
    }

    public String getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(String productStatus) {
        this.productStatus = productStatus;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public Date getProductProductionDate() {
        return productProductionDate;
    }

    public void setProductProductionDate(Date productProductionDate) {
        this.productProductionDate = productProductionDate;
    }

    public Integer getProductCategoryID() {
        return productCategoryID;
    }

    public void setProductCategoryID(Integer productCategoryID) {
        this.productCategoryID = productCategoryID;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public byte[] getImageFile() {
        return imageFile;
    }

    public void setImageFile(byte[] imageFile) {
        this.imageFile = imageFile;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (productID != null ? productID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Product)) {
            return false;
        }
        Product other = (Product) object;
        if ((this.productID == null && other.productID != null) || (this.productID != null && !this.productID.equals(other.productID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Product[ productID=" + productID + " ]";
    }
    
}
