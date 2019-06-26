package com.xavier.fast.entity.pdd;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.List;

/**
 * @author yirenjie
 * createDate:  2018/11/9
 */
public class GoodDetailList implements Serializable {

    @JSONField(name = "goods_detail_response")
    private GoodsDetailResponseBean response;

    public GoodsDetailResponseBean getResponse() {
        return response;
    }

    public void setResponse(GoodsDetailResponseBean response) {
        this.response = response;
    }

    public static class GoodsDetailResponseBean {

        @JSONField(name = "goods_details")
        private List<Good> goodsDetails;

        public List<Good> getGoodsDetails() {
            return goodsDetails;
        }

        public void setGoodsDetails(List<Good> goodsDetails) {
            this.goodsDetails = goodsDetails;
        }
    }
}
