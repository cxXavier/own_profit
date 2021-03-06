package com.xavier.fast.model.goods;

import com.xavier.fast.entity.tag.Tag;

import java.io.Serializable;
import java.util.List;

/**
* @Description:    商品类目
* @Author:         Wang
* @CreateDate:     2019/6/28 15:09
* @UpdateUser:
* @UpdateDate:     2019/6/28 15:09
* @UpdateRemark:
* @Version:        1.0
*/
public class RopGoodsCatsResponse implements Serializable {
    private static final long serialVersionUID = 5272456292849890667L;

    /**
     * 商品类目列表
     */
    private List<Tag> catsList;

    public List<Tag> getCatsList() {
        return catsList;
    }

    public void setCatsList(List<Tag> catsList) {
        this.catsList = catsList;
    }
}
