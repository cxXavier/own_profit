package com.xavier.fast.service.user;

import com.xavier.fast.model.base.RopRequestBody;
import com.xavier.fast.model.base.RopResponse;
import com.xavier.fast.model.user.login.RopInviteRequest;
import com.xavier.fast.model.user.login.RopUserResponse;

/**
* @Description:    用户信息
* @Author:         Wang
* @CreateDate:     2019/7/17 17:14
* @UpdateUser:
* @UpdateDate:     2019/7/17 17:14
* @UpdateRemark:
* @Version:        1.0
*/
public interface IUserService {

    /**
     * 获取徒弟信息列表
     * @param inviteRequest
     * @return
     */
    public RopResponse<RopUserResponse> getInviteUsers(RopRequestBody<RopInviteRequest> inviteRequest);

}
