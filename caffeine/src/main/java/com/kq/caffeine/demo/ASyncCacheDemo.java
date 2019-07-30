package com.kq.caffeine.demo;

import com.github.benmanes.caffeine.cache.AsyncCache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.kq.caffeine.util.CacheUtil;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * ASyncCacheDemo
 *
 * @author kq
 * @date 2019-07-29
 */
public class ASyncCacheDemo {

    static AsyncCache<String, String> cache = Caffeine.newBuilder()
            .maximumSize(100)
            .expireAfterWrite(1, TimeUnit.MINUTES)
//            .buildAsync(k -> CacheUtil.getASyncValue(k)); //k -> CacheUtil.getASyncValue(k)
            .buildAsync(); //k -> CacheUtil.getASyncValue(k)



    public static void main(String[] args) throws Exception{

        String key = "one";
        CompletableFuture<String> f = cache.get(key, k -> CacheUtil.getASyncValue(k));
        System.out.println(f.get());

    }




}
