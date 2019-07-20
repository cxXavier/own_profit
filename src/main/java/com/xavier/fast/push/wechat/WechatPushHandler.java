package com.xavier.fast.push.wechat;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.impl.WxMaServiceImpl;
import cn.binarywang.wx.miniapp.bean.WxMaTemplateData;
import cn.binarywang.wx.miniapp.bean.WxMaTemplateMessage;
import cn.binarywang.wx.miniapp.config.WxMaInMemoryConfig;
import com.alibaba.fastjson.JSON;
import com.xavier.fast.properties.WechatConfig;
import com.xavier.fast.push.wechat.template.TemplateMsg;
import me.chanjar.weixin.common.error.WxErrorException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
* @Description:    小程序推送
* @Author:         Wang
* @CreateDate:     2019/7/18 21:45
* @UpdateUser:
* @UpdateDate:     2019/7/18 21:45
* @UpdateRemark:
* @Version:        1.0
*/
public class WechatPushHandler {

    private final static Logger log = LoggerFactory.getLogger(WechatPushHandler.class);

    private final static WechatConfig CON = new WechatConfig();

    /**
     * 小程序推送
     * @param openId
     * @param formId
     * @param price
     * @param orderId
     * @param wechatPushType
     * @return
     * @throws Exception
     */
    public static String push(String openId, String formId, String price,
                              String orderId, String wechatPushType) throws Exception {
        log.info("小程序推送开始");

        if(StringUtils.isBlank(wechatPushType)){
            log.error("小程序推送失败，wechatPushType 为空");
            return "FAIL";
        }

        //创建推送实体
        TemplateMsg templateMsg = TemplateMsg.createMsgByType(openId, formId, price, orderId, wechatPushType);

        if(templateMsg == null){
            log.error("小程序推送失败，templateMsg 为空");
            return "FAIL";
        }

        log.info("小程序推送实体：" + JSON.toJSONString(templateMsg));

        if(!TemplateMsg.paramIsValidate(templateMsg)){
            log.error("小程序推送失败，参数不合法");
            return "FAIL";
        }

        //1、配置小程序信息
        WxMaInMemoryConfig wxConfig = new WxMaInMemoryConfig();
        wxConfig.setAppid(CON.getAppId());//小程序appid
        wxConfig.setSecret(CON.getAppSecret());//小程序AppSecret

        WxMaService wxMaService = new WxMaServiceImpl();
        wxMaService.setWxMaConfig(wxConfig);

        //2、设置推送消息
        WxMaTemplateMessage templateMessage = WxMaTemplateMessage.builder()
                .toUser(templateMsg.getOpenId())//要推送的用户openid
                .formId(templateMsg.getFormId())//收集到的formid
                .templateId(templateMsg.getTemplateId())//推送的模版id（在小程序后台设置）
                .data(templateMsg.getTemplateDataList())//模版信息
                .page(templateMsg.getPage())//要跳转到小程序那个页面
                .build();

        //3、发起推送
        wxMaService.getMsgService().sendTemplateMsg(templateMessage);

        return "SUCCESS";
    }
}
