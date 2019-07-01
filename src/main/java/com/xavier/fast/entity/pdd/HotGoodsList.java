package com.xavier.fast.entity.pdd;


import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.List;

/**
 * @author yirenjie
 * createDate:  2018/11/9
 */
public class HotGoodsList implements Serializable {

    @JSONField(name = "top_goods_list_get_response")
    private HotGoodsSearchResponse response;
    @JSONField(name = "error_response")
    private HotErrorResponseBean errorResponse;

    public HotGoodsSearchResponse getResponse() {
        return response;
    }

    public void setResponse(HotGoodsSearchResponse response) {
        this.response = response;
    }

    public HotErrorResponseBean getErrorResponse() {
        return errorResponse;
    }

    public void setErrorResponse(HotErrorResponseBean errorResponse) {
        this.errorResponse = errorResponse;
    }

    public static class HotGoodsSearchResponse {

        @JSONField(name = "total")
        private int totalCount;

        @JSONField(name = "list")
        private List<Good> goodsList;

        public int getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }

        public List<Good> getGoodsList() {
            return goodsList;
        }

        public void setGoodsList(List<Good> goodsList) {
            this.goodsList = goodsList;
        }
    }

    public static class HotErrorResponseBean {
        @JSONField(name = "error_code")
        private Integer errorCode;
        @JSONField(name = "error_msg")
        private String errorMsg;

        public Integer getErrorCode() {
            return errorCode;
        }

        public void setErrorCode(Integer errorCode) {
            this.errorCode = errorCode;
        }

        public String getErrorMsg() {
            return errorMsg;
        }

        public void setErrorMsg(String errorMsg) {
            this.errorMsg = errorMsg;
        }
    }
}
