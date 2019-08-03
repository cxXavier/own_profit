package com.xavier.fast.model.goods;

import com.xavier.fast.annotation.validate.NotEmpty;
import com.xavier.fast.model.base.RopRequest;

import java.io.Serializable;

/**
* @Description:    推荐商品
* @Author:         Wang
* @CreateDate:     2019/6/28 14:56
* @UpdateUser:
* @UpdateDate:     2019/6/28 14:56
* @UpdateRemark:
* @Version:        1.0
*/
public class RopRecommendGoodsRequest extends RopRequest implements Serializable {


    private static final long serialVersionUID = -7108033615237826783L;

    /**
     * 推荐商品ids
     */
    @NotEmpty(message = "goodsIds不能为空")
    private String goodsIds;

    public String getGoodsIds() {
        return goodsIds;
    }

    public void setGoodsIds(String goodsIds) {
        this.goodsIds = goodsIds;
    }
}
