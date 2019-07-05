package com.xavier.fast.model.base;

import com.xavier.fast.entity.img.Image;

import java.io.Serializable;
import java.util.List;

/**
* @Description:    通用集合返回对象
* @Author:         Wang
* @CreateDate:     2019/7/4 19:53
* @UpdateUser:
* @UpdateDate:     2019/7/4 19:53
* @UpdateRemark:
* @Version:        1.0
*/
public class RopResponseBody implements Serializable {

    private static final long serialVersionUID = 5608274717821395869L;

    private List<?> dataList;

    public List<?> getDataList() {
        return dataList;
    }

    public void setDataList(List<?> dataList) {
        this.dataList = dataList;
    }
}
