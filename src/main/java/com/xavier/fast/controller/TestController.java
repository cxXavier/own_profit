package com.xavier.fast.controller;

import com.xavier.fast.dao.OrderMapper;
import com.xavier.fast.entity.order.Order;
import com.xavier.fast.service.push.IPushService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/test")
public class TestController {

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private IPushService pushService;

    @RequestMapping("/push")
    @ResponseBody
    public String pushTest(String bizType){
        Order order = orderMapper.selectByPrimaryKey(177);
        pushService.wechatPush(order, bizType);
        return "success";
    }
}
