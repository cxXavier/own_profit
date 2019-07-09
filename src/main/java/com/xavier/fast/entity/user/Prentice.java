package com.xavier.fast.entity.user;

import java.io.Serializable;

/**
* @Description:    徒弟信息
* @Author:         Wang
* @CreateDate:     2019/7/3 0:22
* @UpdateUser:
* @UpdateDate:     2019/7/3 0:22
* @UpdateRemark:
* @Version:        1.0
*/
public class Prentice implements Serializable {
    private static final long serialVersionUID = -5566847491448496337L;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 贡献鲜花数
     */
    private Integer contributionFlower;

    /**
     * 待结算鲜花数
     */
    private Integer waitSettleFlower;

    private Boolean notice;

    private String openid;

    private String unionid;

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

    public Integer getWaitSettleFlower() {
        return waitSettleFlower;
    }

    public void setWaitSettleFlower(Integer waitSettleFlower) {
        this.waitSettleFlower = waitSettleFlower;
    }

    public Boolean getNotice() {
        return notice;
    }

    public void setNotice(Boolean notice) {
        this.notice = notice;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }
}
