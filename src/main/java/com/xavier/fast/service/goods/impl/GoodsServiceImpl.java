package com.xavier.fast.service.goods.impl;

import com.xavier.fast.annotation.ApiMethod;
import com.xavier.fast.dao.GoodsMapper;
import com.xavier.fast.dao.TagMapper;
import com.xavier.fast.entity.goods.Goods;
import com.xavier.fast.entity.pdd.*;
import com.xavier.fast.entity.tag.Tag;
import com.xavier.fast.model.base.RopRequestBody;
import com.xavier.fast.model.base.RopResponse;
import com.xavier.fast.model.base.RopResponseBody;
import com.xavier.fast.model.goods.*;
import com.xavier.fast.service.goods.IGoodsService;
import com.xavier.fast.service.pdd.IpddService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    private final static int PAGE_SIZE = 10;

    @Autowired
    private TagMapper tagMapper;

    @Autowired
    private IpddService pddService;

    @Autowired
    private GoodsMapper goodsMapper;

    /**
    * 获取商品类目
    * @author
    * @param       goodsCatsRequest
    * @return
    * @exception
    * @date        2019/6/30 15:53
    */
    @ApiMethod(method = "api.pinke.goods.getGoodsCats", version = "1.0.0")
    public RopResponse<RopResponseBody> getGoodsCats(RopRequestBody<RopGoodsCatsRequest> goodsCatsRequest) {

        //查询父类目
        Tag tag = new Tag();
        tag.setParentId(goodsCatsRequest.getT().getParentCatId().intValue());
        List<Tag> tags = tagMapper.findTagList(tag);
        if(CollectionUtils.isEmpty(tags)){
           return RopResponse.createFailedRep("","获取主类目数据失败", "1.0.0");
        }

        //查询子类目
        for(Tag t : tags){
            tag = new Tag();
            tag.setParentId(t.getId());
            List<Tag> subTags = tagMapper.findTagList(tag);
            if(CollectionUtils.isNotEmpty(subTags)){
                t.setSubTags(subTags);
            }
        }

        RopResponseBody catsResponse = new RopResponseBody();
        catsResponse.setDataList(tags);
        return RopResponse.createSuccessRep("", "获取类目数据成功", "1.0.0", catsResponse);
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
    public RopResponse<RopResponseBody> getHotGoodsList(RopRequestBody<RopHotGoodsRequest> goodsRequest) {

        RopResponseBody response = new RopResponseBody();

        int pageNum = goodsRequest.getT().getPageNum();
        int startRow = pageNum == 1 ? 0 : (pageNum - 1) * PAGE_SIZE;

        Map<String, Object> params = new HashMap<>();
        params.put("isHot", 1);
        params.put("startRow", startRow);
        params.put("endRow", PAGE_SIZE);
        List<Goods> goodsList = goodsMapper.findGoodsListByParams(params);
        if(CollectionUtils.isEmpty(goodsList)){
            return RopResponse.createFailedRep("", "获取热门商品失败", "1.0.0");
        }
        response.setDataList(goodsList);

        Goods record = new Goods();
        record.setIsHot(1);
        int totalCount = goodsMapper.queryTotalCount(record);
        if(totalCount > 0){
            int totalPage = totalCount % PAGE_SIZE == 0 ? totalCount / PAGE_SIZE : (totalCount / PAGE_SIZE + 1);
            response.setHasNext(totalPage > pageNum);
        }


//        HotGoodsQueryRo goodsQueryRo = new HotGoodsQueryRo();
//        goodsQueryRo.setType(goodsRequest.getT().getType());
//        goodsQueryRo.setPageNum(goodsRequest.getT().getPageNum());
//        goodsQueryRo.setPageSize(goodsRequest.getT().getPageSize());
//        HotGoodsList.HotGoodsSearchResponse goodsSearchResponse = pddService.queryHotGoods(goodsQueryRo);
//        if (goodsSearchResponse == null || CollectionUtils.isEmpty(goodsSearchResponse.getGoodsList())) {
//            return RopResponse.createFailedRep("", "获取热门商品失败", "1.0.0");
//        }
//        List<Good> goodList = goodsSearchResponse.getGoodsList();
//        response.setDataList(goodList);

        return RopResponse.createSuccessRep("", "获取热门商品成功", "1.0.0", response);
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
    public RopResponse<RopResponseBody> getGoodsList(RopRequestBody<RopGoodsListRequest> goodsRequest) {

        RopResponseBody response = new RopResponseBody();

        Integer tagId = goodsRequest.getT().getTagId();
        Integer sortType = goodsRequest.getT().getSortType();
        String keyword = goodsRequest.getT().getKeyword();
        Integer tagLevel = goodsRequest.getT().getTagLevel();

        int pageNum = goodsRequest.getT().getPageNum();
        int startRow = pageNum == 1 ? 0 : (pageNum - 1) * PAGE_SIZE;

        Map<String, Object> params = new HashMap<>();
        params.put("isNormal", 1);
        if(StringUtils.isNotBlank(keyword)){
            params.put("goodsName", keyword);
        }else{
            if(tagLevel != null && tagLevel == 2){
                params.put("optIds", tagId.toString());
            }else {
                if(tagId != null){
                    params.put("optId", tagId);
                }
            }
        }
        if(sortType != null){
            if(sortType == 5){//按销量升序
                params.put("orderBy", "sold_quantity");
            }else if(sortType == 6){//按销量降序
                params.put("orderBy", "sold_quantity");
                params.put("sortBy", "DESC");
            }else if(sortType == 27){//描述评分击败同类店铺百分比升序
                params.put("orderBy", "avg_desc");
            }else if(sortType == 28){//描述评分击败同类店铺百分比降序
                params.put("orderBy", "avg_desc");
                params.put("sortBy", "DESC");
            }else if(sortType == 3){//按价格升序
                params.put("orderBy", "min_group_price");
            }else if(sortType == 4){//按价格降序
                params.put("orderBy", "min_group_price");
                params.put("sortBy", "DESC");
            }
        }
        params.put("startRow", startRow);
        params.put("endRow", PAGE_SIZE);
        List<Goods> goodsList = goodsMapper.findGoodsListByParams(params);
        if(CollectionUtils.isEmpty(goodsList)){
            return RopResponse.createFailedRep("", "获取商品列表失败", "1.0.0");
        }
        response.setDataList(goodsList);

        Goods record = new Goods();
        record.setIsNormal(1);
        if(StringUtils.isNotBlank(keyword)){
            record.setGoodsName(keyword);
        }else{
            if(tagLevel != null && tagLevel == 2){
                record.setOptIds(tagId.toString());
            }else {
                if(tagId != null){
                    record.setOptId(tagId.longValue());
                }
            }
            if(tagId != null){
                record.setOptId(tagId.longValue());
            }
        }
        int totalCount = goodsMapper.queryTotalCount(record);
        if(totalCount > 0){
            int totalPage = totalCount % PAGE_SIZE == 0 ? totalCount / PAGE_SIZE : (totalCount / PAGE_SIZE + 1);
            response.setHasNext(totalPage > pageNum);
        }

//        GoodsQueryRo goodsQueryRo = new GoodsQueryRo();
//        goodsQueryRo.setTagId(goodsRequest.getT().getTagId());
//        goodsQueryRo.setSortType(goodsRequest.getT().getSortType());
//        goodsQueryRo.setKeyword(goodsRequest.getT().getKeyword());
//        goodsQueryRo.setOpenid(goodsRequest.getT().getOpenid());
//        goodsQueryRo.setPageNum(goodsRequest.getT().getPageNum());
//        goodsQueryRo.setPageSize(goodsRequest.getT().getPageSize());
//        GoodsList.GoodsSearchResponse goodsSearchResponse = pddService.queryGoodsList(goodsQueryRo);
//        if (goodsSearchResponse == null || CollectionUtils.isEmpty(goodsSearchResponse.getGoodsList())) {
//            return RopResponse.createFailedRep("", "获取商品列表失败", "1.0.0");
//        }
//        List<Good> goodList = goodsSearchResponse.getGoodsList();
//        response.setDataList(goodList);

        return RopResponse.createSuccessRep("", "获取商品列表成功", "1.0.0", response);
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
            return RopResponse.createFailedRep("", "获取商品详情信息失败", "1.0.0");
        }
        response.setGood(good);
        return RopResponse.createSuccessRep("", "获取商品详情信息成功", "1.0.0", response);
    }
}
