package com.xavier.fast.model.search;


import com.xavier.fast.model.base.RopRequest;

import java.io.Serializable;

/**
* @Description:    搜索用
* @Author:         Wang
* @CreateDate:     2019/6/26 17:12
* @UpdateUser:
* @UpdateDate:     2019/6/26 17:12
* @UpdateRemark:
* @Version:        1.0
*/
public class RopSearchRequest extends RopRequest implements Serializable {

    private static final long serialVersionUID = -7948664603086131448L;

    private Integer pageSize;

    private Integer currentPageNum;

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getCurrentPageNum() {
        return currentPageNum;
    }

    public void setCurrentPageNum(Integer currentPageNum) {
        this.currentPageNum = currentPageNum;
    }
}
