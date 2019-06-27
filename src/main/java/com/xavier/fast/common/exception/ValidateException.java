package com.xavier.fast.common.exception;

/**
* @Description:    验证异常
* @Author:         Wang
* @CreateDate:     2019/6/26 14:24
* @UpdateUser:
* @UpdateDate:     2019/6/26 14:24
* @UpdateRemark:
* @Version:        1.0
*/
public class ValidateException extends RuntimeException  {

    public ValidateException() {
        super();
    }

    public ValidateException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValidateException(String message) {
        super(message);
    }

    public ValidateException(Throwable cause) {
        super(cause);
    }
}
