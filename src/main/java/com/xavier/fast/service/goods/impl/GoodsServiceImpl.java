package com.xavier.fast.service.goods.impl;

import com.xavier.fast.annotation.ApiMethod;
import com.xavier.fast.dao.GoodsMapper;
import com.xavier.fast.dao.TagMapper;
import com.xavier.fast.entity.goods.Goods;
import com.xavier.fast.entity.pdd.Good;
import com.xavier.fast.entity.tag.Tag;
import com.xavier.fast.model.base.RopRequestBody;
import com.xavier.fast.model.base.RopResponse;
import com.xavier.fast.model.base.RopResponseBody;
import com.xavier.fast.model.goods.*;
import com.xavier.fast.service.goods.IGoodsService;
import com.xavier.fast.service.pdd.IpddService;
import com.xavier.fast.utils.GoodsFilter;
import com.xavier.fast.utils.GoodsUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    private static List<Goods> recommendGoodsList = new ArrayList<>();

    @Autowired
    private TagMapper tagMapper;

    @Autowired
    private IpddService pddService;

    @Autowired
    private GoodsMapper goodsMapper;

    /**
    * 更新推荐商品（置顶的商品池）
    * @author      Wang
    * @param       recommendGoodsRequest
    * @return
    * @exception
    * @date        2019/8/3 11:07
    */
    @ApiMethod(method = "api.pinke.goods.updateRecommendGoods", version = "1.0.0")
    public RopResponse<RopRecommendGoodsResponse> updateRecommendGoods(
            RopRequestBody<RopRecommendGoodsRequest> recommendGoodsRequest) {
        String goodsIds = recommendGoodsRequest.getT().getGoodsIds();
        String[] goodsArr = goodsIds.split(",");
        if(ArrayUtils.isEmpty(goodsArr)){
            return RopResponse.createFailedRep("",
                    "更新推荐商品（置顶的商品池）失败，参数不合法", "1.0.0");
        }
        RopRecommendGoodsResponse response = new RopRecommendGoodsResponse();
        StringBuffer sbf = new StringBuffer();
        List<Goods> updateFailedGoodsList = new ArrayList<>();
        List<Goods> updateSuccessGoodsList = new ArrayList<>();
        Goods goods;
        for(String goodsId : goodsArr){
            Good good = pddService.queryGoodDetail(goodsId);
            if(good == null){
                sbf.append(goodsId).append(",");
            }else{
                goods = new Goods();
                goods = GoodsUtils.copyGoods(good, goods, 1);
                //过滤佣金比例小于30%、不含需屏蔽关键字的商品、券后价小于等于10元
                if(GoodsFilter.goodsValidate(good.getPromotionRate(),
                        good.getGoodsName(), good.getPriceAfterCoupon())){
                    updateFailedGoodsList.add(goods);
                }else if(updateSuccessGoodsList.size() <= 20){
                    updateSuccessGoodsList.add(goods);
                }
            }
        }
        if(CollectionUtils.isNotEmpty(updateSuccessGoodsList)){
            //不够20个的时候，打到热门商品商品中拿出20个补齐
            int size = updateSuccessGoodsList.size();
            if(size < 20){
                int startRow = 80;
                Map<String, Object> params = new HashMap<>();
                params.put("isHot", 1);
                params.put("startRow", startRow);
                params.put("endRow", 20);
                params.put("orderBy", "sold_quantity");
                params.put("sortBy", "DESC");
                List<Goods> goodsList = goodsMapper.findGoodsListByParams(params);
                if(CollectionUtils.isEmpty(goodsList)){
                    return RopResponse.createFailedRep("", "补齐推荐商品失败", "1.0.0");
                }
                updateSuccessGoodsList.addAll(goodsList.subList(0, (20 - size)));
            }
            goodsMapper.deleteAllRecommendGoods(new HashMap<>());
            int count = goodsMapper.insertBatchRecommend(updateSuccessGoodsList);
            recommendGoodsList = updateSuccessGoodsList;
        }
        response.setNotExistGoodsId(sbf.toString());
        response.setUpdateFailedGoodsList(updateFailedGoodsList);
        response.setUpdateSuccessGoodsList(updateSuccessGoodsList);
        return RopResponse.createSuccessRep("",
                "更新推荐商品（置顶的商品池）成功", "1.0.0", response);
    }

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

        //先从推荐商品中拿
        if(pageNum < 3){
            if(CollectionUtils.isEmpty(recommendGoodsList) || recommendGoodsList.size() < 20){
                recommendGoodsList = goodsMapper.findRecommendGoodsList(new Goods());
            }
            if(CollectionUtils.isNotEmpty(recommendGoodsList) && recommendGoodsList.size() == 20){
                if(pageNum == 1){
                    response.setDataList(recommendGoodsList.subList(0, 10));
                }
                if(pageNum == 2){
                    response.setDataList(recommendGoodsList.subList(10, 20));
                }
                response.setHasNext(true);
                return RopResponse.createSuccessRep("", "获取热门商品成功", "1.0.0", response);
            }
        }else{
            pageNum = pageNum - 2;
        }


        int startRow = pageNum == 1 ? 0 : (pageNum - 1) * PAGE_SIZE;

        Map<String, Object> params = new HashMap<>();
        params.put("isHot", 1);
        params.put("startRow", startRow);
        params.put("endRow", PAGE_SIZE);
        params.put("orderBy", "sold_quantity");
        params.put("sortBy", "DESC");
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

        int pageNum = goodsRequest.getT().getPageNum();
        int startRow = pageNum == 1 ? 0 : (pageNum - 1) * PAGE_SIZE;

        Map<String, Object> params = new HashMap<>();
        params.put("isNormal", 1);
        if(StringUtils.isNotBlank(keyword)){
            params.put("goodsName", keyword);
        }else{
            if(tagId != null){
                params.put("optIds", tagId.toString());
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
            if(tagId != null){
                record.setOptIds(tagId.toString());
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
