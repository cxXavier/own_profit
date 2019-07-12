package com.xavier.fast.schedule.order;

import com.xavier.fast.dao.OrderMapper;
import com.xavier.fast.dao.UserFlowerMapper;
import com.xavier.fast.entity.order.Order;
import com.xavier.fast.entity.pdd.OrderQueryRo;
import com.xavier.fast.entity.pdd.PddOrderInfo;
import com.xavier.fast.entity.pdd.PddOrderList;
import com.xavier.fast.entity.user.UserFlower;
import com.xavier.fast.service.pdd.IpddService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

//@Component
//@Configuration
//@EnableScheduling
public class OrderSyncSchedule {

    private Logger log = LoggerFactory.getLogger(OrderSyncSchedule.class);

    @Resource
    private IpddService pddService;

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private UserFlowerMapper userFlowerMapper;

//    @Scheduled(cron = "0/5 * * * * ?")
    //或直接指定时间间隔，例如：5秒
    //@Scheduled(fixedRate=5000)
    private void configureTasks() {
        log.info("更新开始");
        //查询一天之内需要更新的订单

        OrderQueryRo dto = new OrderQueryRo();
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2019-07-11 21:00:00");
            dto.setStartUpdateTime(date.getTime() / 1000);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        dto.setEndUpdateTime(System.currentTimeMillis() / 1000);
        dto.setPageNum(1);
        dto.setPageSize(20);
        PddOrderList list = pddService.queryPddOrder(dto, true);
        int updateCount = dealOrders(list);
        log.info("更新结束，本次共更新" + updateCount + "条数据");
    }

    private int dealOrders(PddOrderList list){
        if(list == null){
            log.info("暂无拼多多订单");
            return 0;
        }
        if(list.getTotalCount() == null || list.getTotalCount() == 0){
            log.info("暂无拼多多订单，订单总数为0");
            return 0;
        }
        List<PddOrderInfo> pddOrderInfos = list.getOrderList();
        if(CollectionUtils.isEmpty(pddOrderInfos)){
            log.info("暂无拼多多订单，订单列表为空");
            return 0;
        }
        int count = 0;
        for(PddOrderInfo po : pddOrderInfos){
            String orderId = po.getCustomParameters();
            if(StringUtils.isBlank(orderId)){
                log.info("自定义参数为空");
                continue;
            }
            Order order = orderMapper.selectByPrimaryKey(Integer.parseInt(orderId));
            if(order == null){
                log.info("订单不存在，订单号orderId=" + orderId);
                continue;
            }
            if(StringUtils.isBlank(order.getOrderStatus())){
                //第一次直接更新
                count += updateOrder(po, order);
            }else if(!order.getOrderStatus().equals(po.getOrderStatus().toString())){
                //订单状态变更-更新订单
                count += updateOrder(po, order);
            }else{
                log.info("订单状态未变更，无需更新，order.getOrderStatus()=" + order.getOrderStatus()
                + ",po.getOrderStatus()=" + po.getOrderStatus());
            }
        }

        return count;
    }

    /**
     * 更新订单
     * @param po
     * @param order
     * @return
     */
    private int updateOrder(PddOrderInfo po, Order order){
        order.setPddOrderId(po.getOrderSn());
        order.setOrderStatus(po.getOrderStatus().toString());
        order.setGoodsName(po.getGoodsName());
        order.setGoodsPrice(po.getGoodsPrice());
        order.setGoodsQuantity(po.getGoodsQuantity());
        order.setGoodsThumbnailUrl(po.getGoodsThumbnailUrl());
        order.setOrderAmount(po.getOrderAmount());
        order.setOrderCreateTime(getDate(po.getOrderCreateTime()));
        order.setOrderModifyAt(getDate(po.getOrderModifyAt()));
        order.setOrderPayTime(getDate(po.getOrderPayTime()));
        order.setOrderReceiveTime(getDate(po.getOrderReceiveTime()));
        order.setOrderSettleTime(getDate(po.getOrderSettleTime()));
        order.setOrderStatusDesc(po.getOrderStatusDesc());
        order.setOrderVerifyTime(getDate(po.getOrderVerifyTime()));
        order.setPromotionAmount(po.getPromotionAmount());
        order.setPromotionRate(po.getPromotionRate());
        order.setReturnStatus(po.getReturnStatus());
        int count = orderMapper.update(order);
        if(count > 0){
            log.info("更新订单成功");
            //已生效，添加鲜花记录
            if("3".equals(order.getOrderStatus())){
                int flowerCount = addFlowerRecord(order);
                if(flowerCount > 0){
                    log.info("添加鲜花收支记录成功");
                }
            }
        }
        return count;
    }

    /**
    * 日期转换
    * @author      Wang
    * @param       millSec
    * @return
    * @exception
    * @date        2019/7/10 18:50
    */
    private Date getDate(Long millSec){
        if(millSec == null || millSec < 0){
            return null;
        }
        return new Date(millSec * 1000);
    }

    /**
     * 添加鲜花收支记录
     * @param order
     * @return
     */
    private int addFlowerRecord(Order order){
        UserFlower uf = new UserFlower();
        uf.setOpenId(order.getOpenId());
        uf.setUnioinId(order.getUnionId());
        uf.setParentOpenId(order.getParentOpenId());
        uf.setParentUnionId(order.getParentUnionId());
        uf.setFlowers(order.getContributionFlower());
        uf.setCostType(UserFlower.COST_TYPE.INCREASE.name());
        uf.setCreateTime(new Date());
        int count = userFlowerMapper.insertSelective(uf);
        return count;
    }

}
