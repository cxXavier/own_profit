package com.xavier.fast.model.user.order;

import com.xavier.fast.entity.order.Order;

import java.io.Serializable;
import java.util.List;

/**
* @Description:    订单
* @Author:         Wang
* @CreateDate:     2019/7/3 10:28
* @UpdateUser:
* @UpdateDate:     2019/7/3 10:28
* @UpdateRemark:
* @Version:        1.0
*/
public class RopOrderListResponse implements Serializable {

    private static final long serialVersionUID = -1837868850195418329L;

    private List<Order> orderList;

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }
}
