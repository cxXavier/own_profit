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
public class RopOrderListRequest extends RopRequest implements Serializable {

    private static final long serialVersionUID = -6900294694782148472L;

    @NotEmpty(message = "opendId不能为空")
    private String openId;

    private String unionId;

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
