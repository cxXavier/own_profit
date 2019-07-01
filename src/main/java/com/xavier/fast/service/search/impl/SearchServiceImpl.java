package com.xavier.fast.service.search.impl;

import com.xavier.fast.annotation.ApiMethod;
import com.xavier.fast.model.base.RopRequestBody;
import com.xavier.fast.model.base.RopResponse;
import com.xavier.fast.model.goods.RopGoodsListResponse;
import com.xavier.fast.model.search.RopSearchRequest;
import com.xavier.fast.model.search.RopSearchResponse;
import com.xavier.fast.service.search.ISearchService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
* @Description:    搜索服务类
* @Author:         Wang
* @CreateDate:     2019/6/26 17:26
* @UpdateUser:
* @UpdateDate:     2019/6/26 17:26
* @UpdateRemark:
* @Version:        1.0
*/
@Service
public class SearchServiceImpl implements ISearchService {

    @ApiMethod(method = "api.pinke.search.searchGoods", version = "1.0.0")
    public RopResponse<RopSearchResponse> searchGoods(RopRequestBody<RopSearchRequest> searchRequest) {

        RopSearchResponse searchResponse = new RopSearchResponse();
        List<RopGoodsListResponse> goodsList = new ArrayList<>();
        RopGoodsListResponse goods = new RopGoodsListResponse();
//        goods.setGoodsId(11111L);
//        goods.setGoodsName("美的风扇");
//        goods.setGoodsType(RopGoodsListResponse.GOODS_TYPE.ELECTRICAL.getCode());
        goodsList.add(goods);
        searchResponse.setGoodsList(goodsList);

        return RopResponse.createSuccessRep("1", "获取商品列表信息成功", "1.0.0", searchResponse);
    }
}
