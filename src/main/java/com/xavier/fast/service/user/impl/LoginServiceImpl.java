package com.xavier.fast.service.user.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xavier.fast.annotation.ApiMethod;
import com.xavier.fast.constants.WeChatConstants;
import com.xavier.fast.dao.UserFormidMapper;
import com.xavier.fast.dao.UserMapper;
import com.xavier.fast.entity.user.User;
import com.xavier.fast.entity.user.UserDto;
import com.xavier.fast.entity.user.UserVo;
import com.xavier.fast.entity.user.WechatLoginReturn;
import com.xavier.fast.entity.userFormid.UserFormid;
import com.xavier.fast.model.base.RopRequestBody;
import com.xavier.fast.model.base.RopResponse;
import com.xavier.fast.model.user.login.RopLoginRequest;
import com.xavier.fast.model.user.login.RopLoginResponse;
import com.xavier.fast.model.user.login.RopUserRequest;
import com.xavier.fast.properties.WechatConfig;
import com.xavier.fast.service.user.ILoginService;
import com.xavier.fast.utils.DateUtil;
import com.xavier.fast.utils.InviteCodeUtils;
import com.xavier.fast.utils.OkHttpUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LoginServiceImpl implements ILoginService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private WechatConfig wechatConfig;

    @Resource
    private UserFormidMapper userFormidMapper;

    @ApiMethod(method = "api.pinke.user.login.userLogin", version = "1.0.0")
    public RopResponse<RopLoginResponse> userLogin(RopRequestBody<RopLoginRequest> loginRequest) {
        RopLoginResponse response = new RopLoginResponse();
        UserDto dto = new UserDto();
        BeanUtils.copyProperties(loginRequest.getT(), dto);
        WechatLoginReturn loginObj = wechartLogin(dto.getCode());
        if(null == loginObj.getOpenid()){
            return RopResponse.createFailedRep("", "openId为空", "1.0.0");
        }
        UserVo vo = new UserVo();
        vo.setOpenid(loginObj.getOpenid());
        vo.setUnionid(loginObj.getUnioinid());
        vo.setSessionId(loginObj.getSessionkey());
        //验证是否为新用户
        User info = userMapper.getUserByOpenid(vo.getOpenid());
        if(null == info){
            vo.setNewUser(true);
            info = new User();
            //mapper.map(dto,info);
            info.setOpenid(vo.getOpenid());
            info.setUnionid(vo.getUnionid());
            //有邀请码时需要保留关联关系
            if(StringUtils.isNotBlank(dto.getInviteCode())) {
                User parentUser = userMapper.getUserByInviteCode(dto.getInviteCode());
                if(null != parentUser){
                    info.setParentOpenid(parentUser.getOpenid());
                    info.setParentUnionid(parentUser.getUnionid());
                    info.setGrandparentOpenid(parentUser.getParentOpenid());
                    info.setGrandparentUnionid(parentUser.getGrandparentUnionid());
                }
            }
            userMapper.insertSelective(info);
            //生成邀请码
            String inviteCode = InviteCodeUtils.generate(info.getId());
            info.setInviteCode(inviteCode);
        } else {
            userMapper.updateByPrimaryKeySelective(info);
        }
        //保存用户session信息
        //updater.update(RedisConstants.User.getSessionId(vo.getOpenid(),vo.getSessionId()),"",RedisConstants.User.SESSION_TIME);
        vo.setInviteCode(info.getInviteCode());
        vo.setMobile(info.getMobile());
        vo.setuId(info.getId().intValue());
        response.setUserInfo(vo);
        return RopResponse.createSuccessRep("登陆成功", "登陆成功", "1.0.0", response);
    }

    @ApiMethod(method = "api.pinke.user.login.updateUser", version = "1.0.0")
    public RopResponse<String> updateUser(RopRequestBody<RopUserRequest> loginRequest) {
        User info = new User();
        info.setId(loginRequest.getT().getuId().longValue());
        info.setAvatar(loginRequest.getT().getAvatar());
        info.setNickname(loginRequest.getT().getNickname());
        info.setGender(loginRequest.getT().getGender());
        info.setMobile(loginRequest.getT().getMobile());
        int updateCount = userMapper.updateByPrimaryKeySelective(info);
        if(updateCount <= 0){
            return RopResponse.createFailedRep("", "修改用户信息失败", "1.0.0");
        }
        return RopResponse.createSuccessRep("", "修改用户信息成功", "1.0.0", "");
    }

    @Override
    public UserVo login(UserDto dto) {
        UserVo vo = new UserVo();
        WechatLoginReturn loginObj = wechartLogin(dto.getCode()) ;
        if(null == loginObj.getOpenid()){
            return vo;
        }
        vo.setOpenid(loginObj.getOpenid());
        vo.setUnionid(loginObj.getUnioinid());
        vo.setSessionId(loginObj.getSessionkey());
        //验证是否为新用户
        User info = userMapper.getUserByOpenid(vo.getOpenid());
        if(null == info){
            vo.setNewUser(true);
            info = new User();
            //mapper.map(dto,info);
            info.setOpenid(vo.getOpenid());
            info.setUnionid(vo.getUnionid());
            //有邀请码时需要保留关联关系
            if(StringUtils.isNotBlank(dto.getInviteCode())) {
                User parentUser = userMapper.getUserByInviteCode(dto.getInviteCode());
                if(null != parentUser){
                    info.setParentOpenid(parentUser.getOpenid());
                    info.setParentUnionid(parentUser.getUnionid());
                    info.setGrandparentOpenid(parentUser.getParentOpenid());
                    info.setGrandparentUnionid(parentUser.getGrandparentUnionid());
                }
            }
            userMapper.insertSelective(info);
            //生成邀请码
            String inviteCode = InviteCodeUtils.generate(info.getId());
            info.setInviteCode(inviteCode);
        } else {
            userMapper.updateByPrimaryKeySelective(info);
        }
        //保存用户session信息
        //updater.update(RedisConstants.User.getSessionId(vo.getOpenid(),vo.getSessionId()),"",RedisConstants.User.SESSION_TIME);
        vo.setInviteCode(info.getInviteCode());
        vo.setMobile(info.getMobile());
        return vo;
    }

    @Override
    public void update(UserDto userdto) {
        User info = userMapper.getUserByOpenid(userdto.getOpenid());
        //mapper.map(userdto, info);  // null值不会复制,见com.bxm.shop.config.DozerBeanMapperConfig.mapper
        //updater.hupdate(RedisConstants.User.getUserInfo(),info.getOpenid(),info, userExpireSec());
    }


    @Override
    public int save(String openid, String formId) {
        return  userFormidMapper.save(openid,formId);
    }

    @Override
    public String getAvailiabFormID(String openid) {
        String formid="";
        List<UserFormid> formIdInfoDaoList = userFormidMapper.getFormIdList(openid);
        if(formIdInfoDaoList.size()<=0){
            return  "";
        }
        for (UserFormid formIdInfo : formIdInfoDaoList) {
            if(compareTimes(formIdInfo.getCreateTime())){
                formid=formIdInfo.getFormid();
                //formIdInfoMapper.udate2Used(formIdInfo.getId());//更改状态为已使用
                break;
            }else{
                formid = formIdInfo.getFormid();
                userFormidMapper.update2TimeLimit(formid);//更改formid状态为已过期
                continue;
            }
        }
        return formid;
    }

    /**
     * formid是否过期 （过期时间为7天）
     * @param startTime
     * @return
     */
    private boolean compareTimes(Date startTime){
        Date endTime = new Date();
        long distanceDays = DateUtil.getBetweenDays(startTime,endTime);
        if(distanceDays<=7){
            return true;
        }else{
            return false;
        }
    }















    /**
     * 微信登录
     * 通过返回对象的openid判断是否登录成功
     * @param code
     * @return
     */
    private WechatLoginReturn wechartLogin(String code){
        Map<String,String> params = new HashMap<>();
        params.put(WeChatConstants.Param.APP_ID,wechatConfig.getAppId());
        params.put(WeChatConstants.Param.SECRET,wechatConfig.getAppSecret());
        params.put(WeChatConstants.Param.JS_CDOE,code);
        params.put(WeChatConstants.Param.GRANT_TYPE,WeChatConstants.Param.AUTH_CODE);
        String response = null;
        try {
            response = OkHttpUtils.post(wechatConfig.getJscode2sessionUrl(),params);
        } catch (IOException e) {
            e.printStackTrace();
        }
        WechatLoginReturn wechartLogin = new WechatLoginReturn();
        if(null != response) {
            JSONObject obj = JSON.parseObject(response);
            if(null != obj && obj.getString(WeChatConstants.Param.OPENID) != null){
                wechartLogin.setOpenid(obj.getString(WeChatConstants.Param.OPENID));
                wechartLogin.setUnioinid(obj.getString(WeChatConstants.Param.UNIONID));
                wechartLogin.setSessionkey(obj.getString(WeChatConstants.Param.SESSION_KEY));
            }
        }
        return wechartLogin;
    }

}
