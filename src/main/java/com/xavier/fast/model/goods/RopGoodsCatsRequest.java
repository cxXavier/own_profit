package com.xavier.fast.model.goods;

import com.xavier.fast.model.base.RopRequest;

import java.io.Serializable;

/**
* @Description:    商品类目
* @Author:         Wang
* @CreateDate:     2019/6/28 14:56
* @UpdateUser:
* @UpdateDate:     2019/6/28 14:56
* @UpdateRemark:
* @Version:        1.0
*/
public class RopGoodsCatsRequest extends RopRequest implements Serializable {


    private static final long serialVersionUID = 567586978839507831L;

    /**
     * 值=0时为顶点cat_id,通过树顶级节点获取cat树
     */
    private Long parentCatId;

    public Long getParentCatId() {
        return parentCatId;
    }

    public void setParentCatId(Long parentCatId) {
        this.parentCatId = parentCatId;
    }
}
