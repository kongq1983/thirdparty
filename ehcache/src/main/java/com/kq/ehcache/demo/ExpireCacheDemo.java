package com.kq.ehcache.demo;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ExpiryPolicyBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

/**
 * 3s过期
 * Created by qikong on 2019/4/5.
 */
public class ExpireCacheDemo {


    public static void main(String[] args) throws Exception{
        CacheManager cacheManager = CacheManagerBuilder.newCacheManagerBuilder()
                .withCache("mycache",
                        CacheConfigurationBuilder.newCacheConfigurationBuilder
                                (String.class,String.class,
                                        ResourcePoolsBuilder.heap(100)).
                                withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(3)))).build(true);


        Cache<String, String> mycache = cacheManager.getCache("mycache", String.class, String.class);

        mycache.put("name","king");


        for(int i=0;i<5;i++) {
            getValue(mycache);
            TimeUnit.SECONDS.sleep(1);
        }




    }



    public static void getValue(Cache<String,String> cache){
        String value = cache.get("name");
        System.out.println("value="+value);


    }



}
