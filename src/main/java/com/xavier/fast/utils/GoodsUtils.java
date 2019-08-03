package com.xavier.fast.utils;

import com.xavier.fast.entity.goods.Goods;
import com.xavier.fast.entity.pdd.Good;
import org.apache.commons.collections.CollectionUtils;

import java.util.Date;
import java.util.List;

public class GoodsUtils {


    public static Goods copyGoods(Good source, Goods target, int goodsType, boolean needFilter){
        if(needFilter){
            //过滤佣金比例小于30%、不含需屏蔽关键字的商品、券后价小于等于10元
            if(GoodsFilter.goodsValidate(source.getPromotionRate(),
                    source.getGoodsName(), source.getPriceAfterCoupon())){
                return null;
            }
        }
        return copyGoods(source, target, goodsType);
    }

    /**
     * 商品copy
     * @param source
     * @param target
     * @param goodsType
     * @return
     */
    public static Goods copyGoods(Good source, Goods target, int goodsType){
        target.setGoodsId(source.getGoodsId());
        target.setGoodsName(source.getGoodsName());
        target.setGoodsDesc(source.getGoodsDesc());
        target.setGoodsThumbnailUrl(source.getGoodsThumbnailUrl());
        target.setGoodsImageUrl(source.getGoodsImageUrl());
        target.setGoodsGalleryUrls(convertStrListToString(source.getGoodsGalleryUrls()));
        target.setSoldQuantity(source.getSoldNum());
        target.setMinGroupPrice(source.getMinGroupPrice());
        target.setMinNormalPrice(source.getMinNormalPrice());
        target.setMallId(source.getMallId());
        target.setMallName(source.getMallName());
        target.setMallRate(source.getMallRate());
        target.setMerchantType(source.getMerchantType());
        target.setCategoryId(source.getCategoryId());
        target.setCategoryName(source.getCategoryName());
        target.setOptId(source.getOptId());
        target.setOptName(source.getOptName());
        target.setMallCps(source.getMallCps());
        target.setHasCoupon(source.isHasCoupon() == true ? 1 : 0);
        target.setCouponMinOrderAmount(source.getCouponMinOrderAmount());
        target.setCouponDiscount(source.getCouponDiscount());
        target.setCouponTotalQuantity(source.getCouponTotalQuantity());
        target.setCouponRemainQuantity(source.getCouponRemainQuantity());
        target.setCouponEndTime(source.getCouponEndTime());
        target.setCouponStartTime(source.getCouponStartTime());
        target.setPromotionRate(source.getPromotionRate());
        target.setGoodsEvalScore(source.getGoodsEvalScore());
        target.setGoodsEvalCount(source.getGoods_eval_count());
        target.setAvgDesc(getScore(source.getAvgDesc()));
        target.setAvgLgst(getScore(source.getAvgLgst()));
        target.setAvgServ(getScore(source.getAvgServ()));
        target.setCatIds(convertIntListToString(source.getCatIds()));
        target.setDescPct(source.getDescPct());
        target.setLgstPct(source.getLgstPct());
        target.setServPct(source.getServPct());
        target.setOptIds(convertIntListToString(source.getOptIds()));
        if(goodsType == 1){
            target.setIsHot(1);
        }else{
            target.setIsNormal(1);
        }
        target.setCreateAt(source.getCreateAt());
        target.setCreateTime(new Date());
        return target;
    }

    private static String convertStrListToString(List<String> strList){
        StringBuffer result = new StringBuffer();
        if(CollectionUtils.isEmpty(strList)){
            return null;
        }
        for(int i = 0; i < strList.size(); i++){
            if(i == (strList.size() - 1)){
                result.append(strList.get(i));
            }else{
                result.append(strList.get(i)).append(",");
            }
        }
        return result.toString();
    }

    private static String convertIntListToString(List<Integer> intList){
        StringBuffer result = new StringBuffer();
        if(CollectionUtils.isEmpty(intList)){
            return null;
        }
        for(int i = 0; i < intList.size(); i++){
            if(i == (intList.size() - 1)){
                result.append(intList.get(i));
            }else{
                result.append(intList.get(i)).append(",");
            }
        }
        return result.toString();
    }

    private static Long getScore(String scoreCnName){
        if("高".equals(scoreCnName)){
            return 3L;
        }else if("中".equals(scoreCnName)){
            return 2L;
        }else if("低".equals(scoreCnName)){
            return 1L;
        }
        return 0L;
    }
}
