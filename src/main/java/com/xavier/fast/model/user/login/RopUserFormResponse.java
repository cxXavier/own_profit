package com.xavier.fast.model.user.login;

import com.xavier.fast.annotation.validate.NotEmpty;
import com.xavier.fast.model.base.RopRequest;

import java.io.Serializable;

/**
* @Description:    form
* @Author:         Wang
* @CreateDate:     2019/7/2 16:57
* @UpdateUser:
* @UpdateDate:     2019/7/2 16:57
* @UpdateRemark:
* @Version:        1.0
*/
public class RopUserFormResponse implements Serializable {

    private static final long serialVersionUID = -7686791102631572258L;

    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
