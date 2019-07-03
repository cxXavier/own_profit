package com.xavier.fast.model.user.order;

import com.xavier.fast.annotation.validate.NotEmpty;
import com.xavier.fast.annotation.validate.NotNull;
import com.xavier.fast.annotation.validate.NotZero;
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
public class RopOrderDetailRequest extends RopRequest implements Serializable {


    private static final long serialVersionUID = -1429185127117698530L;

    @NotNull(message = "orderId不能为空")
    @NotZero(message = "orderId必须大于0")
    private Integer orderId;

    private String openId;

    private String unionId;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }
}
