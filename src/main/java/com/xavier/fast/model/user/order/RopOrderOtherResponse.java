package com.xavier.fast.model.user.order;

import com.xavier.fast.annotation.validate.NotEmpty;
import com.xavier.fast.model.base.RopRequest;

import java.io.Serializable;

/**
* @Description:    订单
* @Author:         Wang
* @CreateDate:     2019/7/3 10:27
* @UpdateUser:
* @UpdateDate:     2019/7/3 10:27
* @UpdateRemark:
* @Version:        1.0
*/
public class RopOrderOtherResponse implements Serializable {


    private static final long serialVersionUID = -6169407216885988266L;

    private Integer cashAmount;

    private Integer flowers;

    public Integer getCashAmount() {
        return cashAmount;
    }

    public void setCashAmount(Integer cashAmount) {
        this.cashAmount = cashAmount;
    }

    public Integer getFlowers() {
        return flowers;
    }

    public void setFlowers(Integer flowers) {
        this.flowers = flowers;
    }
}
