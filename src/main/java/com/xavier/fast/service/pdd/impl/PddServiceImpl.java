package com.xavier.fast.service.pdd.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.xavier.fast.constants.PddConstants;
import com.xavier.fast.constants.PingduoduoConstant;
import com.xavier.fast.entity.pdd.*;
import com.xavier.fast.properties.PinduoduoProperties;
import com.xavier.fast.service.pdd.IpddService;
import com.xavier.fast.utils.JSONStringUnderscoreToCamel;
import com.xavier.fast.utils.OkHttpUtils;
import com.xavier.fast.utils.ParamEncodeUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class PddServiceImpl implements IpddService {

    private static final Logger log = LoggerFactory.getLogger(PddServiceImpl.class);

    public static final Pattern PATTERN =Pattern.compile("(\\\\u(\\p{XDigit}{4}))");

    @Resource
    private PinduoduoProperties pinduoduoProperties;

    @Override
    public HotGoodsList.HotGoodsSearchResponse queryHotGoods(HotGoodsQueryRo goodsQueryRo) {
        Map<String, String> param = new HashMap<>();

        Integer offset = goodsQueryRo.getPageSize() * (goodsQueryRo.getPageNum() - 1);
        param.put("offset", offset.toString());
        param.put("limit", goodsQueryRo.getPageSize().toString());
        param.put("sort_type", goodsQueryRo.getType().toString());
        param.put("type", PddConstants.HOT_GOODS);
        param.put("p_id", pinduoduoProperties.getDefaultPid());   // 固定只返回有优惠券的
        try {
            HotGoodsList goodsList = post(param, HotGoodsList.class);
            if (goodsList == null) {
                log.warn("查询拼多多获取商品列表接口返回空值");
                return new HotGoodsList.HotGoodsSearchResponse();
            }
            if (goodsList.getErrorResponse() != null) {
                log.warn("查询拼多多获取商品列表接口返回错误信息{},queryRo={}", goodsList, goodsQueryRo);
                return new HotGoodsList.HotGoodsSearchResponse();
            }
            HotGoodsList.HotGoodsSearchResponse result = goodsList.getResponse();
            return result;
        } catch (IOException e) {
            log.error("查询拼多多获取商品列表接口发生异常:", e);
        }
        return new HotGoodsList.HotGoodsSearchResponse();
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
    public PddOrderList queryPddOrder(OrderQueryRo dto,Boolean retrunCount) {
        if(null == retrunCount){
            retrunCount = true;
        }
        Map<String,String> param = pinduoduoProperties.getCommonParam();
        param.put(PingduoduoConstant.Param.TYPE, PddConstants.ORDER_INCREMENT_GET);
        param.put(PingduoduoConstant.Param.START_UPDATE_TIME,dto.getStartUpdateTime()+"");
        param.put(PingduoduoConstant.Param.END_UPDATE_TIME,dto.getEndUpdateTime()+"");
        param.put(PingduoduoConstant.Param.PAGE,dto.getPageNum()+"");
        param.put(PingduoduoConstant.Param.PAGE_SIZE,dto.getPageSize()+"");
        param.put(PingduoduoConstant.Param.RETURN_COUNT,retrunCount.toString());
        String sign = ParamEncodeUtils.getMd5Signature(param, pinduoduoProperties.getClientSecret());
        param.put(PingduoduoConstant.Param.SIGN,sign);
        String response = null;
        try {
            response = OkHttpUtils.post(PddConstants.URL, param);
            response = unicodeToString(response);
            if(null != response) {
                JSONObject obj = JSON.parseObject(response);
                if(null != obj && obj.getString("order_list_get_response") != null){
                    String orderInfo = JSONStringUnderscoreToCamel.transform(obj.getString("order_list_get_response"));
                    return JSON.parseObject(orderInfo,PddOrderList.class);
                }
            }
        } catch (IOException e) {
            log.error("CommonDoodsPromotionUrlIntegrationImpl.generate  Error",e);
        }
        return null;
    }

    @Override
    public OrderVo queryGoodsShareUrl(String goodsId, String openId, String customParam) {
        //生成推广链接
        OrderVo orderVo = generate(goodsId, customParam);
        //失败重试2次
        for(int i = 0; i < 2; i++){
            if(null == orderVo){
                orderVo = generate(goodsId, customParam);
            }
        }
        orderVo.setOpenid(openId);
        orderVo.setGoodsId(goodsId);
        return orderVo;
    }

    public OrderVo generate(String goodsId,String customParam){
        Map<String,String> param = pinduoduoProperties.getCommonParam();
        param.put(PingduoduoConstant.Param.TYPE, PddConstants.COMMON_GOODS_PROMOTION);
        param.put(PingduoduoConstant.Param.P_ID,pinduoduoProperties.getDefaultPid());
        param.put(PingduoduoConstant.Param.GOODS_ID_LISTS,"["+goodsId+"]");
        param.put(PingduoduoConstant.Param.CUSTOM_PARAMETERS,customParam);
        param.put(PingduoduoConstant.Param.GENERATE_WE_APP, PingduoduoConstant.Param.TRUE);

        String sign = ParamEncodeUtils.getMd5Signature(param, pinduoduoProperties.getClientSecret());
        param.put(PingduoduoConstant.Param.SIGN,sign);
        String response = null;
        try {
            response = OkHttpUtils.post(PddConstants.URL, param);
            response = unicodeToString(response);
            if(null != response) {
                JSONObject obj = JSON.parseObject(response);
                if(null != obj && obj.getJSONObject("goods_promotion_url_generate_response") != null){
                    JSONArray jsonArray = obj.getJSONObject("goods_promotion_url_generate_response").
                            getJSONArray("goods_promotion_url_list");
                    if(null != jsonArray&& jsonArray.size()>0){
                        JSONObject urlObj = (JSONObject) jsonArray.get(0);
                        String weAppInfo = JSONStringUnderscoreToCamel.transform(urlObj.getString("we_app_info"));
                        return JSON.parseObject(weAppInfo,OrderVo.class);
                    }
                }
            }
            log.error("CommonDoodsPromotionUrlIntegrationImpl.generate  Error response ",response);
        } catch (IOException e) {
            log.error("CommonDoodsPromotionUrlIntegrationImpl.generate  Error",e);
        }
        return null;
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

    /**
     * unicode转中文
     * @param str
     * @return
     */
    public static String unicodeToString(String str) {

        Matcher matcher = PATTERN.matcher(str);

        char ch;
        while (matcher.find()) {
            ch = (char) Integer.parseInt(matcher.group(2), 16);
            str = str.replace(matcher.group(1), ch + "");
        }
        return str;
    }

}
