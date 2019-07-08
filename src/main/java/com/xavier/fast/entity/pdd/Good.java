package com.xavier.fast.entity.pdd;

import com.alibaba.fastjson.annotation.JSONField;
import com.xavier.fast.utils.CalFlowerUtils;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author yirenjie
 * createDate:  2018/11/8
 */
@NoArgsConstructor
@Data
public class Good implements Serializable {
    @JSONField(name = "create_at")
    private Long createAt;   // 创建时间（unix时间戳）

    @JSONField(name = "goods_id")
    private Long goodsId;   // 商品id

    @JSONField(name = "goods_name")
    private String goodsName;  // 商品名称

    @JSONField(name = "goods_desc")
    private String goodsDesc;   // 商品描述

    @JSONField(name = "goods_thumbnail_url")
    private String goodsThumbnailUrl;   // 商品缩略图

    @JSONField(name = "goods_image_url")
    private String goodsImageUrl;   // 商品主图

    @JSONField(name = "goods_gallery_urls")
    private List<String> goodsGalleryUrls;    // 商品轮播图

    @JSONField(name = "sold_quantity")
    private Long soldQuantity;  // 已售卖件数

    @JSONField(name = "min_group_price")
    private Long minGroupPrice;  // 最小拼团价（单位为分）

    @JSONField(name = "min_normal_price")
    private Long minNormalPrice; // 最小单买价格（单位为分）

    @JSONField(name = "mall_id")
    private String mallId;   // 店铺名字

    @JSONField(name = "mall_name")
    private String mallName;   // 店铺id

    @JSONField(name = "mall_rate")
    private String mallRate;   // 店铺评分

    @JSONField(name = "merchant_type")
    private Long merchantType;   // 店铺类型，1-个人，2-企业，3-旗舰店，4-专卖店，5-专营店，6-普通店

    @JSONField(name = "category_id")
    private Long categoryId; // 商品类目ID，使用pdd.goods.cats.get接口获取

    @JSONField(name = "category_name")
    private String categoryName;   // 商品类目名

    @JSONField(name = "opt_id")
    private Long optId;     // 商品标签ID，使用pdd.goods.opts.get接口获取

    @JSONField(name = "opt_name")
    private String optName; // 商品标签名

    @JSONField(name = "mall_cps")
    private Long mallCps;   // 该商品所在店铺是否参与全店推广，0：否，1：是

    @JSONField(name = "has_coupon")
    private boolean hasCoupon;  // 商品是否有优惠券 true-有，false-没有

    @JSONField(name = "coupon_min_order_amount")
    private Long couponMinOrderAmount;   // 优惠券门槛价格，单位为分

    @JSONField(name = "coupon_discount")
    private Long couponDiscount; // 优惠券面额，单位为分

    @JSONField(name = "coupon_total_quantity")
    private Long couponTotalQuantity;    // 优惠券总数量

    @JSONField(name = "coupon_remain_quantity")
    private Long couponRemainQuantity; // 优惠券剩余数量

    @JSONField(name = "coupon_end_time")
    private Long couponEndTime;    // 优惠券失效时间，UNIX时间戳

    @JSONField(name = "coupon_start_time")
    private Long couponStartTime;    // 优惠券生效时间，UNIX时间戳

    @JSONField(name = "promotion_rate")
    private Long promotionRate;     // 佣金比例，千分比

    @JSONField(name = "goods_eval_score")
    private double goodsEvalScore;  // 商品评价分

    @JSONField(name = "goods_eval_count")
    private Long goods_eval_count;   // 商品评价数量

    @JSONField(name = "avg_desc")
    private Long avgDesc;    // 描述评分

    @JSONField(name = "avg_lgst")
    private Long avgLgst;    // 物流评分

    @JSONField(name = "avg_serv")
    private Long avgServ;    // 服务评分

    @JSONField(name = "cat_ids")
    private List<Integer> catIds;  // 商品类目id

    @JSONField(name = "desc_pct")
    private double descPct;    // 描述分击败同类店铺百分比

    @JSONField(name = "lgst_pct")
    private double lgstPct;    // 物流分击败同类店铺百分比

    @JSONField(name = "serv_pct")
    private double servPct;    // 服务分击败同类店铺百分比

    @JSONField(name = "opt_ids")
    private List<Integer> optIds;  // 商品标签id

    /**
     * 贡献鲜花数
     */
    private Integer contributionFlower;

    /**
     * 提现需要鲜花数
     */
    private Integer cashCostFlower;

    /**
     * 券后价
     */
    private Long priceAfterCoupon;

    public Integer getContributionFlower() {
        return CalFlowerUtils.calContributionFlower(getPriceAfterCoupon());
    }

    public void setContributionFlower(Integer contributionFlower) {
        this.contributionFlower = contributionFlower;
    }

    public Integer getCashCostFlower() {
        return CalFlowerUtils.calCashFlower(getPriceAfterCoupon());
    }

    public void setCashCostFlower(Integer cashCostFlower) {
        this.cashCostFlower = cashCostFlower;
    }

    public Long getPriceAfterCoupon() {
        return minNormalPrice - couponDiscount;
    }

    public void setPriceAfterCoupon(Long priceAfterCoupon) {
        this.priceAfterCoupon = priceAfterCoupon;
    }
}
