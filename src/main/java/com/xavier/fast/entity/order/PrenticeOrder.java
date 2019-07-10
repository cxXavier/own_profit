package com.xavier.fast.entity.order;

import java.io.Serializable;
import java.util.Date;

public class PrenticeOrder implements Serializable {

    private static final long serialVersionUID = -2008245947905506803L;

    private String avatar;

    private String nickname;

    /**
     * 贡献鲜花数量
     */
    private Integer contributionFlower;

    /**
     * 订单状态
     */
    private String orderStatus;

    /**
     * 订单创建时间
     */
    private Date orderCreateTime;

    private Boolean notice;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getContributionFlower() {
        return contributionFlower;
    }

    public void setContributionFlower(Integer contributionFlower) {
        this.contributionFlower = contributionFlower;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Date getOrderCreateTime() {
        return orderCreateTime;
    }

    public void setOrderCreateTime(Date orderCreateTime) {
        this.orderCreateTime = orderCreateTime;
    }

    public Boolean getNotice() {
        return notice;
    }

    public void setNotice(Boolean notice) {
        this.notice = notice;
    }
}
