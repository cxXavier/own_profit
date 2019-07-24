package com.xavier.fast.model.goods;

import com.xavier.fast.model.base.RopRequest;

import java.io.Serializable;
import java.util.List;

/**
 * @Description: 商品
 * @Author: Wang
 * @CreateDate: 2019/6/25 20:00
 * @UpdateUser:
 * @UpdateDate: 2019/6/25 20:00
 * @UpdateRemark:
 * @Version: 1.0
 */
public class RopGoodsListRequest extends RopRequest implements Serializable {

    private static final long serialVersionUID = -7881056399240504768L;

    private Integer tagId; // 标签id
    /**
     * 排序方式:
     * 0-综合排序;
     * 1-按佣金比率升序;
     * 2-按佣金比例降序;
     * 3-按价格升序;
     * 4-按价格降序;
     * 5-按销量升序;
     * 6-按销量降序;
     * 7-优惠券金额排序升序;
     * 8-优惠券金额排序降序;
     * 9-券后价升序排序;
     * 10-券后价降序排序;
     * 11-按照加入多多进宝时间升序;
     * 12-按照加入多多进宝时间降序;
     * 13-按佣金金额升序排序;
     * 14-按佣金金额降序排序;
     * 15-店铺描述评分升序;
     * 16-店铺描述评分降序;
     * 17-店铺物流评分升序;
     * 18-店铺物流评分降序;
     * 19-店铺服务评分升序;
     * 20-店铺服务评分降序;
     * 27-描述评分击败同类店铺百分比升序，
     * 28-描述评分击败同类店铺百分比降序，
     * 29-物流评分击败同类店铺百分比升序，
     * 30-物流评分击败同类店铺百分比降序，
     * 31-服务评分击败同类店铺百分比升序，
     * 32-服务评分击败同类店铺百分比降序
     */
    private Integer sortType = 0;
    private String keyword; // 查询关键字
    private List<String> goodsIdList; // 商品ID列表 当入参带有goods_id_list字段，将不会以opt_id、 cat_id、keyword维度筛选商品

    private String openid;

    private Integer pageSize = 10;
    private Integer pageNum = 1;

    private Integer tagLevel;

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    public Integer getSortType() {
        return sortType;
    }

    public void setSortType(Integer sortType) {
        this.sortType = sortType;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public List<String> getGoodsIdList() {
        return goodsIdList;
    }

    public void setGoodsIdList(List<String> goodsIdList) {
        this.goodsIdList = goodsIdList;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

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

    public Integer getTagLevel() {
        return tagLevel;
    }

    public void setTagLevel(Integer tagLevel) {
        this.tagLevel = tagLevel;
    }
}
