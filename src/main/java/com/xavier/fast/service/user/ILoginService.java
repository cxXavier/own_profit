package com.xavier.fast.service.user;

import com.xavier.fast.entity.user.UserDto;
import com.xavier.fast.entity.user.UserVo;
import com.xavier.fast.model.base.RopRequestBody;
import com.xavier.fast.model.base.RopResponse;
import com.xavier.fast.model.user.login.RopLoginRequest;
import com.xavier.fast.model.user.login.RopLoginResponse;

public interface ILoginService {

    public RopResponse<RopLoginResponse> userLogin(RopRequestBody<RopLoginRequest> loginRequest);

    UserVo login(UserDto dto);

    void update(UserDto dto);

    int save(String openid,String formId);

    String getAvailiabFormID (String openid);

}