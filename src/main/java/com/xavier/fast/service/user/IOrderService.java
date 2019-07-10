package com.xavier.fast.service.user;

import com.xavier.fast.entity.pdd.OrderVo;
import com.xavier.fast.model.base.RopRequestBody;
import com.xavier.fast.model.base.RopResponse;
import com.xavier.fast.model.base.RopResponseBody;
import com.xavier.fast.model.user.order.*;

/**
* @Description:    订单服务
* @Author:         Wang
* @CreateDate:     2019/7/3 10:25
* @UpdateUser:
* @UpdateDate:     2019/7/3 10:25
* @UpdateRemark:
* @Version:        1.0
*/
public interface IOrderService {

    /**
    * 创建虚拟订单
    * @author      Wang
    * @param       createOrderRequest
    * @return
    * @exception
    * @date        2019/7/10 10:43
    */
    public RopResponse<OrderVo> createOrder(RopRequestBody<RopCreateOrderRequest> createOrderRequest);

    /**
     * 徒弟订单列表
     * @author      Wang
     * @param       orderListRequest
     * @return
     * @exception
     * @date        2019/7/3 10:29
     */
    public RopResponse<RopResponseBody> getPrenticeOrderList(RopRequestBody<RopOrderListQueryRequest> orderListRequest);

    /**
    * 我的订单列表
    * @author      Wang
    * @param       orderListRequest
    * @return
    * @exception
    * @date        2019/7/3 10:29
    */
    public RopResponse<RopResponseBody> getOrderList(RopRequestBody<RopOrderListQueryRequest> orderListRequest);

    /**
     * 我的提现金额和鲜花
     * @author      Wang
     * @param       orderQueryRequest
     * @return
     * @exception
     * @date        2019/7/3 10:29
     */
    public RopResponse<RopOrderOtherResponse> getCashAndFlowers(RopRequestBody<RopOrderQueryRequest> orderQueryRequest);

    /**
    * 订单详情
    * @author      Wang
    * @param       orderDetailRequest
    * @return
    * @exception
    * @date        2019/7/3 10:39
    */
    public RopResponse<RopOrderDetailResponse> getOrderDetail(RopRequestBody<RopOrderDetailRequest> orderDetailRequest);
}
