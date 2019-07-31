package com.xavier.fast.service.push;

import com.xavier.fast.entity.order.Order;

/**
* @Description:    推送
* @Author:         Wang
* @CreateDate:     2019/7/19 10:30
* @UpdateUser:
* @UpdateDate:     2019/7/19 10:30
* @UpdateRemark:
* @Version:        1.0
*/
public interface IPushService {

    /**
     * 微信小程序推送（异步）
     * @param order
     * @param bizType
     */
    public void wechatPush(Order order, String bizType);
}
