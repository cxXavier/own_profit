package com.xavier.fast.schedule.goods;

import com.alibaba.fastjson.JSONArray;
import com.xavier.fast.dao.GoodsMapper;
import com.xavier.fast.dao.OrderMapper;
import com.xavier.fast.dao.TagMapper;
import com.xavier.fast.entity.goods.Goods;
import com.xavier.fast.entity.order.Order;
import com.xavier.fast.entity.pdd.*;
import com.xavier.fast.entity.tag.Tag;
import com.xavier.fast.service.pdd.IpddService;
import com.xavier.fast.utils.GoodsFilter;
import com.xavier.fast.utils.GoodsUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;

/**
* @Description:    商品同步
* @Author:         Wang
* @CreateDate:     2019/7/23 23:47
* @UpdateUser:
* @UpdateDate:     2019/7/23 23:47
* @UpdateRemark:
* @Version:        1.0
*/
@Component
@Configuration
@EnableScheduling
public class GoodsSyncSchedule {

    private Logger log = LoggerFactory.getLogger(GoodsSyncSchedule.class);

    private final static int HOT_PAGE_SIZE = 200;
    private final static int NORMAL_PAGE_SIZE = 100;

    @Autowired
    private IpddService pddService;

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private TagMapper tagMapper;

    @Resource
    private OrderMapper orderMapper;

    @Scheduled(cron = "0 0/20 * * * ?")
//    @Scheduled(cron = "30 54 11 * * ?")
    private void hotGoodsSyncTasks() {
        log.info("同步拼多多热门商品开始...");
        int pageNum = 1;
        int totalCount = 0;
        int totalPageNum = 100;

//        //获取商品总数量
        HotGoodsQueryRo goodsQueryRo = new HotGoodsQueryRo();
//        goodsQueryRo.setPageNum(pageNum);
//        goodsQueryRo.setPageSize(10);
//        goodsQueryRo.setType(1);
//        HotGoodsList.HotGoodsSearchResponse goodsSearchResponse = pddService.queryHotGoods(goodsQueryRo);
//        if (goodsSearchResponse == null || CollectionUtils.isEmpty(goodsSearchResponse.getGoodsList())) {
//            log.error("hotGoodsSyncTasks fail,暂无热门商品数据");
//            return;
//        }
//        totalCount = goodsSearchResponse.getTotalCount();
//        log.info("热门商品总数量：" + totalCount);
//        if(totalCount <= 0){
//            log.error("hotGoodsSyncTasks fail,totalCount=0");
//            return;
//        }
//        totalPageNum = totalCount % HOT_PAGE_SIZE == 0 ? totalCount / HOT_PAGE_SIZE : (totalCount / HOT_PAGE_SIZE + 1);

        HotGoodsList.HotGoodsSearchResponse goodsSearchResponse;
        List<Goods> goodsList;
        List<Long> goodsIdList;
        Goods goods;
        while (pageNum <= totalPageNum) {
            log.info("pageNum = " + pageNum + ",totalPageNum = " + totalPageNum);
            goodsQueryRo = new HotGoodsQueryRo();
            goodsQueryRo.setPageNum(pageNum);
            goodsQueryRo.setPageSize(HOT_PAGE_SIZE);
            goodsQueryRo.setType(1);
            goodsSearchResponse = pddService.queryHotGoods(goodsQueryRo);
            if (goodsSearchResponse == null || CollectionUtils.isEmpty(goodsSearchResponse.getGoodsList())) {
                log.error("hotGoodsSyncTasks fail,查询热门商品数据失败");
                return;
            }
            List<Good> goodList = goodsSearchResponse.getGoodsList();
            goodsList = new ArrayList<>(HOT_PAGE_SIZE);
            goodsIdList = new ArrayList<>(HOT_PAGE_SIZE);
            for(Good good : goodList){
                goods = new Goods();
                goods = GoodsUtils.copyGoods(good, goods, 1, true);
                if(goods != null){
                    goodsList.add(goods);
                    goodsIdList.add(goods.getGoodsId());
                }
            }
            if(CollectionUtils.isNotEmpty(goodsList)){
                this.insertOrUpdate(goodsList, goodsIdList);
            }
            pageNum++;
        }
        log.info("同步拼多多热门商品结束...");
    }

//    @Scheduled(cron = "0 0/20 * * * ?")
    @Scheduled(cron = "20 15 23 * * ?")
    private void normalGoodsSyncTasks() {
        log.info("同步拼多多普通商品开始...");
        int pageNum = 1;
        int totalCount = 0;
        int totalPageNum = 0;

        //获取商品总数量
        GoodsQueryRo goodsQueryRo = new GoodsQueryRo();
        goodsQueryRo.setSortType(0);
        goodsQueryRo.setPageNum(pageNum);
        goodsQueryRo.setPageSize(10);
        GoodsList.GoodsSearchResponse goodsSearchResponse = pddService.queryGoodsList(goodsQueryRo);
        if (goodsSearchResponse == null || CollectionUtils.isEmpty(goodsSearchResponse.getGoodsList())) {
            log.error("normalGoodsSyncTasks fail,暂无普通商品数据");
            return;
        }
        totalCount = goodsSearchResponse.getTotalCount();
        log.info("normalGoodsSyncTasks普通商品总数量：" + totalCount);
        if(totalCount <= 0){
            log.error("normalGoodsSyncTasks fail,totalCount=0");
            return;
        }
        totalPageNum = totalCount % NORMAL_PAGE_SIZE == 0 ? totalCount / NORMAL_PAGE_SIZE : (totalCount / NORMAL_PAGE_SIZE + 1);
        List<Goods> goodsList;
        List<Long> goodsIdList;
        Goods goods;
        while (pageNum <= totalPageNum) {
            log.info("pageNum = " + pageNum + ",totalPageNum = " + totalPageNum);
            goodsQueryRo = new GoodsQueryRo();
            goodsQueryRo.setPageNum(pageNum);
            goodsQueryRo.setPageSize(NORMAL_PAGE_SIZE);
            goodsQueryRo.setSortType(0);
            goodsSearchResponse = pddService.queryGoodsList(goodsQueryRo);
            if (goodsSearchResponse == null || CollectionUtils.isEmpty(goodsSearchResponse.getGoodsList())) {
                log.error("normalGoodsSyncTasks fail,查询普通商品数据失败");
                return;
            }
            List<Good> goodList = goodsSearchResponse.getGoodsList();
            goodsList = new ArrayList<>(NORMAL_PAGE_SIZE);
            goodsIdList = new ArrayList<>(NORMAL_PAGE_SIZE);
            for(Good good : goodList){
                goods = new Goods();
                goods = GoodsUtils.copyGoods(good, goods, 0, true);
                if(goods != null){
                    goodsList.add(goods);
                    goodsIdList.add(goods.getGoodsId());
                }
            }
            if(CollectionUtils.isNotEmpty(goodsList)){
                this.insertOrUpdate(goodsList, goodsIdList);
            }
            pageNum++;
        }
        log.info("同步拼多多普通商品结束...");
    }

    @Scheduled(cron = "0 30 2 * * ?")
    private void normalGoodsSyncByTagIdTasks() {
        log.info("根据类目ID同步拼多多普通商品开始...");

        Set<Integer> tagIds = new HashSet<>();

        //查询父类目
        Tag tag = new Tag();
        tag.setParentId(0);
        List<Tag> tags = tagMapper.findTagList(tag);
        if(CollectionUtils.isEmpty(tags)){
            log.error("normalGoodsSyncByTagIdTasks查询父类目失败");
            return;
        }

        //查询子类目
        for(Tag t : tags){
            tagIds.add(t.getId());
            tag = new Tag();
            tag.setParentId(t.getId());
            List<Tag> subTags = tagMapper.findTagList(tag);
            if(CollectionUtils.isEmpty(subTags)){
                log.error("normalGoodsSyncByTagIdTasks查询子类目失败");
            }
            for(Tag st : subTags){
                tagIds.add(st.getId());
            }
        }
        if(CollectionUtils.isEmpty(tagIds)){
            log.error("normalGoodsSyncByTagIdTasks查询类目数据失败");
            return;
        }

        //查询拼多多数据
        for(Integer tagId : tagIds){
            log.info("抓取数据tagId=" + tagId);
            int pageNum = 1;
            int totalCount = 0;
            int totalPageNum = 0;

            //获取商品总数量
            GoodsQueryRo goodsQueryRo = new GoodsQueryRo();
            goodsQueryRo.setSortType(0);
            goodsQueryRo.setPageNum(pageNum);
            goodsQueryRo.setPageSize(10);
            goodsQueryRo.setTagId(tagId);
            GoodsList.GoodsSearchResponse goodsSearchResponse = pddService.queryGoodsList(goodsQueryRo);
            if (goodsSearchResponse == null || CollectionUtils.isEmpty(goodsSearchResponse.getGoodsList())) {
                log.error("normalGoodsSyncByTagIdTasks fail,暂无普通商品数据");
                continue;
            }
            totalCount = goodsSearchResponse.getTotalCount();
            log.info("normalGoodsSyncByTagIdTasks普通商品总数量：" + totalCount);
            if(totalCount <= 0){
                log.error("normalGoodsSyncByTagIdTasks fail,totalCount=0");
                continue;
            }
            totalPageNum = totalCount % NORMAL_PAGE_SIZE == 0 ? totalCount / NORMAL_PAGE_SIZE :
                    (totalCount / NORMAL_PAGE_SIZE + 1);
            List<Goods> goodsList;
            List<Long> goodsIdList;
            Goods goods;
            while (pageNum <= totalPageNum) {
                log.info("pageNum = " + pageNum + ",totalPageNum = " + totalPageNum);
                goodsQueryRo = new GoodsQueryRo();
                goodsQueryRo.setPageNum(pageNum);
                goodsQueryRo.setPageSize(NORMAL_PAGE_SIZE);
                goodsQueryRo.setSortType(0);
                goodsQueryRo.setTagId(tagId);
                goodsSearchResponse = pddService.queryGoodsList(goodsQueryRo);
                if (goodsSearchResponse == null || CollectionUtils.isEmpty(goodsSearchResponse.getGoodsList())) {
                    log.error("normalGoodsSyncByTagIdTasks fail,查询普通商品数据失败");
                    pageNum = totalPageNum + 1;
                    continue;
                }
                List<Good> goodList = goodsSearchResponse.getGoodsList();
                goodsList = new ArrayList<>(NORMAL_PAGE_SIZE);
                goodsIdList = new ArrayList<>(NORMAL_PAGE_SIZE);
                for(Good good : goodList){
                    goods = new Goods();
                    goods = GoodsUtils.copyGoods(good, goods, 0, true);
                    if(goods != null){
                        goodsList.add(goods);
                        goodsIdList.add(goods.getGoodsId());
                    }
                }
                if(CollectionUtils.isNotEmpty(goodsList)){
                    this.insertOrUpdate(goodsList, goodsIdList);
                }
                pageNum++;
            }
        }
        log.info("根据类目ID同步拼多多普通商品结束...");
    }

    /**
     * 清除商品数据
     * 背景：商品定时入库，
     */
    @Scheduled(cron = "0 0 1 * * ?")
//    @Scheduled(cron = "0 2 2 * * ?")
    private void deleteGoodsTasks() {
        log.info("清除不满足条件的商品开始...");
        Map<String, Object> params = new HashMap<>();
        int pageNum = 1;
        int startRow;
        List<Goods> goodsList;
        List<String> goodsIds;
        List<Long> needRemoveIds = new ArrayList<>();
        List<Good> pddGoodList;

        GoodsQueryRo goodsQueryRo;
        GoodsList.GoodsSearchResponse goodsSearchResponse;

        Map<Long, Long> goodsMap = new HashMap<>();

        Order record;

        while (true) {
            params.clear();
            goodsMap.clear();
            startRow = pageNum == 1 ? 0 : (pageNum - 1) * NORMAL_PAGE_SIZE;
            params.put("startRow", startRow);
            params.put("endRow", NORMAL_PAGE_SIZE);
            goodsList = goodsMapper.findGoodsListByParams(params);
            if(CollectionUtils.isEmpty(goodsList)){
                break;
            }
            goodsIds = new ArrayList<>();
            for(Goods goods : goodsList){
                goodsIds.add(goods.getGoodsId().toString());
                goodsMap.put(goods.getGoodsId(), goods.getId());
            }
            //根据商品ids查询
            goodsQueryRo = new GoodsQueryRo();
            goodsQueryRo.setGoodsIdList(goodsIds);
            goodsSearchResponse = pddService.queryGoodsList(goodsQueryRo);
            if (goodsSearchResponse == null || CollectionUtils.isEmpty(goodsSearchResponse.getGoodsList())) {
                log.error("deleteGoodsTasks fail,查询普通商品数据失败");
                continue;
            }
            pddGoodList = goodsSearchResponse.getGoodsList();
            for(Good pddGoods : pddGoodList){
                Long priceAfterCoupon = pddGoods.getPriceAfterCoupon();
                if(GoodsFilter.goodsValidate(pddGoods.getPromotionRate(),
                        pddGoods.getGoodsName(), priceAfterCoupon)){
                    //必须是未下单的商品
                    record = new Order();
                    record.setGoodsId(pddGoods.getGoodsId().toString());
                    int orderCount = orderMapper.queryTotalCount(record);
                    Long id = goodsMap.get(pddGoods.getGoodsId());
                    if(id != null && orderCount <= 0){
                        needRemoveIds.add(id);
                    }
                }
                //移除掉已存在，不存在的删除
                goodsMap.remove(pddGoods.getGoodsId());
            }
            if(MapUtils.isNotEmpty(goodsMap)){
                for(Map.Entry entry : goodsMap.entrySet()){
                    needRemoveIds.add((Long)entry.getKey());
                }
            }
            pageNum++;
        }
        if(CollectionUtils.isNotEmpty(needRemoveIds)){
            //分批次删除，一次100个
            int startIndex = 0;
            int endIndex = needRemoveIds.size() > 100 ? 100 : needRemoveIds.size();
            List<Long> removeList;
            int count = 0;
            while (true) {
                removeList = needRemoveIds.subList(startIndex, endIndex);
                startIndex = endIndex;
                endIndex += needRemoveIds.size() > 100 ? 100 : needRemoveIds.size();
                if(endIndex >= needRemoveIds.size()){
                    endIndex = needRemoveIds.size();
                }
                if(CollectionUtils.isEmpty(removeList)){
                    break;
                }
                count += goodsMapper.deleteGoodsByGoodsIds(removeList);
            }
            log.info("本次删除商品【" + count + "】个");
        }
        log.info("清除不满足条件的商品结束...");
    }

    private void insertOrUpdate(List<Goods> goodsList, List<Long> goodsIdList){
        Map<String, Object> params = new HashMap<>();
        params.put("goodsIdList", goodsIdList);
        List<Goods> existGoodsList = goodsMapper.findGoodsListByParams(params);
        //不存在则全量插入
        if(CollectionUtils.isEmpty(existGoodsList)){
            int count = goodsMapper.insertBatch(goodsList);
            log.info("本次插入数据" + count + "条");
            return;
        }
        List<Goods> insertList = new ArrayList<>();
        List<Goods> updateList = new ArrayList<>();
        for(Goods goods : goodsList){
            Long id = this.getId(existGoodsList, goods.getGoodsId());
            if(id != null){
                goods.setId(id);
                updateList.add(goods);
            }else{
                insertList.add(goods);
            }
        }
        if(CollectionUtils.isNotEmpty(insertList)){
            int count = goodsMapper.insertBatch(insertList);
            log.info("本次插入数据" + count + "条");
        }
        if(CollectionUtils.isNotEmpty(updateList)){
            int count = goodsMapper.updateBatch(updateList);
            log.info("本次更新数据" + count + "条");
        }
    }

    private Long getId(List<Goods> existGoodsList, Long goodsId){
        if(CollectionUtils.isEmpty(existGoodsList)){
            return null;
        }
        for(Goods goods : existGoodsList){
            if(goods.getGoodsId().longValue() == goodsId.longValue()){
                return goods.getId();
            }
        }
        return null;
    }

}
