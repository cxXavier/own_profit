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
public class RopOrderDetailResponse implements Serializable {


    private static final long serialVersionUID = 2059506541021205922L;

    private Order order;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
