package com.xavier.fast.service.goods;

import com.xavier.fast.model.base.RopRequestBody;
import com.xavier.fast.model.base.RopResponse;
import com.xavier.fast.model.goods.RopGoodsCatsRequest;
import com.xavier.fast.model.goods.RopGoodsCatsResponse;
import com.xavier.fast.model.goods.RopGoodsRequest;
import com.xavier.fast.model.goods.RopGoodsResponse;

/**
* @Description:    商品服务
* @Author:         Wang
* @CreateDate:     2019/6/25 19:50
* @UpdateUser:
* @UpdateDate:     2019/6/25 19:50
* @UpdateRemark:
* @Version:        1.0
*/
public interface IGoodsService {

    /**
    * 获取商品类目
    * @author      Wang
    * @param       goodsCatsRequest
    * @return
    * @exception
    * @date        2019/6/28 15:05
    */
    public RopResponse<RopGoodsCatsResponse> getGoodsCats(RopRequestBody<RopGoodsCatsRequest> goodsCatsRequest);

    /**
    * 获取商品详情
    * @author      Wang
    * @param       goodsRequest
    * @return
    * @exception
    * @date        2019/6/28 15:13
    */
    public RopResponse<RopGoodsResponse> getGoodsDetail(RopRequestBody<RopGoodsRequest> goodsRequest);
}
