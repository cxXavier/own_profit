package com.xavier.fast.schedule.order;

import com.xavier.fast.dao.OrderMapper;
import com.xavier.fast.dao.UserFlowerMapper;
import com.xavier.fast.entity.order.Order;
import com.xavier.fast.entity.pdd.OrderQueryRo;
import com.xavier.fast.entity.pdd.PddOrderInfo;
import com.xavier.fast.entity.pdd.PddOrderList;
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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
* @Description:    同步拼多多订单
* @Author:         Wang
* @CreateDate:     2019/7/17 15:32
* @UpdateUser:
* @UpdateDate:     2019/7/17 15:32
* @UpdateRemark:
* @Version:        1.0
*/
@Component
@Configuration
@EnableScheduling
public class OrderSyncSchedule {

    private Logger log = LoggerFactory.getLogger(OrderSyncSchedule.class);

    private static int PAGE_SIZE = 45;

    private static int PAGE_NUM = 1;

    @Resource
    private IpddService pddService;

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private UserFlowerMapper userFlowerMapper;

    /**
     * 每30秒执行一次
     */
//    @Scheduled(cron = "0 0/30 * * * ?")
    @Scheduled(cron = "0/30 * * * * ?")
    private void orderSyncTasks() {
        log.info("同步拼多多订单开始");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        //当前时间往前推50秒
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(System.currentTimeMillis());
        c.add(Calendar.SECOND, -50);

        Long startTime = c.getTimeInMillis() / 1000;
        Long endTime = System.currentTimeMillis() / 1000;

        OrderQueryRo dto;
        PddOrderList list;
        int updateCount = 0;

        while (true) {
            dto = new OrderQueryRo();
            dto.setStartUpdateTime(startTime);
            dto.setEndUpdateTime(endTime);
            dto.setPageNum(PAGE_NUM);
            dto.setPageSize(PAGE_SIZE);
            list = pddService.queryPddOrder(dto, true);
            if(list == null){
                log.info("暂无拼多多订单，PAGE_NUM=" + PAGE_NUM);
                break;
            }
            if(list.getTotalCount() == null || list.getTotalCount() == 0){
                log.info("暂无拼多多订单，订单总数为0，PAGE_NUM=" + PAGE_NUM);
                break;
            }
            if(CollectionUtils.isEmpty(list.getOrderList())){
                log.info("暂无拼多多订单，订单列表为空，PAGE_NUM=" + PAGE_NUM);
                break;
            }
            Long totalCount = list.getTotalCount();
            log.info("本次查询总数量totalCount为：" + totalCount + "，PAGE_NUM=" + PAGE_NUM
                    + "，currentSize=" + list.getOrderList().size());
            updateCount += dealOrders(list.getOrderList());
            if(list.getOrderList().size() <= PAGE_SIZE){
                break;
            }
            PAGE_NUM++;
        }
        log.info("同步拼多多订单结束，本次共更新" + updateCount + "条数据");
    }

    private int dealOrders(List<PddOrderInfo> pddOrderInfos){
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

    public static void main(String[] args) {
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(System.currentTimeMillis());
        c.add(Calendar.SECOND, -30);
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(c.getTime()));
    }

}
