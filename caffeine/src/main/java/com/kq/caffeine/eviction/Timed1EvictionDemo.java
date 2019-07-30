package com.kq.caffeine.eviction;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import com.kq.caffeine.util.CacheUtil;
import com.kq.caffeine.util.DateUtil;

import java.util.concurrent.TimeUnit;

/**
 * Timed1EvictionDemo
 *
 * @author kq
 * @date 2019-07-30
 */
public class Timed1EvictionDemo {

    static final LoadingCache<String, String> cache = Caffeine.newBuilder()
            .expireAfterWrite(5, TimeUnit.SECONDS)
            .removalListener(((key, value, cause) -> {
                String msg = "remove date=%s,key=%s,value=%s,cause=%s \n";
                System.out.printf(msg, DateUtil.getNowTime(),key,value,cause);
            }))
            .build(k -> CacheUtil.getSyncValue(k));


    public static void main(String[] args) throws Exception{
        String msg = cache.get("1");

        System.out.println("msg="+msg);

        Thread.sleep(8000l);

        msg = cache.get("2");

        System.out.println("msg="+msg);


    }


}
