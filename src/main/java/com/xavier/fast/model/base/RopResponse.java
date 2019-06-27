package com.xavier.fast.model.base;

import java.io.Serializable;

/**
* @Description:    结果集基类
* @Author:         Wang
* @CreateDate:     2019/6/25 17:02
* @UpdateUser:     
* @UpdateDate:     2019/6/25 17:02
* @UpdateRemark:   
* @Version:        1.0
*/
public class RopResponse <T> implements Serializable {

    private static final long serialVersionUID = -7772178794345304873L;
    /**
     *
     */
    private String code="";

    /**
     * 描述
     */
    private String message="";

    /**
     * 错误信息
     */
    private String errorMessage="";

    /**
     * 接口状态:SUCCESS/FAILED
     */
    private String success;

    /**
     * 接口版本号
     */
    private String version;

    /**
     * 接口数据
     */
    private T data ;

    public RopResponse () {}

    public RopResponse(String code, String message, String errorMessage,
                       String success, String version, T data) {
        this.code = code;
        this.message = message;
        this.errorMessage = errorMessage;
        this.success = success;
        this.version = version;
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "RopResponse{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                ", success='" + success + '\'' +
                ", version='" + version + '\'' +
                ", data=" + data +
                '}';
    }

    public static enum ResultStatus {
        SUCCESS, FAILED,
    }

    public static RopResponse createSuccessRep(String code, String message, String version, Object data){

        return new RopResponse(code, message, "", ResultStatus.SUCCESS.name(), version, data);
    }

    public static RopResponse createFailedRep(String code, String errorMessage, String version){

        return new RopResponse(code, "", errorMessage, ResultStatus.FAILED.name(), version, null);
    }

}
