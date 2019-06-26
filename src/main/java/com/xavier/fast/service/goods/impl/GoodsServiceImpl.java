package com.xavier.fast.service.goods.impl;

import com.xavier.fast.annotation.ApiMethod;
import com.xavier.fast.model.base.RopRequestBody;
import com.xavier.fast.model.base.RopResponse;
import com.xavier.fast.model.goods.RopGoodsRequest;
import com.xavier.fast.model.goods.RopGoodsResponse;
import com.xavier.fast.service.goods.IGoodsService;
import org.springframework.stereotype.Service;

/**
* @Description:    商品服务类
* @Author:         Wang
* @CreateDate:     2019/6/25 20:12
* @UpdateUser:
* @UpdateDate:     2019/6/25 20:12
* @UpdateRemark:
* @Version:        1.0
*/
@Service
public class GoodsServiceImpl implements IGoodsService {

    @ApiMethod(method = "api.pinke.goods.getGoodsDetail", version = "1.0.0")
    public RopResponse<RopGoodsResponse> getGoodsDetail(RopRequestBody<RopGoodsRequest> goodsRequest) {

        RopResponse<RopGoodsResponse> response = new RopResponse<>();

        RopGoodsResponse goods = new RopGoodsResponse();
        goods.setGoodsId(11111L);
        goods.setGoodsName("水果");

        return RopResponse.createSuccessRep("1", "获取商品详情信息成功", "1.0.0", goods);
    }
}
