package com.xavier.fast.model.goods;

import com.xavier.fast.entity.goods.Goods;
import com.xavier.fast.entity.pdd.Good;

import java.io.Serializable;
import java.util.List;

/**
* @Description:    推荐商品
* @Author:         Wang
* @CreateDate:     2019/6/26 17:29
* @UpdateUser:
* @UpdateDate:     2019/6/26 17:29
* @UpdateRemark:
* @Version:        1.0
*/
public class RopRecommendGoodsResponse implements Serializable {

    private static final long serialVersionUID = 9165305840915353647L;

    private String notExistGoodsId;

    private List<Goods> updateFailedGoodsList;

    private List<Goods> updateSuccessGoodsList;

    public String getNotExistGoodsId() {
        return notExistGoodsId;
    }

    public void setNotExistGoodsId(String notExistGoodsId) {
        this.notExistGoodsId = notExistGoodsId;
    }

    public List<Goods> getUpdateFailedGoodsList() {
        return updateFailedGoodsList;
    }

    public void setUpdateFailedGoodsList(List<Goods> updateFailedGoodsList) {
        this.updateFailedGoodsList = updateFailedGoodsList;
    }

    public List<Goods> getUpdateSuccessGoodsList() {
        return updateSuccessGoodsList;
    }

    public void setUpdateSuccessGoodsList(List<Goods> updateSuccessGoodsList) {
        this.updateSuccessGoodsList = updateSuccessGoodsList;
    }
}
