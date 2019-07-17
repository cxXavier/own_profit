package com.xavier.fast.eventbus;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import com.xavier.fast.eventbus.subscriber.TestSubscriber;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class TestEventBus extends AsyncEventBus {


    public TestEventBus(Executor executor) {
        super(executor);
    }

    public static void main(String[] args) {
        Executor executor = Executors.newFixedThreadPool(3);
        TestEventBus eventBus = new TestEventBus(executor);
        TestSubscriber subscriber = new TestSubscriber();
        eventBus.register(subscriber);
        eventBus.post("11111");
        System.out.println("eventbus 结束");
    }
}
