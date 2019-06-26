package com.xavier.fast.entity.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;


/**
 * 微信登录接口返回对象
 * @author bcc
 */
public class WechatLoginReturn implements Serializable {

    private String openid;
    private String unioinid;
    private String sessionkey;
    private String errcode;
    private String errMsg;

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getUnioinid() {
        return unioinid;
    }

    public void setUnioinid(String unioinid) {
        this.unioinid = unioinid;
    }

    public String getSessionkey() {
        return sessionkey;
    }

    public void setSessionkey(String sessionkey) {
        this.sessionkey = sessionkey;
    }

    public String getErrcode() {
        return errcode;
    }

    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}
