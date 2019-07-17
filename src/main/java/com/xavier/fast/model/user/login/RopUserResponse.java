package com.xavier.fast.model.user.login;

import com.xavier.fast.entity.user.User;
import com.xavier.fast.entity.user.UserVo;

import java.io.Serializable;
import java.util.List;

/**
* @Description:    用户
* @Author:         Wang
* @CreateDate:     2019/7/2 16:58
* @UpdateUser:
* @UpdateDate:     2019/7/2 16:58
* @UpdateRemark:
* @Version:        1.0
*/
public class RopUserResponse implements Serializable {

    private static final long serialVersionUID = 5136541985088150179L;

    private Integer needPeoples;

    private List<User> dataList;

    public Integer getNeedPeoples() {
        return needPeoples;
    }

    public void setNeedPeoples(Integer needPeoples) {
        this.needPeoples = needPeoples;
    }

    public List<User> getDataList() {
        return dataList;
    }

    public void setDataList(List<User> dataList) {
        this.dataList = dataList;
    }
}
