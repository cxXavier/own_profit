package com.xavier.fast.controller;

import com.xavier.fast.redis.RedisClient;
import com.xavier.fast.service.IndexService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/index")
public class IndexController {

    private static final Logger log = LoggerFactory.getLogger(IndexController.class);

    //@Autowired
    private RedisClient redisClient;

    @Autowired
    private IndexService indexService;

    @RequestMapping("/test")
    public void test(){
        String url = "https://api.mch.weixin.qq.com/mmpaymkttransfers/promotion/transfers";
        String mch_appid = "wx5fd5dbb4208a04a4";
        String mchid = "1539680521";
        String device_info = "WEB";

        String nonce_str = "";
    }


}