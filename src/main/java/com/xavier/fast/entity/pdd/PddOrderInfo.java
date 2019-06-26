package com.xavier.fast.entity.pdd;

import java.io.Serializable;

public class PddOrderInfo implements Serializable {

    /**
     * 多多客工具ID
     */
    private Long authDuoId;

    /**
     * 结算批次号
     */
    private String batchNo;

    /**
     * 自定义参数
     */
    private String customParameters;

    /**
     * 多多券金额
     */
    private Integer duoCouponAmount;

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
     * 成团编号
     */
    private Long groupId;

    /**
     * 订单来源 ：0 ：关联，5 ：直接下单页RPC请求
     */
    private Integer matchChannel;

    /**
     * 订单价格（分）
     */
    private Long orderAmount;

    /**
     * 订单创建时间
     */
    private Long orderCreateTime;

    /**
     * 订单成团时间（UNIX时间戳）
     */
    private Long orderGroupSuccessTime;

    /**
     * 订单最后更新时间
     */
    private Long orderModifyAt;

    /**
     * 订单支付时间（UNIX时间戳）
     */
    private Long orderPayTime;

    /**
     * 订单确认收货时间（UNIX时间戳）
     */
    private Long orderReceiveTime;

    /**
     * 订单结算时间（UNIX时间戳）
     */
    private Long orderSettleTime;

    /**
     * 拼多多订单编号
     */
    private String orderSn;

    /**
     * 订单状态
     */
    private Integer orderStatus;

    /**
     * 订单状态描述
     * （ -1 未支付; 0-已支付；1-已成团；2-确认收货；3-审核成功；4-审核失败（不可提现）；
     *  5-已经结算；8-非多多进宝商品（无佣金订单）;10-已处罚）
     */
    private String orderStatusDesc;

    /**
     * 订单审核时间（UNIX时间戳）
     */
    private Long orderVerifyTime;

    /**
     * 推广位ID
     */
    private String pid;

    /**
     * 佣金（分）
     */
    private Long promotionAmount;

    /**
     * 佣金比例 千分比
     */
    private Long promotionRate;

    /**
     * 订单类型：0：领券页面， 1： 红包页， 2：领券页， 3： 题页
     */
    private Integer type;


    /**
     * 招商多多客ID
     */
    private Long zsDuoId;

    /**
     * cpsSign 拼多多返回字段
     */
    private String cpsSign;

    /**
     * 链接最后一次生产时间
     */
    private String urlLastGenerateTime;

    /**
     * 打点时间
     */
    private Long pointTime;

    /**
     * 售后状态：0：无，1：售后中，2：售后完成
     */
    private Integer returnStatus;

    /**
     * 接口文档中没该字段，接口返回
     */
    private Long verifyTime;

    public Long getAuthDuoId() {
        return authDuoId;
    }

    public void setAuthDuoId(Long authDuoId) {
        this.authDuoId = authDuoId;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public String getCustomParameters() {
        return customParameters;
    }

    public void setCustomParameters(String customParameters) {
        this.customParameters = customParameters;
    }

    public Integer getDuoCouponAmount() {
        return duoCouponAmount;
    }

    public void setDuoCouponAmount(Integer duoCouponAmount) {
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

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Integer getMatchChannel() {
        return matchChannel;
    }

    public void setMatchChannel(Integer matchChannel) {
        this.matchChannel = matchChannel;
    }

    public Long getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(Long orderAmount) {
        this.orderAmount = orderAmount;
    }

    public Long getOrderCreateTime() {
        return orderCreateTime;
    }

    public void setOrderCreateTime(Long orderCreateTime) {
        this.orderCreateTime = orderCreateTime;
    }

    public Long getOrderGroupSuccessTime() {
        return orderGroupSuccessTime;
    }

    public void setOrderGroupSuccessTime(Long orderGroupSuccessTime) {
        this.orderGroupSuccessTime = orderGroupSuccessTime;
    }

    public Long getOrderModifyAt() {
        return orderModifyAt;
    }

    public void setOrderModifyAt(Long orderModifyAt) {
        this.orderModifyAt = orderModifyAt;
    }

    public Long getOrderPayTime() {
        return orderPayTime;
    }

    public void setOrderPayTime(Long orderPayTime) {
        this.orderPayTime = orderPayTime;
    }

    public Long getOrderReceiveTime() {
        return orderReceiveTime;
    }

    public void setOrderReceiveTime(Long orderReceiveTime) {
        this.orderReceiveTime = orderReceiveTime;
    }

    public Long getOrderSettleTime() {
        return orderSettleTime;
    }

    public void setOrderSettleTime(Long orderSettleTime) {
        this.orderSettleTime = orderSettleTime;
    }

    public String getOrderSn() {
        return orderSn;
    }

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderStatusDesc() {
        return orderStatusDesc;
    }

    public void setOrderStatusDesc(String orderStatusDesc) {
        this.orderStatusDesc = orderStatusDesc;
    }

    public Long getOrderVerifyTime() {
        return orderVerifyTime;
    }

    public void setOrderVerifyTime(Long orderVerifyTime) {
        this.orderVerifyTime = orderVerifyTime;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getZsDuoId() {
        return zsDuoId;
    }

    public void setZsDuoId(Long zsDuoId) {
        this.zsDuoId = zsDuoId;
    }

    public String getCpsSign() {
        return cpsSign;
    }

    public void setCpsSign(String cpsSign) {
        this.cpsSign = cpsSign;
    }

    public String getUrlLastGenerateTime() {
        return urlLastGenerateTime;
    }

    public void setUrlLastGenerateTime(String urlLastGenerateTime) {
        this.urlLastGenerateTime = urlLastGenerateTime;
    }

    public Long getPointTime() {
        return pointTime;
    }

    public void setPointTime(Long pointTime) {
        this.pointTime = pointTime;
    }

    public Integer getReturnStatus() {
        return returnStatus;
    }

    public void setReturnStatus(Integer returnStatus) {
        this.returnStatus = returnStatus;
    }

    public Long getVerifyTime() {
        return verifyTime;
    }

    public void setVerifyTime(Long verifyTime) {
        this.verifyTime = verifyTime;
    }
}
