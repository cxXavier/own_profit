package com.xavier.fast.model.user.cash;

import com.xavier.fast.annotation.validate.NotEmpty;
import com.xavier.fast.annotation.validate.NotNull;
import com.xavier.fast.annotation.validate.NotZero;
import com.xavier.fast.model.base.RopRequest;

/**
* @Description:    用户提现
* @Author:         Wang
* @CreateDate:     2019/7/3 22:15
* @UpdateUser:
* @UpdateDate:     2019/7/3 22:15
* @UpdateRemark:
* @Version:        1.0
*/
public class RopReturnCashRequest extends RopRequest {
    private static final long serialVersionUID = 6179852035775858000L;

    @NotEmpty(message = "openId不能为空")
    private String openId;

    @NotNull(message = "订单号不能为空")
    @NotZero(message = "订单必须大于0")
    private Integer orderId;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
}
