package com.xavier.fast.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 分页查询基类
 * @author yirenjie
 * createDate:  2018/11/12
 */
public class BasePageQueryRo implements Serializable {
    private Integer pageSize = 10;
    private Integer pageNum = 1;

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
}
