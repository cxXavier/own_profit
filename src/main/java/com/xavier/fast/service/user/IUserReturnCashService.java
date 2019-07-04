package com.xavier.fast.service.user;

import com.xavier.fast.model.base.RopRequestBody;
import com.xavier.fast.model.base.RopResponse;
import com.xavier.fast.model.user.cash.RopReturnCashRequest;
import com.xavier.fast.model.user.cash.RopReturnCashResponse;

/**
* @Description:    用户提现
* @Author:         Wang
* @CreateDate:     2019/7/3 22:13
* @UpdateUser:
* @UpdateDate:     2019/7/3 22:13
* @UpdateRemark:
* @Version:        1.0
*/
public interface IUserReturnCashService {

    /**
    * 提现
    * @author      Wang
    * @param       cashRequest
    * @return
    * @exception
    * @date        2019/7/3 22:23
    */
    public RopResponse<RopReturnCashResponse> userReturnCash(RopRequestBody<RopReturnCashRequest> cashRequest);
}
