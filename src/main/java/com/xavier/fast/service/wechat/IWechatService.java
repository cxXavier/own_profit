package com.xavier.fast.service.wechat;

public interface IWechatService {

    public Object transferPay(String token, Long amount,
                              String openId, Integer orderId,
                              String relUserName, String desc) throws Exception;
}
