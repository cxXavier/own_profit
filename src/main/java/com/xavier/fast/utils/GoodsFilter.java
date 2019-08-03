package com.xavier.fast.utils;

import org.apache.commons.lang3.StringUtils;

/**
* @Description:    商品过滤
* @Author:         Wang
* @CreateDate:     2019/8/3 10:49
* @UpdateUser:
* @UpdateDate:     2019/8/3 10:49
* @UpdateRemark:
* @Version:        1.0
*/
public class GoodsFilter {

    //过滤佣金比例小于30%
    private final static long PROMOTION_RATE_FILTER = 300L;

    //过滤包含关键字
    private final static String[] KEYWORD_FILTER = new String[]{"VIP","视频","会员","季卡","年卡","月卡","话费","充值"};

    //过滤价格(分)
    private final static long PRICE_FILTER = 1000L;

    /**
     * 过滤佣金比例小于30%、不含需屏蔽关键字的商品、券后价小于等于10元
     * @param promotionRate
     * @param goodsName
     * @param price
     * @return
     */
    public static boolean goodsValidate(Long promotionRate, String goodsName, Long price){
        if(promotionRate == null || promotionRate < PROMOTION_RATE_FILTER){
            return true;
        }
        if(StringUtils.isBlank(goodsName)){
            return true;
        }
        for(String str : KEYWORD_FILTER){
            if(goodsName.contains(str)){
                return true;
            }
        }
        if(price == null || price.longValue() <= PRICE_FILTER){
            return true;
        }
        return false;
    }

}
