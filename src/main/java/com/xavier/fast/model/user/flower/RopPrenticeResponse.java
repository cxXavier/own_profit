package com.xavier.fast.model.user.flower;

import com.xavier.fast.entity.user.Prentice;

import java.io.Serializable;
import java.util.List;

/**
* @Description:    徒弟
* @Author:         Wang
* @CreateDate:     2019/7/2 19:37
* @UpdateUser:
* @UpdateDate:     2019/7/2 19:37
* @UpdateRemark:
* @Version:        1.0
*/
public class RopPrenticeResponse implements Serializable {
    private static final long serialVersionUID = -152484453209932985L;

    /**
     * 我的鲜花数
     */
    private Integer totalFlowers;

    /**
     * 徒弟列表
     */
    private List<Prentice> dataList;

    public List<Prentice> getDataList() {
        return dataList;
    }

    public void setDataList(List<Prentice> dataList) {
        this.dataList = dataList;
    }

    public Integer getTotalFlowers() {
        return totalFlowers;
    }

    public void setTotalFlowers(Integer totalFlowers) {
        this.totalFlowers = totalFlowers;
    }
}
