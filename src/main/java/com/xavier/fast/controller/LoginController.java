package com.xavier.fast.controller;

import com.xavier.fast.entity.user.UserDto;
import com.xavier.fast.entity.user.UserVo;
import com.xavier.fast.service.login.LoginService;
import com.xavier.fast.utils.ResultModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/index")
public class LoginController {

    @Autowired
    private LoginService loginService;

    /**
     * 用户登录
     * @param dto
     * @return
     */
    @RequestMapping("/wxlogin")
    public ResultModel<UserVo> login(UserDto dto){
        ResultModel<UserVo> result = new ResultModel<>();
        result.setReturnValue(loginService.login(dto));
        return result;
    }

    /**
     * 更新用户信息
     * @param dto
     * @return
     */
    @RequestMapping("/update")
    public ResultModel update(UserDto dto){
        loginService.update(dto);
        return new ResultModel();
    }

}
