package com.xavier.fast.entity.user;

import java.util.Date;

public class User {
    private Long id;

    private String avatar;

    private String nickname;

    private Boolean gender;

    private String mobile;

    private String openid;

    private String unionid;

    private String inviteCode;

    private String parentOpenid;

    private String parentUnionid;

    private String grandparentOpenid;

    private String grandparentUnionid;

    private Date created;

    private Date updated;

    private String appOpenid;

    private String source;

    private String relName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar == null ? null : avatar.trim();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid == null ? null : unionid.trim();
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode == null ? null : inviteCode.trim();
    }

    public String getParentOpenid() {
        return parentOpenid;
    }

    public void setParentOpenid(String parentOpenid) {
        this.parentOpenid = parentOpenid == null ? null : parentOpenid.trim();
    }

    public String getParentUnionid() {
        return parentUnionid;
    }

    public void setParentUnionid(String parentUnionid) {
        this.parentUnionid = parentUnionid == null ? null : parentUnionid.trim();
    }

    public String getGrandparentOpenid() {
        return grandparentOpenid;
    }

    public void setGrandparentOpenid(String grandparentOpenid) {
        this.grandparentOpenid = grandparentOpenid == null ? null : grandparentOpenid.trim();
    }

    public String getGrandparentUnionid() {
        return grandparentUnionid;
    }

    public void setGrandparentUnionid(String grandparentUnionid) {
        this.grandparentUnionid = grandparentUnionid == null ? null : grandparentUnionid.trim();
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public String getAppOpenid() {
        return appOpenid;
    }

    public void setAppOpenid(String appOpenid) {
        this.appOpenid = appOpenid == null ? null : appOpenid.trim();
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }

    public String getRelName() {
        return relName;
    }

    public void setRelName(String relName) {
        this.relName = relName;
    }
}