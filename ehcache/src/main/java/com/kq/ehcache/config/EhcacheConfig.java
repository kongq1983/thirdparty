package com.kq.ehcache.config;

import com.kq.ehcache.demo.PersistentDemo;
import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.units.MemoryUnit;

/**
 * Created by qikong on 2019/4/6.
 */
public class EhcacheConfig {

    public static final String SAVE_PATH = "/Users/qikong/ehcache/cache";


    CacheManager cacheManager = CacheManagerBuilder.newCacheManagerBuilder()
            .with(CacheManagerBuilder.persistence(PersistentDemo.SAVE_PATH)).build(true);

    private Object initialObject = new Object();



    public <K,V> Cache<K,V> getCache(String cacheName,Class<K> k,Class<V> v){

        Cache<K,V> cache = cacheManager.getCache(cacheName,k,v);

        if(cache==null) {
            synchronized (initialObject) {

                cache = cacheManager.getCache(cacheName,k,v);

                if (cache==null){
                    cache = cacheManager.createCache(cacheName,
                            CacheConfigurationBuilder.newCacheConfigurationBuilder(k, v,
                                    ResourcePoolsBuilder.heap(1)
                                            .offheap(10, MemoryUnit.MB).
                                                disk(100,MemoryUnit.MB,true)).build());

                }

            }
        }


        return cache;

    }



}
