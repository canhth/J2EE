

package controller;

import dao.*;
import entity.*;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.*;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
/**
 *
 * @author Royal
 */
@ManagedBean
@SessionScoped
public class OrderDetailJSFManagedBean implements Serializable{
    
    @EJB
    private CustomerOrderDetailFacade customerOrderDetailFacade;

    private List<CustomerOrderDetail> listOrderDetail = new ArrayList<CustomerOrderDetail>();

    public Integer quantity = 1;

    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }  
    public List<CustomerOrderDetail> getListOrderDetail() {
        return listOrderDetail;
    }

    public static Double cost;

    public static Double getCost() {
        return cost;
    }

    public static void setCost(Double cost) {
        OrderDetailJSFManagedBean.cost = cost;
    }
    
    
    public void setListOrderDetail(List<CustomerOrderDetail> listOrderDetail) {
        this.listOrderDetail = listOrderDetail;
    }
    
    public OrderDetailJSFManagedBean() {
        
    }
    
    public List<CustomerOrderDetail> getAllOrderDetail()
    {
        CustomerOrder order = new CustomerOrder();
        order = OrderJSFManagedBean.objectCustomerOrder;
        this.listOrderDetail = this.customerOrderDetailFacade.findWithQuery("SELECT c FROM CustomerOrderDetail c WHERE c.customerOrderID = '"+order.getCustomerOrderID()+"'");
        return this.listOrderDetail;
    }
    
    public String countCharge(List<CustomerOrderDetail> orderDetailList)
    {
        double charge = 0;
        for (CustomerOrderDetail productOrder : orderDetailList)
        {
            charge += productOrder.getPrice() * productOrder.getQuantity();
        }
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        cost = charge;
        return formatter.format(charge);      
    }
    
    public String updateColumnOrderDetail(CustomerOrderDetail orderDetail)
    {   
        orderDetail.setQuantity(this.quantity);
        this.customerOrderDetailFacade.edit(orderDetail);
        return "managedOrder";
    }
    public String deleteOrderDetail(CustomerOrderDetail orderDetail)
    {
        this.customerOrderDetailFacade.remove(orderDetail);
        return "managedOrder";
    }
    
}
