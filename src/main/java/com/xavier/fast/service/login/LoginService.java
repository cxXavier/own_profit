package com.xavier.fast.service.login;

import com.xavier.fast.entity.user.UserDto;
import com.xavier.fast.entity.user.UserVo;

public interface LoginService {

    UserVo login(UserDto dto);

    void update(UserDto dto);

    int save(String openid,String formId);

    String getAvailiabFormID (String openid);

}
