package com.xavier.fast.model.user.login;

import com.xavier.fast.annotation.validate.NotEmpty;
import com.xavier.fast.model.base.RopRequest;

/**
* @Description:    登陆
* @Author:         Wang
* @CreateDate:     2019/7/2 16:57
* @UpdateUser:
* @UpdateDate:     2019/7/2 16:57
* @UpdateRemark:
* @Version:        1.0
*/
public class RopBindRequest extends RopRequest {
    private static final long serialVersionUID = -9095901388832441258L;

    private String code;

    private String openid;

    /**
     * 头像url
     */
    private String avatar;

    private String nickname;

    /**
     * 性别
     */
    private Integer gender;

    @NotEmpty(message = "inviteCode不能为空")
    private String inviteCode;

    private String mobile;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

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

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
