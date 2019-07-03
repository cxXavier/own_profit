package com.xavier.fast.service.user.impl;

import com.xavier.fast.annotation.ApiMethod;
import com.xavier.fast.dao.OrderMapper;
import com.xavier.fast.entity.order.Order;
import com.xavier.fast.model.base.RopRequestBody;
import com.xavier.fast.model.base.RopResponse;
import com.xavier.fast.model.user.order.RopOrderDetailRequest;
import com.xavier.fast.model.user.order.RopOrderDetailResponse;
import com.xavier.fast.model.user.order.RopOrderListRequest;
import com.xavier.fast.model.user.order.RopOrderListResponse;
import com.xavier.fast.service.user.IOrderService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
public class OrderServiceImpl implements IOrderService {

    @Resource
    private OrderMapper orderMapper;

    /**
    * 订单列表
    * @author      Wang
    * @param       orderListRequest
    * @return
    * @exception
    * @date        2019/7/3 10:35
    */
    @ApiMethod(method = "api.pinke.user.order.getOrderList", version = "1.0.0")
    public RopResponse<RopOrderListResponse> getOrderList(RopRequestBody<RopOrderListRequest> orderListRequest) {
        RopOrderListResponse response = new RopOrderListResponse();
        Order order = new Order();
        order.setOpenId(orderListRequest.getT().getOpenId());
        List<Order> orderList = orderMapper.findOrderList(order);
        if(CollectionUtils.isEmpty(orderList)){
            return RopResponse.createFailedRep("-1", "暂无订单", "1.0.0");
        }
        response.setOrderList(orderList);
        return RopResponse.createSuccessRep("1", "查询订单列表成功", "1.0.0", response);
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
            return RopResponse.createFailedRep("-1", "暂无订单", "1.0.0");
        }
        response.setOrder(order);
        return RopResponse.createSuccessRep("1", "查询订单详情成功", "1.0.0", response);
    }
}
