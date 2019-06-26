package com.xavier.fast.service.pdd;

import com.xavier.fast.entity.pdd.Good;
import com.xavier.fast.entity.pdd.GoodsList;
import com.xavier.fast.entity.pdd.GoodsQueryRo;
import com.xavier.fast.entity.pdd.HotGoodsQueryRo;

import java.util.List;

public interface IpddService {
    //爆款获取商品
    GoodsList.GoodsSearchResponse queryHotGoods(HotGoodsQueryRo goodsQueryRo);
    //通过分类或关键词查询商品列表
    GoodsList.GoodsSearchResponse queryGoodsList(GoodsQueryRo goodsQueryRo);
    //通过商品ID查询商品列表
    List<Good> queryGoodsList(List<String> goodsIdList);
    //商品详情查询
    Good queryGoodDetail(String goodId);
    //更新拼多多订单信息
    void updateOrderMsg();
    //获取推广链接
    void queryGoodsShareUrl();
}
