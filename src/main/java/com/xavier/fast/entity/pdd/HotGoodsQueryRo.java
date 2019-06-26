package com.xavier.fast.entity.pdd;

import com.xavier.fast.entity.BasePageQueryRo;

public class HotGoodsQueryRo  extends BasePageQueryRo {

    //1-实时热销榜；2-实时收益榜
    private Integer type;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
