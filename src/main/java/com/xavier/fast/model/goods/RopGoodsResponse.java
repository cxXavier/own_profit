package com.xavier.fast.model.goods;

import java.io.Serializable;

/**
* @Description:    商品
* @Author:         Wang
* @CreateDate:     2019/6/26 17:29
* @UpdateUser:
* @UpdateDate:     2019/6/26 17:29
* @UpdateRemark:
* @Version:        1.0
*/
public class RopGoodsResponse implements Serializable {

    private static final long serialVersionUID = 2403548821287792717L;
    /**
     * 商品ID
     */
    private Long goodsId;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 商品类型
     */
    private String goodsType;

    /**
     * 市场价(分)
     */
    private Long marketPrice;

    /**
     * 券后价(分)
     */
    private Long couponPrice;

    /**
     * 图片链接
     */
    private String imgUrl;

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

    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }

    public Long getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(Long marketPrice) {
        this.marketPrice = marketPrice;
    }

    public Long getCouponPrice() {
        return couponPrice;
    }

    public void setCouponPrice(Long couponPrice) {
        this.couponPrice = couponPrice;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    /**
    * @Description:    商品类型
    * @Author:         Wang
    * @CreateDate:     2019/6/26 17:35
    * @UpdateUser:
    * @UpdateDate:     2019/6/26 17:35
    * @UpdateRemark:
    * @Version:        1.0
    */
    public static enum GOODS_TYPE {
        ELECTRICAL("电器", "ELECTRICAL"),
        FRUITS("水果", "FRUITS"),
        CLOTHING("服装", "CLOTHING");

        private String cnName;

        private String code;

        GOODS_TYPE(String cnName, String code) {
            this.cnName = cnName;
            this.code = code;
        }

        public String getCnName() {
            return cnName;
        }

        public void setCnName(String cnName) {
            this.cnName = cnName;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }
    }
}
