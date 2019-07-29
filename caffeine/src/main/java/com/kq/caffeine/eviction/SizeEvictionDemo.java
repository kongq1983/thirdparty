package com.kq.caffeine.eviction;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import com.kq.caffeine.demo.CacheUtil;

/**
 * SizeEvictionDemo
 * size-based eviction
 * @author kq
 * @date 2019-07-29
 */
public class SizeEvictionDemo {

    // Evict based on the number of vertices in the cache
    LoadingCache<String, String> graphs = Caffeine.newBuilder()
            .maximumWeight(10)
            .weigher((String key, String value) -> Integer.parseInt(value))  //权重
            .build(key -> CacheUtil.getSyncValue(key));


}
