package com.xavier.fast.entity.user;

import java.io.Serializable;

/**
 * 用户信息vo
 * @author 用户信息vo
 */
public class UserVo  implements Serializable {

    private String mobile;

    private String openid;

    private String unionid;

    /**
     * 邀请码
     */
    private String inviteCode;

    private String sessionId;

    private Boolean newUser = false;  // 是否为新用户

    public Boolean getNewUser() {
        return newUser;
    }

    public void setNewUser(Boolean newUser) {
        this.newUser = newUser;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
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

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
