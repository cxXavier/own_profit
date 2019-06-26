package com.xavier.fast.entity.pdd;


import java.util.Date;
import java.util.List;

/**
 * 订单相关Dto
 * @author bcc
 */
public class OrderQueryRo extends CommonDto {

    private Long id;

    /**
     * 订单类型
     * 所有：all
     * 无效：invalid
     * 待返现:cashbacking
     * 已返现:cashbacked
     */
    private String orderType;

    /**
     * 订单类型  titile
     *
     * 自赚：purchase ;
     * 分享：share ;
     * 团队： team
     */
    private String orderTypeTitle;

    /**
     * 分享ID
     */
    private Long shareId;

    /**
     * 分享者openid
     */
    private String shareOpenid;

    /**
     * 订单状态
     */
    private Integer orderStatus;

    /**
     * 订单状态列表
     */
    private List<Integer> orderStatuses;

    /**
     * 数据删除日期
     */
    private Date date;

    /**
     * 同步订单开始时间
     */
    private Long startUpdateTime;

    /**
     * 同步订单结束时间
     */
    private Long endUpdateTime;

    public enum enumOrderTypeTiltle{
        purhcase,
        share,
        team;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getOrderTypeTitle() {
        return orderTypeTitle;
    }

    public void setOrderTypeTitle(String orderTypeTitle) {
        this.orderTypeTitle = orderTypeTitle;
    }

    public Long getShareId() {
        return shareId;
    }

    public void setShareId(Long shareId) {
        this.shareId = shareId;
    }

    public String getShareOpenid() {
        return shareOpenid;
    }

    public void setShareOpenid(String shareOpenid) {
        this.shareOpenid = shareOpenid;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public List<Integer> getOrderStatuses() {
        return orderStatuses;
    }

    public void setOrderStatuses(List<Integer> orderStatuses) {
        this.orderStatuses = orderStatuses;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getStartUpdateTime() {
        return startUpdateTime;
    }

    public void setStartUpdateTime(Long startUpdateTime) {
        this.startUpdateTime = startUpdateTime;
    }

    public Long getEndUpdateTime() {
        return endUpdateTime;
    }

    public void setEndUpdateTime(Long endUpdateTime) {
        this.endUpdateTime = endUpdateTime;
    }
}
