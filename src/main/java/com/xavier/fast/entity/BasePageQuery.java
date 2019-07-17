package com.xavier.fast.entity;

import java.io.Serializable;

/**
 * 分页查询基类
 */
public class BasePageQuery implements Serializable {

    private static final long serialVersionUID = 4005185907885872395L;

    private Integer startRow;

    private Integer endRow;

    public Integer getStartRow() {
        return startRow;
    }

    public void setStartRow(Integer startRow) {
        this.startRow = startRow;
    }

    public Integer getEndRow() {
        return endRow;
    }

    public void setEndRow(Integer endRow) {
        this.endRow = endRow;
    }
}
