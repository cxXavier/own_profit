package com.xavier.fast.service.pdd.impl;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.xavier.fast.constants.PddConstants;
import com.xavier.fast.entity.pdd.*;
import com.xavier.fast.properties.PinduoduoProperties;
import com.xavier.fast.service.pdd.IpddService;
import com.xavier.fast.utils.OkHttpUtils;
import com.xavier.fast.utils.ParamEncodeUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PddServiceImpl implements IpddService {

    private static final Logger log = LoggerFactory.getLogger(PddServiceImpl.class);

    @Resource
    private PinduoduoProperties pinduoduoProperties;

    @Override
    public GoodsList.GoodsSearchResponse queryHotGoods(HotGoodsQueryRo goodsQueryRo) {
        Map<String, String> param = new HashMap<>();

        Integer offset = goodsQueryRo.getPageSize()*(goodsQueryRo.getPageNum() + 1);
        param.put("offset", offset.toString());
        param.put("limit", goodsQueryRo.getPageSize().toString());
        param.put("sort_type", goodsQueryRo.getType().toString());
        param.put("type", PddConstants.HOT_GOODS);
        param.put("p_id", "true");   // 固定只返回有优惠券的
        try {
            GoodsList goodsList = post(param, GoodsList.class);
            if (goodsList == null) {
                log.warn("查询拼多多获取商品列表接口返回空值");
                return new GoodsList.GoodsSearchResponse();
            }
            if (goodsList.getErrorResponse() != null) {
                log.warn("查询拼多多获取商品列表接口返回错误信息{},queryRo={}", goodsList, goodsQueryRo);
                return new GoodsList.GoodsSearchResponse();
            }
            GoodsList.GoodsSearchResponse result = goodsList.getResponse();
            return result;
        } catch (IOException e) {
            log.error("查询拼多多获取商品列表接口发生异常:", e);
        }
        return new GoodsList.GoodsSearchResponse();
    }

    @Override
    public GoodsList.GoodsSearchResponse queryGoodsList(GoodsQueryRo goodsQueryRo) {
        Map<String, String> param = new HashMap<>();
        if (goodsQueryRo.getTagId() != null) {
            param.put("opt_id", goodsQueryRo.getTagId().toString());
        }
        if (StringUtils.isNotBlank(goodsQueryRo.getKeyword())) {
            param.put("keyword", goodsQueryRo.getKeyword());
        }
        if (goodsQueryRo.getGoodsIdList() != null) {
            param.put("goods_id_list", JSONObject.toJSONString(goodsQueryRo.getGoodsIdList()));
        }
        param.put("page_size", goodsQueryRo.getPageSize().toString());
        param.put("page", goodsQueryRo.getPageNum().toString());
        param.put("sort_type", goodsQueryRo.getSortType().toString());
        param.put("type", PddConstants.GOODS_SEARCH);
        param.put("with_coupon", "true");   // 固定只返回有优惠券的
        try {
            GoodsList goodsList = post(param, GoodsList.class);
            if (goodsList == null) {
                log.warn("查询拼多多获取商品列表接口返回空值");
                return new GoodsList.GoodsSearchResponse();
            }
            if (goodsList.getErrorResponse() != null) {
                log.warn("查询拼多多获取商品列表接口返回错误信息{},queryRo={}", goodsList, goodsQueryRo);
                return new GoodsList.GoodsSearchResponse();
            }
            GoodsList.GoodsSearchResponse result = goodsList.getResponse();
            return result;
        } catch (IOException e) {
            log.error("查询拼多多获取商品列表接口发生异常:", e);
        }
        return new GoodsList.GoodsSearchResponse();
    }

    @Override
    public List<Good> queryGoodsList(List<String> goodsIdList) {
        if (goodsIdList == null || goodsIdList.isEmpty()) {
            return Lists.newArrayList();
        }
        GoodsQueryRo ro = new GoodsQueryRo();
        List<Good> goods = Lists.newArrayList();
        for (int i = 0; i < goodsIdList.size(); i += 100) {
            int start = i;
            int end = i + 100;
            ro.setGoodsIdList(goodsIdList.subList(start, end > goodsIdList.size() ? goodsIdList.size() : end));
            goods.addAll(queryGoodsList(ro).getGoodsList());
        }
        return goods;
    }

    @Override
    public Good queryGoodDetail(String goodId) {
        Map<String, String> param = new HashMap<>();
        String[] goodIds = {goodId};
        param.put("goods_id_list", JSONObject.toJSONString(goodIds));
        param.put("type", PddConstants.GOODS_DETAIL);
        try {
            GoodDetailList goodDetailList = post(param, GoodDetailList.class);
            if (goodDetailList != null && goodDetailList.getResponse() != null
                    && goodDetailList.getResponse().getGoodsDetails() != null
                    && goodDetailList.getResponse().getGoodsDetails().size() > 0) {

                Good result = goodDetailList.getResponse().getGoodsDetails().get(0);
                return result;
            }
        } catch (IOException e) {
            log.error("查询拼多多获取商品详情接口发生异常:", e);
        }
        return null;
    }

    @Override
    public void updateOrderMsg() {

    }

    @Override
    public void queryGoodsShareUrl() {

    }



    private <T> T post(Map<String, String> param, Class<T> clazz) throws IOException {
        param.put("timestamp", String.valueOf(System.currentTimeMillis()).substring(0, 10));
        param.put("client_id",pinduoduoProperties.getClientId());
        String signature = ParamEncodeUtils.getMd5Signature(param, pinduoduoProperties.getClientSecret());
        param.put("sign", signature);
        String s = OkHttpUtils.post(PddConstants.URL, param);
        if (StringUtils.isNotBlank(s)) {
            s = ParamEncodeUtils.unicodeToString(s);
            return JSONObject.parseObject(s, clazz);
        }
        return null;
    }

}
