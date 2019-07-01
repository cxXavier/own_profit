package com.xavier.fast.model.goods;

import com.xavier.fast.entity.pdd.Good;

import java.io.Serializable;
import java.util.List;

/**
* @Description:    商品
* @Author:         Wang
* @CreateDate:     2019/6/26 17:29
* @UpdateUser:
* @UpdateDate:     2019/6/26 17:29
* @UpdateRemark:
* @Version:        1.0
*/
public class RopGoodsDetailResponse implements Serializable {

    private static final long serialVersionUID = 2403548821287792717L;

    private Good good;

    public Good getGood() {
        return good;
    }

    public void setGood(Good good) {
        this.good = good;
    }
}
