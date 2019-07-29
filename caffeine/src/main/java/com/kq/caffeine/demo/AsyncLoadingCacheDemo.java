package com.kq.caffeine.demo;

import com.github.benmanes.caffeine.cache.AsyncCache;
import com.github.benmanes.caffeine.cache.AsyncLoadingCache;
import com.github.benmanes.caffeine.cache.Caffeine;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * AsyncLoadingCacheDemo
 *
 * @author kq
 * @date 2019-07-29
 */
public class AsyncLoadingCacheDemo {

    static AsyncLoadingCache<String, String> cache = Caffeine.newBuilder()
            .maximumSize(100)
            .expireAfterWrite(1, TimeUnit.MINUTES)
            .buildAsync(k -> CacheUtil.getASyncValue(k)); //k -> CacheUtil.getASyncValue(k)



    public static void main(String[] args) throws Exception{

        String key = "4";
        CompletableFuture<String> f = cache.get(key);
        System.out.println(f.get());

        System.out.println("---------------------------");
    }

}
