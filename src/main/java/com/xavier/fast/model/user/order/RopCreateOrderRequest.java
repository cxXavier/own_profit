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
public class RopCreateOrderRequest extends RopRequest implements Serializable {


    private static final long serialVersionUID = 6839716994375808738L;

    @NotEmpty(message = "opendId不能为空")
    private String openId;

    @NotEmpty(message = "goodsId不能为空")
    private String goodsId;

    /**
     * 券后价
     */
    @NotNull(message = "价格不能为空")
    @NotZero(message = "价格必须大于0")
    private Long priceAfterCoupon;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public Long getPriceAfterCoupon() {
        return priceAfterCoupon;
    }

    public void setPriceAfterCoupon(Long priceAfterCoupon) {
        this.priceAfterCoupon = priceAfterCoupon;
    }

    @Override
    public String toString() {
        return "RopCreateOrderRequest{" +
                "openId='" + openId + '\'' +
                ", goodsId='" + goodsId + '\'' +
                ", priceAfterCoupon=" + priceAfterCoupon +
                '}';
    }
}
