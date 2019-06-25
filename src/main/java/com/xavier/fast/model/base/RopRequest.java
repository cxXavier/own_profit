package com.xavier.fast.model.base;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;

/**
* @Description:    请求基类
* @Author:         Wang
* @CreateDate:     2019/6/25 19:52
* @UpdateUser:
* @UpdateDate:     2019/6/25 19:52
* @UpdateRemark:
* @Version:        1.0
*/
public class RopRequest implements Serializable {

    /**
     * 方法名
     */
    private String method;

    /**
     * 版本号
     */
    private String version;

    /**
     * 设备名称
     */
    private String deviceName;

    private HttpServletRequest request;

    private HttpServletResponse response;

    private String ip;

    /**
     * 设备指纹字段
     */
    private String bsfitDeviceid;

    /**
     * 验证码
     */
    private String validateCode;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 客户端发起请求的时间戳(毫秒)
     */
    private Long clientTimestamp;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getBsfitDeviceid() {
        return bsfitDeviceid;
    }

    public void setBsfitDeviceid(String bsfitDeviceid) {
        this.bsfitDeviceid = bsfitDeviceid;
    }

    public String getValidateCode() {
        return validateCode;
    }

    public void setValidateCode(String validateCode) {
        this.validateCode = validateCode;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getClientTimestamp() {
        return clientTimestamp;
    }

    public void setClientTimestamp(Long clientTimestamp) {
        this.clientTimestamp = clientTimestamp;
    }

    public RopRequest() {
    }

    public RopRequest(String method, String version, String deviceName, HttpServletRequest request, HttpServletResponse response, String ip, String bsfitDeviceid, String validateCode, Long userId, String userName, Long clientTimestamp) {
        this.method = method;
        this.version = version;
        this.deviceName = deviceName;
        this.request = request;
        this.response = response;
        this.ip = ip;
        this.bsfitDeviceid = bsfitDeviceid;
        this.validateCode = validateCode;
        this.userId = userId;
        this.userName = userName;
        this.clientTimestamp = clientTimestamp;
    }

    @Override
    public String toString() {
        return "RopRequest{" +
                "method='" + method + '\'' +
                ", version='" + version + '\'' +
                ", deviceName='" + deviceName + '\'' +
                ", request=" + request +
                ", response=" + response +
                ", ip='" + ip + '\'' +
                ", bsfitDeviceid='" + bsfitDeviceid + '\'' +
                ", validateCode='" + validateCode + '\'' +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                ", clientTimestamp=" + clientTimestamp +
                '}';
    }
}
