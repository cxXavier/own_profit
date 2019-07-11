package com.xavier.fast.service.user.impl;

import com.xavier.fast.annotation.ApiMethod;
import com.xavier.fast.dao.OrderMapper;
import com.xavier.fast.dao.UserFlowerMapper;
import com.xavier.fast.dao.UserMapper;
import com.xavier.fast.dao.UserReturnCashRecordMapper;
import com.xavier.fast.entity.order.MyOrder;
import com.xavier.fast.entity.order.Order;
import com.xavier.fast.entity.order.PrenticeOrder;
import com.xavier.fast.entity.pdd.OrderVo;
import com.xavier.fast.entity.user.User;
import com.xavier.fast.entity.user.UserFlower;
import com.xavier.fast.entity.user.UserReturnCashRecord;
import com.xavier.fast.model.base.RopRequestBody;
import com.xavier.fast.model.base.RopResponse;
import com.xavier.fast.model.base.RopResponseBody;
import com.xavier.fast.model.user.order.*;
import com.xavier.fast.service.pdd.IpddService;
import com.xavier.fast.service.user.IOrderService;
import com.xavier.fast.utils.CalFlowerUtils;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
* @Description:    订单服务
* @Author:         Wang
* @CreateDate:     2019/7/3 10:30
* @UpdateUser:
* @UpdateDate:     2019/7/3 10:30
* @UpdateRemark:
* @Version:        1.0
*/
@Service
public class OrderServiceImpl extends BaseServiceImpl implements IOrderService {

    private Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Resource
    private OrderMapper orderMapper;

    @Autowired
    private IpddService pddService;

    @Resource
    private UserMapper userMapper;

    @Resource
    private UserFlowerMapper userFlowerMapper;

    @Resource
    private UserReturnCashRecordMapper userReturnCashRecordMapper;

    /**
    * 创建虚拟订单
    * @author      Wang
    * @param       createOrderRequest
    * @return
    * @exception
    * @date        2019/7/10 10:52
    */
    @ApiMethod(method = "api.pinke.user.order.createOrder", version = "1.0.0")
    public RopResponse<OrderVo> createOrder(RopRequestBody<RopCreateOrderRequest> createOrderRequest) {
        String openId = createOrderRequest.getT().getOpenId();
        String goodsId = createOrderRequest.getT().getGoodsId();
        Long priceAfterCoupon = createOrderRequest.getT().getPriceAfterCoupon();
        log.info("createOrder params:" + createOrderRequest.getT().toString());
        Date currentDate = new Date();
        //保存虚拟订单
        Order record = new Order();
        record.setOpenId(openId);
        record.setGoodsId(goodsId);
        record.setDuoCouponAmount(priceAfterCoupon);
        record.setCreateTime(currentDate);
        int count = orderMapper.insertSelective(record);
        if(count <= 0){
            log.error("保存虚拟订单失败，参数：" + record.toString());
        }
        log.info("保存虚拟订单成功");

        //openId+goodsId+订单号
        String customParam = record.getId().toString();
        //去拼多多调起支付页面
        OrderVo order = pddService.queryGoodsShareUrl(goodsId, openId, customParam);
        if(order == null){
            return RopResponse.createFailedRep("", "创建拼多多订单失败", "1.0.0");
        }
        return RopResponse.createSuccessRep("", "创建订单成功", "1.0.0", order);
    }

    /**
     * 获取徒弟订单列表
     * @author      Wang
     * @param       orderListRequest
     * @return
     * @exception
     * @date        2019/7/10 10:52
     */
    @ApiMethod(method = "api.pinke.user.order.getPrenticeOrderList", version = "1.0.0")
    public RopResponse<RopResponseBody> getPrenticeOrderList(RopRequestBody<RopOrderListQueryRequest> orderListRequest) {
        RopResponseBody responseBody = new RopResponseBody();
        String openId = orderListRequest.getT().getOpenId();
        String queryType = orderListRequest.getT().getQueryType();
        String orderStatus = getOrderStatus(queryType);//默认查询全部订单
        Order order = new Order();
        order.setParentOpenId(openId);
        order.setOrderStatus(orderStatus);
        List<Order> orderList = orderMapper.findOrderList(order);
        List<PrenticeOrder> prenticeOrders = getPrenticeOrder(orderList);

        if(CollectionUtils.isEmpty(prenticeOrders)){
            return RopResponse.createSuccessRep("", "暂无订单", "1.0.0", null);
        }
        responseBody.setDataList(prenticeOrders);
        return RopResponse.createSuccessRep("", "获取订单成功", "1.0.0", responseBody);
    }

    /**
    * 我的订单列表
    * @author      Wang
    * @param       orderListRequest
    * @return
    * @exception
    * @date        2019/7/3 10:35
    */
    @ApiMethod(method = "api.pinke.user.order.getOrderList", version = "1.0.0")
    public RopResponse<RopResponseBody> getOrderList(RopRequestBody<RopOrderListQueryRequest> orderListRequest) {
        RopResponseBody responseBody = new RopResponseBody();
        String openId = orderListRequest.getT().getOpenId();
        String queryType = orderListRequest.getT().getQueryType();
        String orderStatus = getOrderStatus(queryType);//默认查询全部订单
        Order order = new Order();
        order.setOpenId(openId);
        order.setOrderStatus(orderStatus);
        List<Order> orderList = orderMapper.findOrderList(order);
        List<MyOrder> myOrders = getMyOrder(orderList);
        if(CollectionUtils.isEmpty(myOrders)){
            return RopResponse.createFailedRep("", "暂无订单", "1.0.0");
        }
        responseBody.setDataList(myOrders);
        return RopResponse.createSuccessRep("", "查询订单列表成功", "1.0.0", responseBody);
    }

    /**
     * 我的提现金额和鲜花
     * @author      Wang
     * @param       orderQueryRequest
     * @return
     * @exception
     * @date        2019/7/3 10:35
     */
    @ApiMethod(method = "api.pinke.user.order.getCashAndFlowers", version = "1.0.0")
    public RopResponse<RopOrderOtherResponse> getCashAndFlowers(RopRequestBody<RopOrderQueryRequest> orderQueryRequest) {

        RopOrderOtherResponse response = new RopOrderOtherResponse();
        String openId = orderQueryRequest.getT().getOpenId();

        //查询我的鲜花数
        UserFlower uf = new UserFlower();
        uf.setOpenId(openId);
        List<UserFlower> flowerList = userFlowerMapper.findUserFlowerList(uf);
        if(CollectionUtils.isNotEmpty(flowerList)){
            response.setFlowers(CalFlowerUtils.calTotalFlowers(flowerList));
        }

        //查询我的累计提现金额
        UserReturnCashRecord record = new UserReturnCashRecord();
        record.setOpenId(openId);
        record.setCashBackStatus(1);
        List<UserReturnCashRecord> list = userReturnCashRecordMapper.findRecordList(record);
        if(CollectionUtils.isNotEmpty(list)){
            response.setCashAmount(getTotalCashAmount(list));
        }
        return RopResponse.createSuccessRep("", "查询成功", "1.0.0", response);
    }

    /**
     * 订单详情
     * @author      Wang
     * @param       orderDetailRequest
     * @return
     * @exception
     * @date        2019/7/3 10:39
     */
    @ApiMethod(method = "api.pinke.user.order.getOrderDetail", version = "1.0.0")
    public RopResponse<RopOrderDetailResponse> getOrderDetail(RopRequestBody<RopOrderDetailRequest> orderDetailRequest) {
        RopOrderDetailResponse response = new RopOrderDetailResponse();
        Order order = orderMapper.selectByPrimaryKey(orderDetailRequest.getT().getOrderId());
        if(order == null){
            return RopResponse.createFailedRep("", "暂无订单", "1.0.0");
        }
        response.setOrder(order);
        return RopResponse.createSuccessRep("", "查询订单详情成功", "1.0.0", response);
    }

    /**
    * 订单转换
    * @author      Wang
    * @param       orderList
    * @return
    * @exception
    * @date        2019/7/10 15:36
    */
    private List<PrenticeOrder> getPrenticeOrder(List<Order> orderList){
        if(CollectionUtils.isEmpty(orderList)){
            return null;
        }
        List<PrenticeOrder> prenticeOrders = new ArrayList<>();
        PrenticeOrder prenticeOrder;
        User user;
        for(Order o : orderList){
            prenticeOrder = new PrenticeOrder();
            user = getUser(o.getOpenId());
            if(user != null){
                prenticeOrder.setAvatar(user.getAvatar());
                prenticeOrder.setNickname(user.getNickname());
            }
            prenticeOrder.setContributionFlower(o.getContributionFlower());
            prenticeOrder.setOrderStatus(o.getOrderStatus());
            prenticeOrder.setNotice("0".equals(o.getOrderStatus()));
            prenticeOrder.setOrderCreateTime(o.getOrderCreateTime());
            prenticeOrders.add(prenticeOrder);
        }
        return prenticeOrders;
    }

    /**
     * 订单转换
     * @author      Wang
     * @param       orderList
     * @return
     * @exception
     * @date        2019/7/10 15:36
     */
    private List<MyOrder> getMyOrder(List<Order> orderList){
        if(CollectionUtils.isEmpty(orderList)){
            return null;
        }
        List<MyOrder> myOrders = new ArrayList<>();
        MyOrder myOrder;
        for(Order o : orderList){
            myOrder = new MyOrder();
            BeanUtils.copyProperties(myOrder, o);
            myOrders.add(myOrder);
        }
        return myOrders;
    }

    /**
    * 计算订单状态
    * @author      Wang
    * @param       queryType
    * @return
    * @exception
    * @date        2019/7/10 16:19
    */
    private String getOrderStatus(String queryType){
        String orderStatus = "";
        if(QUERY_TYPE.WAITRECEIVE.name().equals(queryType)){//待收货
            orderStatus = "0";
        }else if(QUERY_TYPE.WAITSETTLE.name().equals(queryType)){//待结算
            orderStatus = "2";
        }else if(QUERY_TYPE.VALIDATED.name().equals(queryType)){//已生效
            orderStatus = "3";
        }else if(QUERY_TYPE.INVALIDATE.name().equals(queryType)){//已失效
            orderStatus = "4";
        }
        return orderStatus;
    }

    /**
    * 查询用户信息
    * @author      Wang
    * @param       openId
    * @return
    * @exception
    * @date        2019/7/10 15:39
    */
    private User getUser(String openId){
        return userMapper.getUserByOpenid(openId);
    }

    /**
    * 计算累计提现
    * @author      Wang
    * @param       list
    * @return
    * @exception
    * @date        2019/7/10 16:45
    */
    private Integer getTotalCashAmount(List<UserReturnCashRecord> list){
        int result = 0;
        if(CollectionUtils.isNotEmpty(list)){
            for(UserReturnCashRecord cash : list){
                result += cash.getCashBackAmount();
            }
        }
        return result;
    }

}
