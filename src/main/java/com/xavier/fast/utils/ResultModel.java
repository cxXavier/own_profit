//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.xavier.fast.utils;

import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class ResultModel<T> implements Serializable {
    private static final long serialVersionUID = 9041530013023432967L;
    private T returnValue;
    private boolean isSuccessed = true;
    private String errorCode;
    private String errorDesc;

    public ResultModel() {
    }

    public static <T> ResultModel<T> newInstance() {
        return new ResultModel();
    }

    public T getReturnValue() {
        return this.returnValue;
    }

    public void setReturnValue(T returnValue) {
        this.returnValue = returnValue;
    }

    public boolean isSuccessed() {
        return this.isSuccessed;
    }

    public void setSuccessed(boolean isSuccessed) {
        this.isSuccessed = isSuccessed;
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorDesc() {
        return this.errorDesc;
    }

    public void setErrorDesc(String errorDesc) {
        this.errorDesc = errorDesc;
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
