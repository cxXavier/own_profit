package com.xavier.fast.schedule.order;

import com.xavier.fast.dao.OrderMapper;
import com.xavier.fast.dao.UserFlowerMapper;
import com.xavier.fast.entity.order.Order;
import com.xavier.fast.entity.order.OrderBase;
import com.xavier.fast.entity.pdd.OrderQueryRo;
import com.xavier.fast.entity.pdd.PddOrderInfo;
import com.xavier.fast.entity.pdd.PddOrderList;
import com.xavier.fast.entity.user.UserFlower;
import com.xavier.fast.service.pdd.IpddService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;

@Component
@Configuration
@EnableScheduling
public class UpdateOrderCashStatusSchedule {

    private Logger log = LoggerFactory.getLogger(UpdateOrderCashStatusSchedule.class);

    private static int PAGE_SIZE = 45;

    private static int PAGE_NUM = 1;

    @Resource
    private IpddService pddService;

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private UserFlowerMapper userFlowerMapper;

    /**
     * 每个月25号凌晨2点更新订单的提现状态和鲜花的生效记录
     */
    @Scheduled(cron = "0 0 2 25 * ?")
    private void orderUpdateTasks() {
        log.info("更新开始");
        int updateCount = 0;

        //查询已经结算，并且确认收货时间为上个月内的订单
        Map<String, Object> params = new HashMap<>();
        List<Order> orderList = orderMapper.getSettledAndReceivedOrders(params);
        if(CollectionUtils.isEmpty(orderList)){
            log.info("暂无订单数据");
            return;
        }
        for(Order o : orderList){
            //将订单更新为可提现并添加鲜花记录
            o.setCashBackStatus(OrderBase.ORDER_CASH_STATUS.wait_cash_back.getCode());
            updateCount += updateOrder(o);
        }

        log.info("更新结束，本次共更新" + updateCount + "条数据");
    }


    /**
     * 更新订单
     * @param order
     * @return
     */
    private int updateOrder(Order order){
        int count = orderMapper.update(order);
        if(count > 0){
            log.info("更新订单成功");
            //添加鲜花记录
            int flowerCount = addFlowerRecord(order);
            if(flowerCount > 0){
                log.info("添加鲜花收支记录成功");
            }
        }else{
            log.info("更新订单失败");
        }
        return count;
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
