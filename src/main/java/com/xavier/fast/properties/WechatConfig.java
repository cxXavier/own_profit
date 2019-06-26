package com.xavier.fast.properties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 微信配置
 * @author bcc
 */
@Configuration
@ConfigurationProperties(prefix = "wechat.config")
public class WechatConfig {

    private String jscode2sessionUrl = "https://api.weixin.qq.com/sns/jscode2session";

    /**
     * 转帐接口
     */
    private String payPersonUrl = "https://api.mch.weixin.qq.com/mmpaymkttransfers/promotion/transfers";

    /**
     * 查询
     */
    private String payQueryUrl = "https://api.mch.weixin.qq.com/mmpaymkttransfers/gettransferinfo" ;

    /**
     * 获取accessToken
     */
    private String accessTokenUrl = "https://api.weixin.qq.com/cgi-bin/token" ;

    /**
     * 发送模板消息
     */
    private String sendTemplateMessage = "https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send";

    /**
     * 公众号ID
     */
    private String appId = "wx570f34ccd5e1e73f";


    private String appSecret = "4c75cd7bd386345964a5eb22bdd830a1";

    /**
     * 微信支付分配的商户号
     */
    private String mchId;

    /**
     * 微信支付密匙
     */
    private  String paySecret;

    /**
     * 证书路径地址
     */
    private String certPath;

    /**
     * 支付描述
     */
    private String payDesc = "多多客提现到账";

    /**
     * 助力消息模板id
     */
    private String boostTemplateId = "Ubzs3OTcemgql2EGPolJIA0CVHQiY6Y0GTX3_fPZffY";

    /**
     * 零元购剩余次数消息模板
     */
    private  String zeroSpTimesTemplateId = "WNKZt7NPYQ8889huEl8M4The-m3pm0pFZv5I4LrIz2I";

    /**
     * 收益到账消息模板
     */
    private  String incomeTemplateId = "LwlRGwVWwNaVAuV_um8Z_nGbKOOHK9fIR8mt6FMRC7I";

    public String getJscode2sessionUrl() {
        return jscode2sessionUrl;
    }

    public void setJscode2sessionUrl(String jscode2sessionUrl) {
        this.jscode2sessionUrl = jscode2sessionUrl;
    }

    public String getPayPersonUrl() {
        return payPersonUrl;
    }

    public void setPayPersonUrl(String payPersonUrl) {
        this.payPersonUrl = payPersonUrl;
    }

    public String getPayQueryUrl() {
        return payQueryUrl;
    }

    public void setPayQueryUrl(String payQueryUrl) {
        this.payQueryUrl = payQueryUrl;
    }

    public String getAccessTokenUrl() {
        return accessTokenUrl;
    }

    public void setAccessTokenUrl(String accessTokenUrl) {
        this.accessTokenUrl = accessTokenUrl;
    }

    public String getSendTemplateMessage() {
        return sendTemplateMessage;
    }

    public void setSendTemplateMessage(String sendTemplateMessage) {
        this.sendTemplateMessage = sendTemplateMessage;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getPaySecret() {
        return paySecret;
    }

    public void setPaySecret(String paySecret) {
        this.paySecret = paySecret;
    }

    public String getCertPath() {
        return certPath;
    }

    public void setCertPath(String certPath) {
        this.certPath = certPath;
    }

    public String getPayDesc() {
        return payDesc;
    }

    public void setPayDesc(String payDesc) {
        this.payDesc = payDesc;
    }

    public String getBoostTemplateId() {
        return boostTemplateId;
    }

    public void setBoostTemplateId(String boostTemplateId) {
        this.boostTemplateId = boostTemplateId;
    }

    public String getZeroSpTimesTemplateId() {
        return zeroSpTimesTemplateId;
    }

    public void setZeroSpTimesTemplateId(String zeroSpTimesTemplateId) {
        this.zeroSpTimesTemplateId = zeroSpTimesTemplateId;
    }

    public String getIncomeTemplateId() {
        return incomeTemplateId;
    }

    public void setIncomeTemplateId(String incomeTemplateId) {
        this.incomeTemplateId = incomeTemplateId;
    }
}
