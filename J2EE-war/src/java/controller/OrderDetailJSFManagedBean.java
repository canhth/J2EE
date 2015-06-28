

package controller;

import dao.*;
import entity.*;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.*;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
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

    public List<CustomerOrderDetail> getListOrderDetail() {
        return listOrderDetail;
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
        return formatter.format(charge);
        /*float epsilon = 0.004f; // 4 tenths of a cent
        if (Math.abs(Math.round(charge) - charge) < epsilon) {
            return String.format("%10.0f", charge); // sdb
        } else {
            return String.format("%10.2f", charge); // dj_segfault
        }*/
    }
    
}
