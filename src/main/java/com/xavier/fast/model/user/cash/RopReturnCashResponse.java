package com.xavier.fast.model.user.cash;

import java.io.Serializable;

/**
 * @Description:    用户提现
 * @Author:         Wang
 * @CreateDate:     2019/7/3 22:15
 * @UpdateUser:
 * @UpdateDate:     2019/7/3 22:15
 * @UpdateRemark:
 * @Version:        1.0
 */
public class RopReturnCashResponse implements Serializable {
    private static final long serialVersionUID = -626773666713402651L;

    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
