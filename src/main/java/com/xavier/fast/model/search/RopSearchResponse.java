package com.xavier.fast.model.search;

import com.xavier.fast.model.goods.RopGoodsResponse;

import java.io.Serializable;
import java.util.List;

/**
* @Description:    搜索
* @Author:         Wang
* @CreateDate:     2019/6/26 17:14
* @UpdateUser:
* @UpdateDate:     2019/6/26 17:14
* @UpdateRemark:
* @Version:        1.0
*/
public class RopSearchResponse implements Serializable {

    private static final long serialVersionUID = -1276780246603085085L;

    /**
     * 商品列表
     */
    private List<RopGoodsResponse> goodsList;

    private Integer pageSize;

    private Integer currentPageNum;

    private Integer totalPageNum;

    private Boolean hasNextPage;

    public List<RopGoodsResponse> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<RopGoodsResponse> goodsList) {
        this.goodsList = goodsList;
    }

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

    public Integer getTotalPageNum() {
        return totalPageNum;
    }

    public void setTotalPageNum(Integer totalPageNum) {
        this.totalPageNum = totalPageNum;
    }

    public Boolean getHasNextPage() {
        return hasNextPage;
    }

    public void setHasNextPage(Boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }
}
