package com.xavier.fast.schedule.order;

import com.xavier.fast.dao.OrderMapper;
import com.xavier.fast.dao.UserFlowerMapper;
import com.xavier.fast.entity.order.Order;
import com.xavier.fast.entity.order.OrderBase;
import com.xavier.fast.entity.user.UserFlower;
import com.xavier.fast.push.wechat.template.TemplateMsg;
import com.xavier.fast.service.push.IPushService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Configuration
@EnableScheduling
public class UpdateOrderCashStatusSchedule {

    private Logger log = LoggerFactory.getLogger(UpdateOrderCashStatusSchedule.class);

    private final static int PAGE_SIZE = 50;

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private UserFlowerMapper userFlowerMapper;

    @Resource
    private IPushService pushService;

    /**
     * 每个月25号凌晨2点更新订单的提现状态和鲜花的生效记录
     */
    @Scheduled(cron = "0 0 2 25 * ?")
    private void orderUpdateTasks() {
        log.info("更新开始");
        int updateCount = 0;
        int pageNum = 0;

        //查询已经结算，并且确认收货时间为上个月内的订单
        Map<String, Object> params = new HashMap<>();
        int totalCount = orderMapper.getSettledAndReceivedOrdersCount(params);
        if(totalCount <= 0){
            log.info("暂无订单数据，totalCount=0");
            return;
        }
        int totalPage = totalCount % PAGE_SIZE == 0 ? totalCount / PAGE_SIZE : (totalCount / PAGE_SIZE + 1);
        while(pageNum <= totalPage){
            int startRow = pageNum * PAGE_SIZE;
            params.put("startRow", startRow);
            params.put("endRow", PAGE_SIZE);
            log.info("orderUpdateTasks pageNum=" + pageNum + ",startRow=" + startRow);
            List<Order> orderList = orderMapper.getSettledAndReceivedOrders(params);
            if(CollectionUtils.isEmpty(orderList)){
                log.info("暂无订单数据，orderList为空");
                return;
            }
            log.info("orderUpdateTasks listSize=" + orderList.size());
            for(Order o : orderList){
                //将订单更新为可提现并添加鲜花记录
                o.setCashBackStatus(OrderBase.ORDER_CASH_STATUS.wait_cash_back.getCode());
                updateCount += updateOrder(o);
            }
            pageNum++;
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
            //推送结算成功消息
            wechatPushCreateOrderMsg(order);
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
        //如果parentOpenId为空，表示自购下单，不添加鲜花记录，只添加徒弟贡献鲜花
        if(StringUtils.isNotBlank(order.getParentOpenId())){
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
        return 0;
    }

    /**
     * 小程序下单成功推送(异步)
     * @param order
     */
    private void wechatPushCreateOrderMsg(Order order){
        //如果parentOpenId为空，表示自购下单，不为空，为徒弟下单
        if(StringUtils.isBlank(order.getParentOpenId())){
            pushService.wechatPush(order, TemplateMsg.WECHAT_PUSH_TYPE.OWN_SETTLED.name());
        }else{
            pushService.wechatPush(order, TemplateMsg.WECHAT_PUSH_TYPE.PRENTICE_SETTLED.name());
        }
    }

}
