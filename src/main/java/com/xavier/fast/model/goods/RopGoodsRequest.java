package com.xavier.fast.model.goods;

import com.xavier.fast.annotation.validate.NotEmpty;
import com.xavier.fast.annotation.validate.NotNull;
import com.xavier.fast.annotation.validate.NotZero;
import com.xavier.fast.model.base.RopRequest;

import java.io.Serializable;

/**
* @Description:    商品
* @Author:         Wang
* @CreateDate:     2019/6/25 20:00
* @UpdateUser:
* @UpdateDate:     2019/6/25 20:00
* @UpdateRemark:
* @Version:        1.0
*/
public class RopGoodsRequest extends RopRequest implements Serializable {

    private static final long serialVersionUID = -7881056399240504768L;
    /**
     * 商品ID
     */
    @NotNull(message = "商品ID不能为空")
    @NotZero(message = "商品ID必须大于0")
    private Long goodsId;

    /**
     * 商品类型
     */
    @NotEmpty(message = "商品类型不能为空")
    private String goodsType;

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }
}
