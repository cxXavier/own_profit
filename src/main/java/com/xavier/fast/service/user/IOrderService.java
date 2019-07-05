package com.xavier.fast.service.user;

import com.xavier.fast.model.base.RopRequestBody;
import com.xavier.fast.model.base.RopResponse;
import com.xavier.fast.model.base.RopResponseBody;
import com.xavier.fast.model.user.order.RopOrderDetailRequest;
import com.xavier.fast.model.user.order.RopOrderDetailResponse;
import com.xavier.fast.model.user.order.RopOrderListRequest;
import com.xavier.fast.model.user.order.RopOrderListResponse;

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
    * 订单列表
    * @author      Wang
    * @param       orderListRequest
    * @return
    * @exception
    * @date        2019/7/3 10:29
    */
    public RopResponse<RopResponseBody> getOrderList(RopRequestBody<RopOrderListRequest> orderListRequest);

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
