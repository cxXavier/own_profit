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
     * @param bizType
     */
    @Async
    public void wechatPush(Order order, String bizType) {
        log.info("微信小程序开始推送...");
        if(order == null){
            log.error("微信推送失败，订单对象为空");
            return;
        }
        log.info("推送订单对象:" + JSON.toJSONString(order));
        if(StringUtils.isBlank(bizType)){
            log.error("微信推送失败，bizType为空");
            return;
        }
        if(StringUtils.isBlank(order.getPddOrderId())){
            log.error("微信推送失败，拼多多订单号为空");
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

        //推送给自己
        //1.获取formId
        String formid = this.getFormId(order.getOpenId());
        if(StringUtils.isBlank(formid)){
            log.error("微信推送失败，formid为空");
            return;
        }
        //2.获取价格
        double price = order.getOrderAmount().doubleValue() / 100;

        //3.拼多多订单号处理

        //4.推送
        if(TemplateMsg.BIZ_TYPE.CREATE_ORDER.name().equals(bizType)){
            this.pushMsg(order.getOpenId(), formid, String.valueOf(price), order.getPddOrderId(),
                    TemplateMsg.WECHAT_PUSH_TYPE.OWN_CREATE_ORDER.name());
        }else if(TemplateMsg.BIZ_TYPE.SETTLED.name().equals(bizType)){
            this.pushMsg(order.getOpenId(), formid, String.valueOf(price), order.getPddOrderId(),
                    TemplateMsg.WECHAT_PUSH_TYPE.OWN_SETTLED.name());
        }

        log.info("推送给自己，成功");

        //推送给师傅
        if(StringUtils.isNotBlank(order.getParentOpenId())){
            String parentFormid = this.getFormId(order.getParentOpenId());
            if(StringUtils.isBlank(parentFormid)){
                log.error("微信推送失败，parentFormid为空");
                return;
            }
            //2.获取花朵
            if(order.getContributionFlower() == null){
                log.error("微信推送失败，贡献花朵为空");
                return;
            }
            String flower = order.getContributionFlower().toString();
            //3.拼多多订单号处理

            //4.推送
            if(TemplateMsg.BIZ_TYPE.CREATE_ORDER.name().equals(bizType)){
                this.pushMsg(order.getParentOpenId(), parentFormid, flower, order.getPddOrderId(),
                        TemplateMsg.WECHAT_PUSH_TYPE.PRENTICE_CREATEORDER.name());
            }else if(TemplateMsg.BIZ_TYPE.SETTLED.name().equals(bizType)){
                this.pushMsg(order.getParentOpenId(), parentFormid, flower, order.getPddOrderId(),
                        TemplateMsg.WECHAT_PUSH_TYPE.PRENTICE_SETTLED.name());
            }
            log.info("推送给师傅，成功");
        }
        log.info("微信小程序推送结束...");
    }

    /**
     * 推送
     * @param openId
     * @param formid
     * @param price
     * @param pddOrderId
     * @param wechatPushType
     */
    private void pushMsg(String openId, String formid,
                           String price, String pddOrderId, String wechatPushType){
        try {
            WechatPushHandler.push(openId, formid, price, pddOrderId, wechatPushType);
            //推送成功，更新formid状态
            boolean flag = userFormidMapper.udate2Used(formid);
            if(flag){
                log.info("pushMsg 更新formid状态成功");
            }else{
                log.error("pushMsg 更新formid状态失败，openId=" +openId
                        + ",pddOrderId=" + pddOrderId + ",wechatPushType=" + wechatPushType);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("pushToOwn 微信小程序推送失败，errorMsg:" + e.getMessage());
        }
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
