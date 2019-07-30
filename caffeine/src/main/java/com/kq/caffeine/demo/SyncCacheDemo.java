package com.kq.caffeine.demo;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import com.kq.caffeine.util.CacheUtil;

import java.util.concurrent.TimeUnit;

/**
 * SyncCacheDemo
 * 同步
 * @author kq
 * @date 2019-07-29
 */
public class SyncCacheDemo {

    static LoadingCache<String, String> cache = Caffeine.newBuilder()
            .maximumSize(100)
            .expireAfterWrite(5, TimeUnit.SECONDS)
            .build(k -> CacheUtil.getSyncValue(k));


    public String getValue(String key) {
        return cache.get(key);
    }

    public static void main(String args[]) throws Exception{
        SyncCacheDemo t = new SyncCacheDemo();
        int num = 0;

        while(true) {
            System.out.println(num+". -----------------------------------------------");
            if(num>6) break;
            for (int i = 0; i < 10; i++) {
                System.out.println(t.getValue(String.valueOf(i)));
            }
            Thread.sleep(1000);
            num++;
        }

    }


}
