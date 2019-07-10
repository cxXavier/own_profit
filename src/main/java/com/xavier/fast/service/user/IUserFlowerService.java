package com.xavier.fast.service.user;

import com.xavier.fast.model.base.RopRequestBody;
import com.xavier.fast.model.base.RopResponse;
import com.xavier.fast.model.user.flower.RopFlowerRequest;
import com.xavier.fast.model.user.flower.RopFlowerResponse;
import com.xavier.fast.model.user.flower.RopPrenticeResponse;
import com.xavier.fast.model.user.flower.RopToTalFlowerResponse;

/**
* @Description:    用户鲜花
* @Author:         Wang
* @CreateDate:     2019/7/2 18:15
* @UpdateUser:
* @UpdateDate:     2019/7/2 18:15
* @UpdateRemark:
* @Version:        1.0
*/
public interface IUserFlowerService {

    /**
    * 徒弟鲜花列表
    * @author      Wang
    * @param       flowerRequest
    * @return
    * @exception
    * @date        2019/7/2 19:39
    */
    public RopResponse<RopPrenticeResponse> getPrenticeListWithFlowers(RopRequestBody<RopFlowerRequest> flowerRequest);

    /**
    * 用户鲜花开支
    * @author      Wang
    * @param       flowerRequest
    * @return
    * @exception
    * @date        2019/7/3 10:15
    */
    public RopResponse<RopFlowerResponse> getUserFlowers(RopRequestBody<RopFlowerRequest> flowerRequest);


    /**
    * 鲜花数量汇总
    * @author      Wang
    * @param       flowerRequest
    * @return      
    * @exception   
    * @date        2019/7/10 14:06
    */
    public RopResponse<RopToTalFlowerResponse> getTotalFlowers(RopRequestBody<RopFlowerRequest> flowerRequest);


}
