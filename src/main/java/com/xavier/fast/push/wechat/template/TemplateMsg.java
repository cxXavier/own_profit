package com.xavier.fast.push.wechat.template;

import cn.binarywang.wx.miniapp.bean.WxMaTemplateData;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
* @Description:    小程序推送消息实体
* @Author:         Wang
* @CreateDate:     2019/7/18 21:49
* @UpdateUser:
* @UpdateDate:     2019/7/18 21:49
* @UpdateRemark:
* @Version:        1.0
*/
public class TemplateMsg implements Serializable {

    private static final long serialVersionUID = -3473514793024067241L;

    private static final String OWN_TEMPLATE_ID = "8RU6gLXNR40GSsKPOaf6PwEWL-lwvNLe06IafeOmZTA";

    private static final String PRENTICE_TEMPLATE_ID = "8RU6gLXNR40GSsKPOaf6P4gtsf7g6l5BduRnPf3EJTE";

    private static final String OWN_CREATE_ORDER_REMARK = "（备注：待结算）";

    private static final String OWN_SETTLED_REMARK = "（备注：已结算）";

    private static final String PRENTICE_CREATE_ORDER_REMARK = "（备注：待结算）";

    private static final String PRENTICE_SETTLED_REMARK = "（备注：已生效）";

    /**
     * 用户openid
     */
    private String openId;

    /**
     * 模版id
     */
    private String templateId;

    /**
     * 默认跳到小程序首页
     */
    private String page = "pages/home/home";

    /**
     * 收集到的用户formId
     */
    private String formId;

    /**
     * 推送内容
     */
    private List<WxMaTemplateData> templateDataList;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getFormId() {
        return formId;
    }

    public void setFormId(String formId) {
        this.formId = formId;
    }

    public List<WxMaTemplateData> getTemplateDataList() {
        return templateDataList;
    }

    public void setTemplateDataList(List<WxMaTemplateData> templateDataList) {
        this.templateDataList = templateDataList;
    }

    /**
     * 推送类型
     */
    public static enum WECHAT_PUSH_TYPE{
        OWN_CREATE_ORDER,
        OWN_SETTLED,
        PRENTICE_CREATEORDER,
        PRENTICE_SETTLED;
    }

    /**
     * 收益类型
     */
    public static enum EARNINGS_TYPE{
        own("自购订单"),
        prentice("徒弟订单");

        private String cnName;

        private EARNINGS_TYPE(String cnName){
            this.cnName = cnName;
        }
    }

    /**
     * 推送入口
     * @param openId
     * @param formId
     * @param price
     * @param orderId
     * @param wechatPushType
     * @return
     */
    public static TemplateMsg createMsgByType(String openId, String formId,
                                              String price, String orderId, String wechatPushType){
        if(WECHAT_PUSH_TYPE.OWN_CREATE_ORDER.name().equals(wechatPushType)){
            return createOwnCreateOrderMsg(openId, formId, price, orderId);
        }else if(WECHAT_PUSH_TYPE.OWN_SETTLED.name().equals(wechatPushType)){
            return createOwnSettledMsg(openId, formId, price, orderId);
        }else if(WECHAT_PUSH_TYPE.PRENTICE_CREATEORDER.name().equals(wechatPushType)){
            return createPrenticeCreateOrderMsg(openId, formId, price, orderId);
        }else if(WECHAT_PUSH_TYPE.PRENTICE_SETTLED.name().equals(wechatPushType)){
            return createPrenticeSettledMsg(openId, formId, price, orderId);
        }
        return null;
    }

    /**
     * 自购刚下单
     * @param openId
     * @param formId
     * @param price
     * @param orderId
     * @return
     */
    private static TemplateMsg createOwnCreateOrderMsg(
            String openId, String formId, String price, String orderId){
        return createOwnMsg(openId, formId, price, orderId, OWN_CREATE_ORDER_REMARK);
    }

    /**
     * 自购已结算（25号）
     * @param openId
     * @param formId
     * @param price
     * @param orderId
     * @return
     */
    private static TemplateMsg createOwnSettledMsg(
            String openId, String formId, String price, String orderId){
        return createOwnMsg(openId, formId, price, orderId, OWN_SETTLED_REMARK);
    }

    /**
     * 徒弟刚下单
     * @param openId
     * @param formId
     * @param price
     * @param orderId
     * @return
     */
    private static TemplateMsg createPrenticeCreateOrderMsg(
            String openId, String formId, String price, String orderId){
        return createPrenticeMsg(openId, formId, price, orderId, PRENTICE_CREATE_ORDER_REMARK);
    }

    /**
     * 徒弟已结算（25号）
     * @param openId
     * @param formId
     * @param price
     * @param orderId
     * @return
     */
    private static TemplateMsg createPrenticeSettledMsg(
            String openId, String formId, String price, String orderId){
        return createPrenticeMsg(openId, formId, price, orderId, PRENTICE_SETTLED_REMARK);
    }

    /**
     * 自购-推送
     * @param openId
     * @param formId
     * @param price
     * @param orderId
     * @param remark
     * @return
     */
    private static TemplateMsg createOwnMsg(
            String openId, String formId, String price, String orderId, String remark){
        TemplateMsg templateMsg = new TemplateMsg();
        templateMsg.setOpenId(openId);
        templateMsg.setTemplateId(OWN_TEMPLATE_ID);
        templateMsg.setFormId(formId);
        List<WxMaTemplateData> templateDataList = new ArrayList<>();
        WxMaTemplateData data1 = new WxMaTemplateData("keyword1", EARNINGS_TYPE.own.cnName);
        WxMaTemplateData data2 = new WxMaTemplateData("keyword2", price + "元" + remark);
        WxMaTemplateData data3 = new WxMaTemplateData("keyword3", orderId);
        templateDataList.add(data1);
        templateDataList.add(data2);
        templateDataList.add(data3);
        templateMsg.setTemplateDataList(templateDataList);
        return templateMsg;
    }


    /**
     * 徒弟-推送
     * @param openId
     * @param formId
     * @param price
     * @param orderId
     * @param remark
     * @return
     */
    private static TemplateMsg createPrenticeMsg(
            String openId, String formId, String price, String orderId, String remark){
        TemplateMsg templateMsg = new TemplateMsg();
        templateMsg.setOpenId(openId);
        templateMsg.setTemplateId(PRENTICE_TEMPLATE_ID);
        templateMsg.setFormId(formId);
        List<WxMaTemplateData> templateDataList = new ArrayList<>();
        WxMaTemplateData data1 = new WxMaTemplateData("keyword1", EARNINGS_TYPE.prentice.cnName);
        WxMaTemplateData data2 = new WxMaTemplateData("keyword2", price + "朵鲜花" + remark);
        WxMaTemplateData data3 = new WxMaTemplateData("keyword3", orderId);
        templateDataList.add(data1);
        templateDataList.add(data2);
        templateDataList.add(data3);
        templateMsg.setTemplateDataList(templateDataList);
        return templateMsg;
    }

    /**
     * 参数校验
     * @param templateMsg
     * @return
     */
    public static boolean paramIsValidate(TemplateMsg templateMsg){
        if(templateMsg == null){
            return false;
        }
        if(StringUtils.isBlank(templateMsg.getOpenId())){
            return false;
        }
        if(StringUtils.isBlank(templateMsg.getTemplateId())){
            return false;
        }
        if(StringUtils.isBlank(templateMsg.getFormId())){
            return false;
        }
        if(CollectionUtils.isEmpty(templateMsg.getTemplateDataList())){
            return false;
        }
        if(templateMsg.getTemplateDataList().size() < 3){
            return false;
        }
        return true;
    }

}
