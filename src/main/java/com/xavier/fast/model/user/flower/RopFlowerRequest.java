package com.xavier.fast.model.user.flower;

import com.xavier.fast.annotation.validate.NotEmpty;
import com.xavier.fast.model.base.RopRequest;

import java.io.Serializable;

/**
* @Description:    鲜花
* @Author:         Wang
* @CreateDate:     2019/7/2 19:36
* @UpdateUser:
* @UpdateDate:     2019/7/2 19:36
* @UpdateRemark:
* @Version:        1.0
*/
public class RopFlowerRequest extends RopRequest implements Serializable {
    private static final long serialVersionUID = -8155846985344285796L;

    @NotEmpty(message = "opendId不能为空")
    private String openId;

    private String unionId;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }
}
