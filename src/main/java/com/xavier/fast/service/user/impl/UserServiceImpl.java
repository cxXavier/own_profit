package com.xavier.fast.service.user.impl;

import com.xavier.fast.annotation.ApiMethod;
import com.xavier.fast.dao.UserFormidMapper;
import com.xavier.fast.dao.UserMapper;
import com.xavier.fast.entity.user.User;
import com.xavier.fast.entity.userFormid.UserFormid;
import com.xavier.fast.model.base.RopRequestBody;
import com.xavier.fast.model.base.RopResponse;
import com.xavier.fast.model.base.RopResponseBody;
import com.xavier.fast.model.user.login.RopInviteRequest;
import com.xavier.fast.model.user.login.RopUserFormRequest;
import com.xavier.fast.model.user.login.RopUserFormResponse;
import com.xavier.fast.model.user.login.RopUserResponse;
import com.xavier.fast.service.user.IUserService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @Description:    用户信息
* @Author:         Wang
* @CreateDate:     2019/7/17 17:23
* @UpdateUser:
* @UpdateDate:     2019/7/17 17:23
* @UpdateRemark:
* @Version:        1.0
*/
@Service
public class UserServiceImpl implements IUserService {

    private final static int NEED_INVITE_USERS = 5;

    @Resource
    private UserMapper userMapper;

    @Resource
    private UserFormidMapper userFormidMapper;

    /**
     * 获取徒弟信息列表
     * @param inviteRequest
     * @return
     */
    @ApiMethod(method = "api.pinke.user.info.getInviteUsers", version = "1.0.0")
    public RopResponse<RopUserResponse> getInviteUsers(RopRequestBody<RopInviteRequest> inviteRequest) {
        RopUserResponse userResponse = new RopUserResponse();
        String openId = inviteRequest.getT().getOpenId();
        //最多只查5个徒弟
        Map<String, Object> params = new HashMap<>();
        params.put("parentOpenid", openId);
        params.put("startRow", 0);
        params.put("endRow", 5);
        List<User> userList = userMapper.getUserListByParams(params);
        if(CollectionUtils.isEmpty(userList)){
            userResponse.setNeedPeoples(NEED_INVITE_USERS);
            userResponse.setDataList(null);
        }else{
            userResponse.setNeedPeoples(NEED_INVITE_USERS - userList.size());
            userResponse.setDataList(userList);
        }
        return RopResponse.createSuccessRep("", "查询成功", "1.0.0", userResponse);
    }

    @ApiMethod(method = "api.pinke.user.info.saveUserForm", version = "1.0.0")
    public RopResponse<RopUserFormResponse> saveUserForm(RopRequestBody<RopUserFormRequest> formRequest) {
        RopUserFormResponse response = new RopUserFormResponse();
        String openId = formRequest.getT().getOpenId();
        String formId = formRequest.getT().getFormId();
        UserFormid record = new UserFormid();
        record.setOpenid(openId);
        record.setFormid(formId);
        record.setCreateTime(new Date());
        record.setStatus(1);
        int count = userFormidMapper.insert(record);
        if(count <= 0){
            return RopResponse.createFailedRep("", "保存formId失败", "1.0.0");
        }
        response.setMsg("保存formId成功");
        return RopResponse.createSuccessRep("", "保存formId成功", "1.0.0", response);
    }


}
