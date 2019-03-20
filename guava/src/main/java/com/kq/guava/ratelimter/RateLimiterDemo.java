package com.kq.guava.ratelimter;

import com.google.common.util.concurrent.RateLimiter;

/**
 * RateLimiterDemo
 *
 * @author kq
 * @date 2019-03-19
 */
public class RateLimiterDemo {

    public static void main(String[] args) {

        RateLimiter rateLimiter = RateLimiter.create(2);
        System.out.println(rateLimiter.acquire(5));
        System.out.println(rateLimiter.acquire(2));
        System.out.println(rateLimiter.acquire(1));

    }

}
