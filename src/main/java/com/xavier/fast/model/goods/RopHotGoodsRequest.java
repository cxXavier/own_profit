package com.xavier.fast.model.goods;

import com.xavier.fast.annotation.validate.NotEmpty;
import com.xavier.fast.annotation.validate.NotNull;
import com.xavier.fast.annotation.validate.NotZero;
import com.xavier.fast.model.base.RopRequest;

import java.io.Serializable;

/**
* @Description:    热门商品
* @Author:         Wang
* @CreateDate:     2019/6/25 20:00
* @UpdateUser:
* @UpdateDate:     2019/6/25 20:00
* @UpdateRemark:
* @Version:        1.0
*/
public class RopHotGoodsRequest extends RopRequest implements Serializable {

    private static final long serialVersionUID = -8744976691265791640L;

    //1-实时热销榜；2-实时收益榜
    private Integer type;

    private Integer pageSize = 10;
    private Integer pageNum = 1;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public static enum HOT_GOODS_TYPE{
        HOT_SALE(1, "实时热销榜"),
        HIGH_INCOME(2, "实时收益榜");

        private int num;

        private String cnName;

        HOT_GOODS_TYPE(int num, String cnName){
            this.num = num;
            this.cnName = cnName;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public String getCnName() {
            return cnName;
        }

        public void setCnName(String cnName) {
            this.cnName = cnName;
        }
    }
}
