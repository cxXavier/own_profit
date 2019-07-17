package com.xavier.fast.eventbus.subscriber;

import com.google.common.eventbus.Subscribe;
import com.xavier.fast.eventbus.subscriber.base.Subscriber;

public class TestSubscriber extends Subscriber {

    @Subscribe
    @Override
    public void listenEvent(Object obj) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("收到消息:" + obj.toString());
    }
}
