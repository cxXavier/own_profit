package com.xavier.fast.service.goods.impl;

import com.xavier.fast.annotation.ApiMethod;
import com.xavier.fast.dao.TagMapper;
import com.xavier.fast.entity.pdd.*;
import com.xavier.fast.entity.tag.Tag;
import com.xavier.fast.model.base.RopRequestBody;
import com.xavier.fast.model.base.RopResponse;
import com.xavier.fast.model.goods.*;
import com.xavier.fast.service.goods.IGoodsService;
import com.xavier.fast.service.pdd.IpddService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Autowired
    private TagMapper tagMapper;

    @Autowired
    private IpddService pddService;

    /**
    * 获取商品类目
    * @author
    * @param       goodsCatsRequest
    * @return
    * @exception
    * @date        2019/6/30 15:53
    */
    @ApiMethod(method = "api.pinke.goods.getGoodsCats", version = "1.0.0")
    public RopResponse<RopGoodsCatsResponse> getGoodsCats(RopRequestBody<RopGoodsCatsRequest> goodsCatsRequest) {

        List<Tag> tags = tagMapper.findTagList(new Tag());
        if(CollectionUtils.isEmpty(tags)){
           return RopResponse.createFailedRep("-1", "获取类目数据失败", "1.0.0");
        }
        RopGoodsCatsResponse catsResponse = new RopGoodsCatsResponse();
        catsResponse.setCatsList(tags);
        return RopResponse.createSuccessRep("1", "获取类目数据成功", "1.0.0", catsResponse);
    }

    /**
    * 获取热门商品
    * @author      Wang
    * @param       goodsRequest
    * @return
    * @exception
    * @date        2019/6/30 23:06
    */
    @ApiMethod(method = "api.pinke.goods.getHotGoodsList", version = "1.0.0")
    public RopResponse<RopGoodsListResponse> getHotGoodsList(RopRequestBody<RopHotGoodsRequest> goodsRequest) {

        RopGoodsListResponse response = new RopGoodsListResponse();

        HotGoodsQueryRo goodsQueryRo = new HotGoodsQueryRo();
        goodsQueryRo.setType(goodsRequest.getT().getType());
        goodsQueryRo.setPageNum(goodsRequest.getT().getPageNum());

        HotGoodsList.HotGoodsSearchResponse goodsSearchResponse = pddService.queryHotGoods(goodsQueryRo);
        if (goodsSearchResponse == null || CollectionUtils.isEmpty(goodsSearchResponse.getGoodsList())) {
            return RopResponse.createFailedRep("-1", "获取热门商品失败", "1.0.0");
        }
        List<Good> goodList = goodsSearchResponse.getGoodsList();
        response.setGoodList(goodList);

        return RopResponse.createSuccessRep("1", "获取热门商品成功", "1.0.0", response);
    }

    /**
     * 获取商品列表
     * @author      Wang
     * @param       goodsRequest
     * @return
     * @exception
     * @date        2019/6/30 23:06
     */
    @ApiMethod(method = "api.pinke.goods.getGoodsList", version = "1.0.0")
    public RopResponse<RopGoodsListResponse> getGoodsList(RopRequestBody<RopGoodsListRequest> goodsRequest) {

        RopGoodsListResponse response = new RopGoodsListResponse();

        GoodsQueryRo goodsQueryRo = new GoodsQueryRo();
        goodsQueryRo.setTagId(goodsRequest.getT().getTagId());
        goodsQueryRo.setSortType(goodsRequest.getT().getSortType());
        goodsQueryRo.setKeyword(goodsRequest.getT().getKeyword());
        goodsQueryRo.setOpenid(goodsRequest.getT().getOpenid());
        goodsQueryRo.setPageNum(goodsRequest.getT().getPageNum());
        GoodsList.GoodsSearchResponse goodsSearchResponse = pddService.queryGoodsList(goodsQueryRo);
        if (goodsSearchResponse == null || CollectionUtils.isEmpty(goodsSearchResponse.getGoodsList())) {
            return RopResponse.createFailedRep("-1", "获取商品列表失败", "1.0.0");
        }
        List<Good> goodList = goodsSearchResponse.getGoodsList();
        response.setGoodList(goodList);

        return RopResponse.createSuccessRep("1", "获取商品列表成功", "1.0.0", response);
    }

    /**
    * 商品详情接口
    * @author      Wang
    * @param       goodsRequest
    * @return
    * @exception
    * @date        2019/6/26 15:15
    */
    @ApiMethod(method = "api.pinke.goods.getGoodsDetail", version = "1.0.0")
    public RopResponse<RopGoodsDetailResponse> getGoodsDetail(RopRequestBody<RopGoodsDetailRequest> goodsRequest) {

        RopGoodsDetailResponse response = new RopGoodsDetailResponse();
        Good good = pddService.queryGoodDetail(goodsRequest.getT().getGoodsId().toString());
        if(good == null){
            return RopResponse.createFailedRep("-1", "获取商品详情信息失败", "1.0.0");
        }
        response.setGood(good);
        return RopResponse.createSuccessRep("1", "获取商品详情信息成功", "1.0.0", response);
    }
}
