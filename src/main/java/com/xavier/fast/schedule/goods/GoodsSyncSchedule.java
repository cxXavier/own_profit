package com.xavier.fast.schedule.goods;

import com.xavier.fast.dao.GoodsMapper;
import com.xavier.fast.dao.TagMapper;
import com.xavier.fast.entity.goods.Goods;
import com.xavier.fast.entity.pdd.*;
import com.xavier.fast.entity.tag.Tag;
import com.xavier.fast.model.base.RopResponse;
import com.xavier.fast.service.pdd.IpddService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

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

    private final static String[] KEYWORD_FILTER = new String[]{"VIP","视频","会员","季卡","年卡","月卡","话费","充值"};

    @Autowired
    private IpddService pddService;

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private TagMapper tagMapper;

//    @Scheduled(cron = "0 0/20 * * * ?")
    private void hotGoodsSyncTasks() {
        log.info("同步拼多多热门商品开始...");
        int pageNum = 1;
        int totalCount = 0;
        int totalPageNum = 0;

        //获取商品总数量
        HotGoodsQueryRo goodsQueryRo = new HotGoodsQueryRo();
        goodsQueryRo.setPageNum(pageNum);
        goodsQueryRo.setPageSize(10);
        goodsQueryRo.setType(1);
        HotGoodsList.HotGoodsSearchResponse goodsSearchResponse = pddService.queryHotGoods(goodsQueryRo);
        if (goodsSearchResponse == null || CollectionUtils.isEmpty(goodsSearchResponse.getGoodsList())) {
            log.error("hotGoodsSyncTasks fail,暂无热门商品数据");
            return;
        }
        totalCount = goodsSearchResponse.getTotalCount();
        log.info("热门商品总数量：" + totalCount);
        if(totalCount <= 0){
            log.error("hotGoodsSyncTasks fail,totalCount=0");
            return;
        }
        totalPageNum = totalCount % HOT_PAGE_SIZE == 0 ? totalCount / HOT_PAGE_SIZE : (totalCount / HOT_PAGE_SIZE + 1);
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
                goods = copyGoods(good, goods, 1);
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
                goods = copyGoods(good, goods, 0);
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

    @Scheduled(cron = "20 20 22 * * ?")
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
                    goods = copyGoods(good, goods, 0);
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
     * 商品copy
     * @param source
     * @param target
     * @return
     */
    private Goods copyGoods(Good source, Goods target, int goodsType){
        //过滤佣金比例小于30%、不含需屏蔽关键字的商品
        if(source.getPromotionRate() == null || source.getPromotionRate() < 300
                || containFilterKeyword(source.getGoodsName())){
            return null;
        }
        target.setGoodsId(source.getGoodsId());
        target.setGoodsName(source.getGoodsName());
        target.setGoodsDesc(source.getGoodsDesc());
        target.setGoodsThumbnailUrl(source.getGoodsThumbnailUrl());
        target.setGoodsImageUrl(source.getGoodsImageUrl());
        target.setGoodsGalleryUrls(this.convertStrListToString(source.getGoodsGalleryUrls()));
        target.setSoldQuantity(source.getSoldQuantity());
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
        target.setAvgDesc(source.getAvgDesc());
        target.setAvgLgst(source.getAvgLgst());
        target.setAvgServ(source.getAvgServ());
        target.setCatIds(this.convertIntListToString(source.getCatIds()));
        target.setDescPct(source.getDescPct());
        target.setLgstPct(source.getLgstPct());
        target.setServPct(source.getServPct());
        target.setOptIds(this.convertIntListToString(source.getOptIds()));
        if(goodsType == 1){
            target.setIsHot(1);
        }else{
            target.setIsNormal(1);
        }
        target.setCreateAt(source.getCreateAt());
        target.setCreateTime(new Date());
        return target;
    }

    private String convertStrListToString(List<String> strList){
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

    private String convertIntListToString(List<Integer> intList){
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

    private boolean containFilterKeyword(String goodsName){
        if(StringUtils.isBlank(goodsName)){
            return false;
        }
        for(String str : KEYWORD_FILTER){
            if(goodsName.contains(str)){
                return true;
            }
        }
        return false;
    }

}
