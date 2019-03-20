package com.kq.guava.ratelimter.demo1;

import com.google.common.util.concurrent.RateLimiter;

/**
 * MyData
 *
 * @author kq
 * @date 2019-03-19
 */
public class CommonData {

    RateLimiter rateLimiter = RateLimiter.create(1);

    public void call() {

        String name = Thread.currentThread().getName();

//        System.out.println(name+" before call");

        rateLimiter.acquire();

//        if(rateLimiter.tryAcquire(1)) {


        System.out.println(name + " is called.");

//        }

    }

}
