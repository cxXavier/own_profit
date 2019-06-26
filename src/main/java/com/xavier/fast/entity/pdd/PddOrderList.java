package com.xavier.fast.entity.pdd;

import java.io.Serializable;
import java.util.List;

/**
 * 拼多多返回订单列表
 */
public class PddOrderList  implements Serializable {

    private Long totalCount;

    private List<PddOrderInfo> orderList;

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public List<PddOrderInfo> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<PddOrderInfo> orderList) {
        this.orderList = orderList;
    }
}
