package com.xavier.fast.model.user.flower;

import com.xavier.fast.entity.user.UserFlower;

import java.io.Serializable;
import java.util.List;

/**
* @Description:    鲜花
* @Author:         Wang
* @CreateDate:     2019/7/3 10:13
* @UpdateUser:
* @UpdateDate:     2019/7/3 10:13
* @UpdateRemark:
* @Version:        1.0
*/
public class RopFlowerResponse implements Serializable {
    private static final long serialVersionUID = -152484453209932985L;

    private List<UserFlower> dataList;

    public List<UserFlower> getDataList() {
        return dataList;
    }

    public void setDataList(List<UserFlower> dataList) {
        this.dataList = dataList;
    }
}
