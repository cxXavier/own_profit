package com.xavier.fast.entity.user;

import java.io.Serializable;
import java.util.Date;

/**
* @Description:    用户提现记录
* @Author:         Wang
* @CreateDate:     2019/7/4 15:12
* @UpdateUser:
* @UpdateDate:     2019/7/4 15:12
* @UpdateRemark:
* @Version:        1.0
*/
public class UserReturnCashRecord implements Serializable {

    private static final long serialVersionUID = 8885436877011688393L;

    private Integer id;

    private String openId;

    /**
     * 订单号
     */
    private Integer orderId;

    /**
     * 提现金额
     */
    private Integer cashBackAmount;

    /**
     * 提现状态(0-未提现，1-已提现)
     */
    private Integer cashBackStatus;

    /**
     * 微信支付状态
     */
    private String wechatPaymentStatus;

    /**
     * 微信订单号
     */
    private String wechatPaymentNo;

    /**
     * 微信支付时间
     */
    private Date wechatPaymentTime;

    private Date createTime;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getCashBackAmount() {
        return cashBackAmount;
    }

    public void setCashBackAmount(Integer cashBackAmount) {
        this.cashBackAmount = cashBackAmount;
    }

    public Integer getCashBackStatus() {
        return cashBackStatus;
    }

    public void setCashBackStatus(Integer cashBackStatus) {
        this.cashBackStatus = cashBackStatus;
    }

    public String getWechatPaymentStatus() {
        return wechatPaymentStatus;
    }

    public void setWechatPaymentStatus(String wechatPaymentStatus) {
        this.wechatPaymentStatus = wechatPaymentStatus;
    }

    public String getWechatPaymentNo() {
        return wechatPaymentNo;
    }

    public void setWechatPaymentNo(String wechatPaymentNo) {
        this.wechatPaymentNo = wechatPaymentNo;
    }

    public Date getWechatPaymentTime() {
        return wechatPaymentTime;
    }

    public void setWechatPaymentTime(Date wechatPaymentTime) {
        this.wechatPaymentTime = wechatPaymentTime;
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
}
