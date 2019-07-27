package com.xavier.fast.service.user;

import com.xavier.fast.entity.user.UserDto;
import com.xavier.fast.entity.user.UserVo;
import com.xavier.fast.model.base.RopRequestBody;
import com.xavier.fast.model.base.RopResponse;
import com.xavier.fast.model.user.login.RopLoginRequest;
import com.xavier.fast.model.user.login.RopLoginResponse;
import com.xavier.fast.model.user.login.RopUserRequest;

public interface ILoginService {

    public RopResponse<RopLoginResponse> userLogin(RopRequestBody<RopLoginRequest> loginRequest);

    public RopResponse<String> updateUser(RopRequestBody<RopUserRequest> loginRequest);

    public RopResponse<String> bindRelation(RopRequestBody<RopLoginRequest> loginRequest);

}
