package com.xavier.fast.model.user.login;

import com.xavier.fast.annotation.validate.NotEmpty;
import com.xavier.fast.model.base.RopRequest;

/**
* @Description:    form
* @Author:         Wang
* @CreateDate:     2019/7/2 16:57
* @UpdateUser:
* @UpdateDate:     2019/7/2 16:57
* @UpdateRemark:
* @Version:        1.0
*/
public class RopUserFormRequest extends RopRequest {

    private static final long serialVersionUID = -8094591299428513351L;

    @NotEmpty(message = "openId不能为空")
    private String openId;

    @NotEmpty(message = "formId不能为空")
    private String formId;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getFormId() {
        return formId;
    }

    public void setFormId(String formId) {
        this.formId = formId;
    }
}
