package com.xavier.fast.model.goods;

import com.xavier.fast.model.base.RopRequest;

import java.io.Serializable;
import java.util.List;

/**
 * @Description: 商品
 * @Author: Wang
 * @CreateDate: 2019/6/25 20:00
 * @UpdateUser:
 * @UpdateDate: 2019/6/25 20:00
 * @UpdateRemark:
 * @Version: 1.0
 */
public class RopGoodsDetailRequest extends RopRequest implements Serializable {

    private static final long serialVersionUID = -7881056399240504768L;

    private Long goodsId;

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }
}
