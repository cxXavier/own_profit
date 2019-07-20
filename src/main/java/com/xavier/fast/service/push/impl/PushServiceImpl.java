package com.xavier.fast.service.push.impl;

import com.alibaba.fastjson.JSON;
import com.xavier.fast.dao.UserFormidMapper;
import com.xavier.fast.entity.order.Order;
import com.xavier.fast.entity.userFormid.UserFormid;
import com.xavier.fast.push.wechat.WechatPushHandler;
import com.xavier.fast.push.wechat.template.TemplateMsg;
import com.xavier.fast.service.push.IPushService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @Description:    推送
* @Author:         Wang
* @CreateDate:     2019/7/19 10:31
* @UpdateUser:
* @UpdateDate:     2019/7/19 10:31
* @UpdateRemark:
* @Version:        1.0
*/
@Service
public class PushServiceImpl implements IPushService {

    private final static Logger log = LoggerFactory.getLogger(PushServiceImpl.class);

    @Resource
    private UserFormidMapper userFormidMapper;

    /**
     * 微信小程序推送（异步）
     * @param order
     */
    @Async
    public void wechatPush(Order order, String wechatPushType) {
        log.info("微信小程序开始推送...");
        if(order == null){
            log.error("微信推送失败，订单对象为空");
            return;
        }
        if(StringUtils.isBlank(wechatPushType)){
            log.error("微信推送失败，推送类型为空");
            return;
        }
        log.info("推送订单对象:" + JSON.toJSONString(order));
        log.info("推送类型:" + wechatPushType);
        if(order.getId() == null){
            log.error("微信推送失败，订单号为空");
            return;
        }
        if(StringUtils.isBlank(order.getOpenId())){
            log.error("微信推送失败，OpenId为空");
            return;
        }
        if(order.getOrderAmount() == null){
            log.error("微信推送失败，订单金额为空");
            return;
        }

        String formid = this.getFormId(order.getOpenId());
        if(StringUtils.isBlank(formid)){
            log.error("微信推送失败，formid为空");
            return;
        }

        double price = order.getOrderAmount().doubleValue() / 100;

        try {
            WechatPushHandler.push(order.getOpenId(), formid,
                    String.valueOf(price), order.getId().toString(), wechatPushType);
            //推送成功，更新formid状态
            boolean flag = userFormidMapper.udate2Used(formid);
            if(flag){
                log.info("更新formid状态成功");
            }else{
                log.error("更新formid状态失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("微信小程序推送失败，errorMsg:" + e.getMessage());
        }

        log.info("微信小程序推送结束...");
    }

    /**
     * 获取formId
     * @param openId
     * @return
     */
    private String getFormId(String openId){
        UserFormid userForm = userFormidMapper.getLatestUsefulInfo(openId);
        if(userForm == null){
            log.error("暂无userForm信息，openId=" + openId);
            return null;
        }
        if(StringUtils.isBlank(userForm.getFormid())){
            log.error("暂无Formid，openId=" + openId);
            return null;
        }
        return userForm.getFormid();
    }

}
