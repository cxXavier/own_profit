package com.xavier.fast.entity.goods;

import com.xavier.fast.utils.CalFlowerUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
* @Description:    商品
* @Author:         Wangwei
* @CreateDate:     2019/7/23 22:10
* @UpdateUser:
* @UpdateDate:     2019/7/23 22:10
* @UpdateRemark:
* @Version:        1.0
*/
public class Goods implements Serializable {

    private static final long serialVersionUID = 1319211699070719685L;

    private Long id;

    private Long goodsId;   // 商品id

    private String goodsName;  // 商品名称

    private String goodsDesc;   // 商品描述

    private String goodsThumbnailUrl;   // 商品缩略图

    private String goodsImageUrl;   // 商品主图

    private String goodsGalleryUrls;    // 商品轮播图

    private Long soldQuantity;  // 已售卖件数

    private Long minGroupPrice;  // 最小拼团价（单位为分）

    private Long minNormalPrice; // 最小单买价格（单位为分）

    private String mallId;   // 店铺名字

    private String mallName;   // 店铺id

    private String mallRate;   // 店铺评分

    private Long merchantType;   // 店铺类型，1-个人，2-企业，3-旗舰店，4-专卖店，5-专营店，6-普通店

    private Long categoryId; // 商品类目ID，使用pdd.goods.cats.get接口获取

    private String categoryName;   // 商品类目名

    private Long optId;     // 商品标签ID，使用pdd.goods.opts.get接口获取

    private String optName; // 商品标签名

    private Long mallCps;   // 该商品所在店铺是否参与全店推广，0：否，1：是

    private Integer hasCoupon;  // 商品是否有优惠券 true-有，false-没有

    private Long couponMinOrderAmount;   // 优惠券门槛价格，单位为分

    private Long couponDiscount; // 优惠券面额，单位为分

    private Long couponTotalQuantity;    // 优惠券总数量

    private Long couponRemainQuantity; // 优惠券剩余数量

    private Long couponEndTime;    // 优惠券失效时间，UNIX时间戳

    private Long couponStartTime;    // 优惠券生效时间，UNIX时间戳

    private Long promotionRate;     // 佣金比例，千分比

    private Double goodsEvalScore;  // 商品评价分

    private Long goodsEvalCount;   // 商品评价数量

    private Long avgDesc;    // 描述评分

    private Long avgLgst;    // 物流评分

    private Long avgServ;    // 服务评分

    private String catIds;  // 商品类目id

    private Double descPct;    // 描述分击败同类店铺百分比

    private Double lgstPct;    // 物流分击败同类店铺百分比

    private Double servPct;    // 服务分击败同类店铺百分比

    private String optIds;  // 商品标签id

    /**
     * 是否是热门商品
     */
    private Integer isHot;

    /**
     * 是否是普通商品
     */
    private Integer isNormal;

    private Long createAt;   // 创建时间（unix时间戳）

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

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

    private List<Long> goodsIdList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsDesc() {
        return goodsDesc;
    }

    public void setGoodsDesc(String goodsDesc) {
        this.goodsDesc = goodsDesc;
    }

    public String getGoodsThumbnailUrl() {
        return goodsThumbnailUrl;
    }

    public void setGoodsThumbnailUrl(String goodsThumbnailUrl) {
        this.goodsThumbnailUrl = goodsThumbnailUrl;
    }

    public String getGoodsImageUrl() {
        return goodsImageUrl;
    }

    public void setGoodsImageUrl(String goodsImageUrl) {
        this.goodsImageUrl = goodsImageUrl;
    }

    public String getGoodsGalleryUrls() {
        return goodsGalleryUrls;
    }

    public void setGoodsGalleryUrls(String goodsGalleryUrls) {
        this.goodsGalleryUrls = goodsGalleryUrls;
    }

    public Long getSoldQuantity() {
        return soldQuantity;
    }

    public void setSoldQuantity(Long soldQuantity) {
        this.soldQuantity = soldQuantity;
    }

    public Long getMinGroupPrice() {
        return minGroupPrice;
    }

    public void setMinGroupPrice(Long minGroupPrice) {
        this.minGroupPrice = minGroupPrice;
    }

    public Long getMinNormalPrice() {
        return minNormalPrice;
    }

    public void setMinNormalPrice(Long minNormalPrice) {
        this.minNormalPrice = minNormalPrice;
    }

    public String getMallId() {
        return mallId;
    }

    public void setMallId(String mallId) {
        this.mallId = mallId;
    }

    public String getMallName() {
        return mallName;
    }

    public void setMallName(String mallName) {
        this.mallName = mallName;
    }

    public String getMallRate() {
        return mallRate;
    }

    public void setMallRate(String mallRate) {
        this.mallRate = mallRate;
    }

    public Long getMerchantType() {
        return merchantType;
    }

    public void setMerchantType(Long merchantType) {
        this.merchantType = merchantType;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Long getOptId() {
        return optId;
    }

    public void setOptId(Long optId) {
        this.optId = optId;
    }

    public String getOptName() {
        return optName;
    }

    public void setOptName(String optName) {
        this.optName = optName;
    }

    public Long getMallCps() {
        return mallCps;
    }

    public void setMallCps(Long mallCps) {
        this.mallCps = mallCps;
    }

    public Integer getHasCoupon() {
        return hasCoupon;
    }

    public void setHasCoupon(Integer hasCoupon) {
        this.hasCoupon = hasCoupon;
    }

    public Long getCouponMinOrderAmount() {
        return couponMinOrderAmount;
    }

    public void setCouponMinOrderAmount(Long couponMinOrderAmount) {
        this.couponMinOrderAmount = couponMinOrderAmount;
    }

    public Long getCouponDiscount() {
        return couponDiscount;
    }

    public void setCouponDiscount(Long couponDiscount) {
        this.couponDiscount = couponDiscount;
    }

    public Long getCouponTotalQuantity() {
        return couponTotalQuantity;
    }

    public void setCouponTotalQuantity(Long couponTotalQuantity) {
        this.couponTotalQuantity = couponTotalQuantity;
    }

    public Long getCouponRemainQuantity() {
        return couponRemainQuantity;
    }

    public void setCouponRemainQuantity(Long couponRemainQuantity) {
        this.couponRemainQuantity = couponRemainQuantity;
    }

    public Long getCouponEndTime() {
        return couponEndTime;
    }

    public void setCouponEndTime(Long couponEndTime) {
        this.couponEndTime = couponEndTime;
    }

    public Long getCouponStartTime() {
        return couponStartTime;
    }

    public void setCouponStartTime(Long couponStartTime) {
        this.couponStartTime = couponStartTime;
    }

    public Long getPromotionRate() {
        return promotionRate;
    }

    public void setPromotionRate(Long promotionRate) {
        this.promotionRate = promotionRate;
    }

    public Double getGoodsEvalScore() {
        return goodsEvalScore;
    }

    public void setGoodsEvalScore(Double goodsEvalScore) {
        this.goodsEvalScore = goodsEvalScore;
    }

    public Long getGoodsEvalCount() {
        return goodsEvalCount;
    }

    public void setGoodsEvalCount(Long goodsEvalCount) {
        this.goodsEvalCount = goodsEvalCount;
    }

    public Long getAvgDesc() {
        return avgDesc;
    }

    public void setAvgDesc(Long avgDesc) {
        this.avgDesc = avgDesc;
    }

    public Long getAvgLgst() {
        return avgLgst;
    }

    public void setAvgLgst(Long avgLgst) {
        this.avgLgst = avgLgst;
    }

    public Long getAvgServ() {
        return avgServ;
    }

    public void setAvgServ(Long avgServ) {
        this.avgServ = avgServ;
    }

    public String getCatIds() {
        return catIds;
    }

    public void setCatIds(String catIds) {
        this.catIds = catIds;
    }

    public Double getDescPct() {
        return descPct;
    }

    public void setDescPct(Double descPct) {
        this.descPct = descPct;
    }

    public Double getLgstPct() {
        return lgstPct;
    }

    public void setLgstPct(Double lgstPct) {
        this.lgstPct = lgstPct;
    }

    public Double getServPct() {
        return servPct;
    }

    public void setServPct(Double servPct) {
        this.servPct = servPct;
    }

    public String getOptIds() {
        return optIds;
    }

    public void setOptIds(String optIds) {
        this.optIds = optIds;
    }

    public Integer getIsHot() {
        return isHot;
    }

    public void setIsHot(Integer isHot) {
        this.isHot = isHot;
    }

    public Integer getIsNormal() {
        return isNormal;
    }

    public void setIsNormal(Integer isNormal) {
        this.isNormal = isNormal;
    }

    public Long getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Long createAt) {
        this.createAt = createAt;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

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
        return minGroupPrice - couponMinOrderAmount;
    }

    public void setPriceAfterCoupon(Long priceAfterCoupon) {
        this.priceAfterCoupon = priceAfterCoupon;
    }

    public List<Long> getGoodsIdList() {
        return goodsIdList;
    }

    public void setGoodsIdList(List<Long> goodsIdList) {
        this.goodsIdList = goodsIdList;
    }
}
