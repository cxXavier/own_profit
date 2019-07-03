package com.xavier.fast.model.user.login;

import com.xavier.fast.entity.user.UserVo;

import java.io.Serializable;

/**
* @Description:    登陆
* @Author:         Wang
* @CreateDate:     2019/7/2 16:58
* @UpdateUser:
* @UpdateDate:     2019/7/2 16:58
* @UpdateRemark:
* @Version:        1.0
*/
public class RopLoginResponse implements Serializable {
    private static final long serialVersionUID = 5806606290435904595L;

    private UserVo userInfo;

    public UserVo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserVo userInfo) {
        this.userInfo = userInfo;
    }
}
