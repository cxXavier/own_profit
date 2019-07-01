package com.xavier.fast.service.goods;

import com.xavier.fast.model.base.RopRequestBody;
import com.xavier.fast.model.base.RopResponse;
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
    * 获取商品类目
    * @author      Wang
    * @param       goodsCatsRequest
    * @return
    * @exception
    * @date        2019/6/28 15:05
    */
    public RopResponse<RopGoodsCatsResponse> getGoodsCats(RopRequestBody<RopGoodsCatsRequest> goodsCatsRequest);

    /**
    * 获取热门商品
    * @author      
    * @param       goodsRequest
    * @return      
    * @exception   
    * @date        2019/6/30 23:03
    */
    public RopResponse<RopGoodsListResponse> getHotGoodsList(RopRequestBody<RopHotGoodsRequest> goodsRequest);

    /**
    * 获取商品列表
    * @author      Wang
    * @param       goodsRequest
    * @return
    * @exception
    * @date        2019/7/1 0:01
    */
    public RopResponse<RopGoodsListResponse> getGoodsList(RopRequestBody<RopGoodsListRequest> goodsRequest);

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
