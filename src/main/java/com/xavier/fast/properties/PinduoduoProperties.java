package com.xavier.fast.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * 拼多多相关配置类
 */
@Configuration
@ConfigurationProperties(prefix = "pdd.config")
public class PinduoduoProperties {

    private String clientId;

    private String clientSecret;

    /**
     * 默认推广位ID
     */
    private String defaultPid;


    /**
     * 统计已售商品的状态：pingdd.config.soldState
     * 0,1,2,3,5
     */
    private Integer[] sold;

    /**
     * 可返现订单状态
     */
    private Integer[] cashbacked;

    private Integer[] invalid;

    private Integer[] cashbacking;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getDefaultPid() {
        return defaultPid;
    }

    public void setDefaultPid(String defaultPid) {
        this.defaultPid = defaultPid;
    }

    public Integer[] getSold() {
        return sold;
    }

    public void setSold(Integer[] sold) {
        this.sold = sold;
    }

    public Integer[] getCashbacked() {
        return cashbacked;
    }

    public void setCashbacked(Integer[] cashbacked) {
        this.cashbacked = cashbacked;
    }

    public Integer[] getInvalid() {
        return invalid;
    }

    public void setInvalid(Integer[] invalid) {
        this.invalid = invalid;
    }

    public Integer[] getCashbacking() {
        return cashbacking;
    }

    public void setCashbacking(Integer[] cashbacking) {
        this.cashbacking = cashbacking;
    }

    public Map<String,String> getCommonParam(){
        Map<String,String> param = new HashMap<>();
        param.put("client_id",clientId);
        param.put("data_type","JSON");
        param.put("timestamp",System.currentTimeMillis()+"");
        return param;
    }

}
