package com.xavier.fast.service.wechat.impl;

import com.xavier.fast.properties.WechatConfig;
import com.xavier.fast.service.wechat.IWechatService;
import com.xavier.fast.utils.ClientCustomSSL;
import com.xavier.fast.utils.SignUtils;
import com.xavier.fast.utils.WechatUtils;
import com.xavier.fast.utils.XmlUtils;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.Map;

public class WechatServiceImpl implements IWechatService {

    @Override
    public Object transferPay(String token, Long amount,
                              String openId, Integer orderId,
                              String relUserName, String desc) throws Exception {
        Map<Object, Object> map = new HashMap<>();
        Map<String, String> restMap = null;
        try {
            Map<String, Object> parm = new HashMap<String, Object>();
            WechatConfig wechatConfig = new WechatConfig();
            parm.put("mch_appid", wechatConfig.getAppId()); // 公众账号appid
            parm.put("mchid", wechatConfig.getMchId()); // 商户号
            parm.put("nonce_str", WechatUtils.getRandomString(32)); // 随机字符串
            parm.put("partner_trade_no", orderId); // 生成商户订单号
            parm.put("openid", openId); // 用户openid
            parm.put("check_name", "NO_CHECK"); // 是否验证真实姓名--校验用户姓名选项 OPTION_CHECK
            parm.put("re_user_name", relUserName); //收款用户姓名---check_name设置为FORCE_CHECK或OPTION_CHECK，则必填
            parm.put("amount", amount); // 转账金额
            parm.put("desc", desc); // 企业付款描述信息
            System.out.println(WechatUtils.getLocalIP());
            parm.put("spbill_create_ip", WechatUtils.getLocalIP()); // Ip地址
            parm.put("sign", SignUtils.createSign("UTF-8", parm));

            String restxml = ClientCustomSSL.doRefund(wechatConfig.getPayPersonUrl(), XmlUtils.getRequestXml(parm));
            restMap = XmlUtils.xmlToMap(restxml);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (!CollectionUtils.isEmpty(restMap) && "SUCCESS".equals(restMap.get("result_code"))) {
            System.out.println("转账成功：" + restMap.get("err_code") + ":" + restMap.get("err_code_des"));
            Map<String, String> transferMap = new HashMap<>();
            transferMap.put("partner_trade_no", restMap.get("partner_trade_no"));// 商户转账订单号
            transferMap.put("payment_no", restMap.get("payment_no")); // 微信订单号
            transferMap.put("payment_time", restMap.get("payment_time")); // 微信支付成功时间
            return transferMap;
        } else {
            if (CollectionUtils.isEmpty(restMap)) {
                System.out.println("转账失败：" + restMap.get("err_code") + ":" + restMap.get("err_code_des"));
            }
        }
        map.put("code", 200);
        map.put("msg", "提现成功！");
        return map;
    }
}
