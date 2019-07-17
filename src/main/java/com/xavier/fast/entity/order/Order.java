package com.xavier.fast.entity.order;

import com.xavier.fast.entity.BasePageQuery;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
* @Description:    订单对象
* @Author:         Wang
* @CreateDate:     2019/7/2 23:07
* @UpdateUser:
* @UpdateDate:     2019/7/2 23:07
* @UpdateRemark:
* @Version:        1.0
*/
public class Order extends BasePageQuery implements Serializable {

    private static final long serialVersionUID = -4521309469481245221L;

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
     * 订单最后更新时间
     */
    private Date orderModifyAt;

    /**
     * 订单支付时间（UNIX时间戳）
     */
    private Date orderPayTime;

    /**
     * 订单确认收货时间（UNIX时间戳）
     */
    private Date orderReceiveTime;

    /**
     * 订单结算时间（UNIX时间戳）
     */
    private Date orderSettleTime;

    /**
     * 订单状态描述
     * （ -1 未支付; 0-已支付；1-已成团；2-确认收货；3-审核成功；4-审核失败（不可提现）；
     *  5-已经结算；8-非多多进宝商品（无佣金订单）;10-已处罚）
     */
    private String orderStatusDesc;

    /**
     * 订单审核时间（UNIX时间戳）
     */
    private Date orderVerifyTime;

    /**
     * 佣金（分）
     */
    private Long promotionAmount;

    /**
     * 佣金比例 千分比
     */
    private Long promotionRate;

    /**
     * 售后状态：0：无，1：售后中，2：售后完成
     */
    private Integer returnStatus;

    private String openId;

    private String unionId;

    private String parentOpenId;

    private String parentUnionId;

    /**
     * 提现状态(0-未提现，1-已提现)
     */
    private Integer cashBackStatus;

    /**
     * 贡献鲜花数量
     */
    private Integer contributionFlower;

    /**
     * 提现版本号（乐观锁用）
     */
    private Integer cashBackVersion;

    private Date createTime;

    private Date updateTime;

    private List<String> openIds;

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

    public Date getOrderModifyAt() {
        return orderModifyAt;
    }

    public void setOrderModifyAt(Date orderModifyAt) {
        this.orderModifyAt = orderModifyAt;
    }

    public Date getOrderPayTime() {
        return orderPayTime;
    }

    public void setOrderPayTime(Date orderPayTime) {
        this.orderPayTime = orderPayTime;
    }

    public Date getOrderReceiveTime() {
        return orderReceiveTime;
    }

    public void setOrderReceiveTime(Date orderReceiveTime) {
        this.orderReceiveTime = orderReceiveTime;
    }

    public Date getOrderSettleTime() {
        return orderSettleTime;
    }

    public void setOrderSettleTime(Date orderSettleTime) {
        this.orderSettleTime = orderSettleTime;
    }

    public String getOrderStatusDesc() {
        return orderStatusDesc;
    }

    public void setOrderStatusDesc(String orderStatusDesc) {
        this.orderStatusDesc = orderStatusDesc;
    }

    public Date getOrderVerifyTime() {
        return orderVerifyTime;
    }

    public void setOrderVerifyTime(Date orderVerifyTime) {
        this.orderVerifyTime = orderVerifyTime;
    }

    public Long getPromotionAmount() {
        return promotionAmount;
    }

    public void setPromotionAmount(Long promotionAmount) {
        this.promotionAmount = promotionAmount;
    }

    public Long getPromotionRate() {
        return promotionRate;
    }

    public void setPromotionRate(Long promotionRate) {
        this.promotionRate = promotionRate;
    }

    public Integer getReturnStatus() {
        return returnStatus;
    }

    public void setReturnStatus(Integer returnStatus) {
        this.returnStatus = returnStatus;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    public String getParentOpenId() {
        return parentOpenId;
    }

    public void setParentOpenId(String parentOpenId) {
        this.parentOpenId = parentOpenId;
    }

    public String getParentUnionId() {
        return parentUnionId;
    }

    public void setParentUnionId(String parentUnionId) {
        this.parentUnionId = parentUnionId;
    }

    public Integer getCashBackStatus() {
        return cashBackStatus;
    }

    public void setCashBackStatus(Integer cashBackStatus) {
        this.cashBackStatus = cashBackStatus;
    }

    public Integer getContributionFlower() {
        return contributionFlower;
    }

    public void setContributionFlower(Integer contributionFlower) {
        this.contributionFlower = contributionFlower;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getCashBackVersion() {
        return cashBackVersion;
    }

    public void setCashBackVersion(Integer cashBackVersion) {
        this.cashBackVersion = cashBackVersion;
    }

    public List<String> getOpenIds() {
        return openIds;
    }

    public void setOpenIds(List<String> openIds) {
        this.openIds = openIds;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", pddOrderId='" + pddOrderId + '\'' +
                ", orderStatus='" + orderStatus + '\'' +
                ", duoCouponAmount=" + duoCouponAmount +
                ", goodsId='" + goodsId + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", goodsPrice=" + goodsPrice +
                ", goodsQuantity=" + goodsQuantity +
                ", goodsThumbnailUrl='" + goodsThumbnailUrl + '\'' +
                ", orderAmount=" + orderAmount +
                ", orderCreateTime=" + orderCreateTime +
                ", orderModifyAt=" + orderModifyAt +
                ", orderPayTime=" + orderPayTime +
                ", orderReceiveTime=" + orderReceiveTime +
                ", orderSettleTime=" + orderSettleTime +
                ", orderStatusDesc='" + orderStatusDesc + '\'' +
                ", orderVerifyTime=" + orderVerifyTime +
                ", promotionAmount=" + promotionAmount +
                ", promotionRate=" + promotionRate +
                ", returnStatus=" + returnStatus +
                ", openId='" + openId + '\'' +
                ", unionId='" + unionId + '\'' +
                ", parentOpenId='" + parentOpenId + '\'' +
                ", parentUnionId='" + parentUnionId + '\'' +
                ", cashBackStatus=" + cashBackStatus +
                ", contributionFlower=" + contributionFlower +
                ", cashBackVersion=" + cashBackVersion +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
