package com.xavier.fast.entity.pdd;


import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.List;

/**
 * @author yirenjie
 * createDate:  2018/11/9
 */
public class GoodsList implements Serializable {

    @JSONField(name = "goods_search_response")
    private GoodsSearchResponse response = new GoodsSearchResponse();
    @JSONField(name = "error_response")
    private ErrorResponseBean errorResponse;

    public GoodsSearchResponse getResponse() {
        return response;
    }

    public void setResponse(GoodsSearchResponse response) {
        this.response = response;
    }

    public ErrorResponseBean getErrorResponse() {
        return errorResponse;
    }

    public void setErrorResponse(ErrorResponseBean errorResponse) {
        this.errorResponse = errorResponse;
    }

    public static class GoodsSearchResponse {

        @JSONField(name = "total_count")
        private int totalCount;

        @JSONField(name = "goods_list")
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

    public static class ErrorResponseBean {
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
