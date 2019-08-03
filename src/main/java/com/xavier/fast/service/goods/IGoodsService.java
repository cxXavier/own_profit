package com.xavier.fast.service.goods;

import com.xavier.fast.model.base.RopRequestBody;
import com.xavier.fast.model.base.RopResponse;
import com.xavier.fast.model.base.RopResponseBody;
import com.xavier.fast.model.goods.*;

/**
* @Description:    商品服务
* @Author:         Wang
* @CreateDate:     2019/6/25 19:50
* @UpdateUser:
* @UpdateDate:     2019/6/25 19:50
* @UpdateRemark:
* @Version:        1.0
*/
public interface IGoodsService {

    /**
     * 更新推荐商品（置顶的商品池）
     * @author      Wang
     * @param       recommendGoodsRequest
     * @return
     * @exception
     * @date        2019/8/3 11:07
     */
    public RopResponse<RopRecommendGoodsResponse> updateRecommendGoods(
            RopRequestBody<RopRecommendGoodsRequest> recommendGoodsRequest);

    /**
    * 获取商品类目
    * @author      Wang
    * @param       goodsCatsRequest
    * @return
    * @exception
    * @date        2019/6/28 15:05
    */
    public RopResponse<RopResponseBody> getGoodsCats(RopRequestBody<RopGoodsCatsRequest> goodsCatsRequest);

    /**
    * 获取热门商品
    * @author      
    * @param       goodsRequest
    * @return      
    * @exception   
    * @date        2019/6/30 23:03
    */
    public RopResponse<RopResponseBody> getHotGoodsList(RopRequestBody<RopHotGoodsRequest> goodsRequest);

    /**
    * 获取商品列表
    * @author      Wang
    * @param       goodsRequest
    * @return
    * @exception
    * @date        2019/7/1 0:01
    */
    public RopResponse<RopResponseBody> getGoodsList(RopRequestBody<RopGoodsListRequest> goodsRequest);

    /**
    * 获取商品详情
    * @author      Wang
    * @param       goodsRequest
    * @return
    * @exception
    * @date        2019/6/28 15:13
    */
    public RopResponse<RopGoodsDetailResponse> getGoodsDetail(RopRequestBody<RopGoodsDetailRequest> goodsRequest);
}
