package com.xavier.fast.service.goods;

import com.xavier.fast.model.base.RopResponse;
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

    public RopResponse<RopGoodsResponse> getGoodsDetail(RopGoodsRequest goodsRequest);
}
