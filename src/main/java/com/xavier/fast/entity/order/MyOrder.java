package com.xavier.fast.entity.order;

import com.xavier.fast.utils.CalFlowerUtils;

import java.io.Serializable;
import java.util.Date;

public class MyOrder implements Serializable {

    private static final long serialVersionUID = 3343516884487540977L;

    private Integer id;

    /**
     * 拼多多订单号
     */
    private String pddOrderId;

    /**
     * 订单状态
     */
    private String orderStatus;

    /**
     * 多多券金额
     */
    private Long duoCouponAmount;

    /**
     * 商品id
     */
    private String goodsId;

    /**
     * 商品名
     */
    private String goodsName;

    /**
     * 商品价格（分）
     */
    private Long goodsPrice;

    /**
     * 商品数量
     */
    private Integer goodsQuantity;

    /**
     * 商品缩略图
     */
    private String goodsThumbnailUrl;

    /**
     * 订单价格（分）
     */
    private Long orderAmount;

    /**
     * 订单创建时间
     */
    private Date orderCreateTime;

    /**
     * 提现需要鲜花数
     */
    private Integer cashCostFlower;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPddOrderId() {
        return pddOrderId;
    }

    public void setPddOrderId(String pddOrderId) {
        this.pddOrderId = pddOrderId;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Long getDuoCouponAmount() {
        return duoCouponAmount;
    }

    public void setDuoCouponAmount(Long duoCouponAmount) {
        this.duoCouponAmount = duoCouponAmount;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Long getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(Long goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public Integer getGoodsQuantity() {
        return goodsQuantity;
    }

    public void setGoodsQuantity(Integer goodsQuantity) {
        this.goodsQuantity = goodsQuantity;
    }

    public String getGoodsThumbnailUrl() {
        return goodsThumbnailUrl;
    }

    public void setGoodsThumbnailUrl(String goodsThumbnailUrl) {
        this.goodsThumbnailUrl = goodsThumbnailUrl;
    }

    public Long getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(Long orderAmount) {
        this.orderAmount = orderAmount;
    }

    public Date getOrderCreateTime() {
        return orderCreateTime;
    }

    public void setOrderCreateTime(Date orderCreateTime) {
        this.orderCreateTime = orderCreateTime;
    }

    public Integer getCashCostFlower() {
        return CalFlowerUtils.calCashFlower(getOrderAmount());
    }

    public void setCashCostFlower(Integer cashCostFlower) {
        this.cashCostFlower = cashCostFlower;
    }
}
