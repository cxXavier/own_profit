package com.xavier.fast.model.user.login;

import com.xavier.fast.annotation.validate.NotEmpty;
import com.xavier.fast.model.base.RopRequest;

/**
* @Description:    登陆
* @Author:         Wang
* @CreateDate:     2019/7/2 16:57
* @UpdateUser:
* @UpdateDate:     2019/7/2 16:57
* @UpdateRemark:
* @Version:        1.0
*/
public class RopInviteRequest extends RopRequest {

    private static final long serialVersionUID = 7234855154317631228L;

    @NotEmpty(message = "openId不能为空")
    private String openId;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }
}
