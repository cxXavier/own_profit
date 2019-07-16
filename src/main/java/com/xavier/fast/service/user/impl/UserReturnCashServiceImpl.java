package com.xavier.fast.service.user.impl;

import com.xavier.fast.annotation.ApiMethod;
import com.xavier.fast.dao.OrderMapper;
import com.xavier.fast.dao.UserFlowerMapper;
import com.xavier.fast.dao.UserMapper;
import com.xavier.fast.dao.UserReturnCashRecordMapper;
import com.xavier.fast.entity.order.Order;
import com.xavier.fast.entity.user.User;
import com.xavier.fast.entity.user.UserFlower;
import com.xavier.fast.entity.user.UserReturnCashRecord;
import com.xavier.fast.model.base.RopRequestBody;
import com.xavier.fast.model.base.RopResponse;
import com.xavier.fast.model.user.cash.RopReturnCashRequest;
import com.xavier.fast.model.user.cash.RopReturnCashResponse;
import com.xavier.fast.properties.WechatConfig;
import com.xavier.fast.service.user.IUserReturnCashService;
import com.xavier.fast.utils.*;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @Description:    用户提现
* @Author:         Wang
* @CreateDate:     2019/7/3 22:14
* @UpdateUser:
* @UpdateDate:     2019/7/3 22:14
* @UpdateRemark:
* @Version:        1.0
*/
@Service
public class UserReturnCashServiceImpl implements IUserReturnCashService {

    private Logger log = LoggerFactory.getLogger(UserReturnCashServiceImpl.class);

    @Resource
    private UserMapper userMapper;

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private UserFlowerMapper userFlowerMapper;

    @Resource
    private UserReturnCashRecordMapper userReturnCashRecordMapper;

    /**
     * 提现
     * @author      Wang
     * @param       cashRequest
     * @return
     * @exception
     * @date        2019/7/3 22:23
     */
    @ApiMethod(method = "api.pinke.user.cash.userReturnCash", version = "1.0.0")
    public RopResponse<RopReturnCashResponse> userReturnCash(RopRequestBody<RopReturnCashRequest> cashRequest) {
        String openId = cashRequest.getT().getOpenId();
        Integer orderId = cashRequest.getT().getOrderId();

        log.info("userReturnCash params:openId=" + openId + ",orderId=" + orderId);

        //查询用户
        User user = userMapper.getUserByOpenid(openId);
        if(user == null){
            return RopResponse.createFailedRep("", "当前用户不存在", "1.0.0");
        }

        //查询当前用户的订单
        Order order = orderMapper.selectByPrimaryKey(orderId);
        if(order == null){
            return RopResponse.createFailedRep("", "当前用户暂无可提现订单", "1.0.0");
        }
        if(order.getCashBackStatus() == 1){
            return RopResponse.createFailedRep("", "当前订单已经提现，请勿重复操作", "1.0.0");
        }

        //查询鲜花开支明细
        UserFlower paramFlower = new UserFlower();
        paramFlower.setOpenId(openId);
        paramFlower.setParentOpenId(openId);
        List<UserFlower> flowerList = userFlowerMapper.findListByOpendIdOrParentId(paramFlower);
        if(CollectionUtils.isEmpty(flowerList)){
            return RopResponse.createFailedRep("", "暂无可用鲜花", "1.0.0");
        }
        //计算鲜花余额
        int residueFlowers = CalFlowerUtils.calTotalFlowers(flowerList);
        //提现需要鲜花数4倍
        int needFlowers = order.getContributionFlower() * 4;
        if(residueFlowers < needFlowers){
            return RopResponse.createFailedRep("",
                    "当前可用鲜花不足，可用鲜花：" + residueFlowers + "朵，还需要鲜花："
                    + (needFlowers - residueFlowers) + "朵", "1.0.0");
        }

        //满足提现要求
        // 微信转账
        Map<String, String> restMap = null;
        try {
            restMap = transfers(openId, orderId, user.getNickname(), order.getOrderAmount());
        } catch (Exception e) {
            e.printStackTrace();
            return RopResponse.createFailedRep("", e.getMessage(), "1.0.0");
        }

        if(MapUtils.isEmpty(restMap)){
            return RopResponse.createFailedRep("", "转账失败", "1.0.0");
        }
        if("FAIL".equals(restMap.get("return_code"))){
            return RopResponse.createFailedRep("", "转账失败，失败原因"
                    + restMap.get("return_msg"), "1.0.0");
        }
        if(restMap.get("return_code").equals("SUCCESS")){
            if("FAIL".equals(restMap.get("result_code"))){
                return RopResponse.createFailedRep("", "转账失败，失败原因"
                        + restMap.get("err_code_des"), "1.0.0");
            }
            if("SUCCESS".equals(restMap.get("result_code"))){
                // 商户转账订单号
                String tradeNo = restMap.get("partner_trade_no");
                // 微信订单号
                String paymentNO = restMap.get("payment_no");
                // 微信支付成功时间
                String paymentTime = restMap.get("payment_time");

                //更新订单提现状态
                Map<String, Object> params = new HashMap<>();
                params.put("id", orderId);
                params.put("cashBackVersion", order.getCashBackVersion());
                int updateCount = orderMapper.updateOrderCashBackStatus(params);
                if(updateCount <= 0){
                    //TODO 更新订单失败-补偿
                    log.info("更新订单现状态失败");
                }else{
                    //添加鲜花收支记录
                    addFlowerRecord(order);
                    //添加提现记录
                    UserReturnCashRecord record = new UserReturnCashRecord();
                    record.setOpenId(openId);
                    record.setOrderId(orderId);
                    record.setCashBackAmount(order.getOrderAmount().intValue());
                    record.setCashBackStatus(1);
                    record.setWechatPaymentStatus("1");
                    record.setWechatPaymentNo(paymentNO);
                    try {
                        record.setWechatPaymentTime(
                                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(paymentTime));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    record.setCreateTime(new Date());
                    int insertCount = userReturnCashRecordMapper.insert(record);
                }
            }
        }

        RopReturnCashResponse response = new RopReturnCashResponse();

        return RopResponse.createSuccessRep("", "提现成功", "1.0.0", response);
    }

    /**
    * 微信转账
    * @author      Wang
    * @param       openId
    * @param       orderId
    * @param       relName
    * @param       amount
    * @return
    * @exception
    * @date        2019/7/3 22:44
    */
    private Map<String, String> transfers(String openId, Integer orderId, String relName,
                                          Long amount) throws Exception{
        Map<String, Object> parm = new HashMap<String, Object>();
        WechatConfig wechatConfig = new WechatConfig();
        parm.put("mch_appid", wechatConfig.getAppId()); // 公众账号appid
        parm.put("mchid", wechatConfig.getMchId()); // 商户号
        parm.put("nonce_str", WechatUtils.getRandomString(32)); // 随机字符串
        parm.put("partner_trade_no", orderId); // 生成商户订单号
        parm.put("openid", openId); // 用户openid
        parm.put("check_name", "NO_CHECK"); // 是否验证真实姓名--校验用户姓名选项 OPTION_CHECK
        parm.put("re_user_name", relName); //收款用户姓名---check_name设置为FORCE_CHECK或OPTION_CHECK，则必填
        parm.put("amount", amount); // 转账金额
        parm.put("desc", wechatConfig.getPayDesc()); // 企业付款描述信息
        parm.put("spbill_create_ip", WechatUtils.getLocalIP()); // Ip地址
        parm.put("sign", SignUtils.createSign("UTF-8", parm));

        String restXml = ClientCustomSSL.doRefund(wechatConfig.getPayPersonUrl(), XmlUtils.getRequestXml(parm));
        Map<String, String> restMap = XmlUtils.xmlToMap(restXml);
        return restMap;
    }

    /**
     * 添加鲜花收支记录
     * @param order
     * @return
     */
    private int addFlowerRecord(Order order){
        UserFlower uf = new UserFlower();
        uf.setOpenId(order.getOpenId());
        uf.setUnioinId(order.getUnionId());
        uf.setParentOpenId(order.getParentOpenId());
        uf.setParentUnionId(order.getParentUnionId());
        uf.setFlowers(order.getContributionFlower());
        uf.setCostType(UserFlower.COST_TYPE.DECREASE.name());
        uf.setCreateTime(new Date());
        int count = userFlowerMapper.insertSelective(uf);
        return count;
    }

}
