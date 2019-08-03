package com.xavier.fast.dao;

import com.xavier.fast.common.mybatis.MyBatisDao;
import com.xavier.fast.entity.goods.Goods;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class GoodsMapper extends MyBatisDao {

    public GoodsMapper() {
        super("TBL_GOODS");
    }

    public int insert(Goods record){
        return super.insert("insert", record);
    }

    public int insertSelective(Goods record){
        return super.insert("insertSelective", record);
    }

    public Goods selectByPrimaryKey(Integer id) {
        return super.get("selectByPrimaryKey", id);
    }

    public int queryTotalCount(Goods record) {
        return super.get("queryTotalCount", record);
    }

    public List<Goods> findGoodsList(Goods record) {
        return super.queryForList("findGoodsList", record);
    }

    public List<Goods> findGoodsListByParams(Map<String, Object> params) {
        return super.queryForList("findGoodsListByParams", params);
    }

    public int update(Goods record){
        return super.update("update", record);
    }

    public int insertBatch(List<Goods> goodsList){
        return super.insert("insertBatch", goodsList);
    }

    public int updateBatch(List<Goods> goodsList){
        return super.update("updateBatch", goodsList);
    }

    public List<Goods> findRecommendGoodsList(Goods record) {
        return super.queryForList("findRecommendGoodsList", record);
    }

    public int insertBatchRecommend(List<Goods> goodsList){
        return super.insert("insertBatchRecommend", goodsList);
    }

    public int deleteAllRecommendGoods(Map<String, Object> params){
        return super.delete("deleteAllRecommendGoods", params);
    }
}