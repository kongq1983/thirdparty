package com.kq.ehcache.demo;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ExpiryPolicyBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.units.MemoryUnit;

import java.time.Duration;

/**
 * Created by qikong on 2019/4/5.
 */
public class PersistentDemo {

    public static final String SAVE_PATH = "/Users/qikong/ehcache/cache";


    static CacheManager cacheManager = CacheManagerBuilder.newCacheManagerBuilder()
            .withCache("mycache",
                    CacheConfigurationBuilder.newCacheConfigurationBuilder
                            (String.class,String.class,
                                    ResourcePoolsBuilder.heap(100).disk(100, MemoryUnit.MB,true)).
                            withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(30000))))
            .with(CacheManagerBuilder.persistence(SAVE_PATH)).build(true);


    public static void saveData() {
        Cache<String, String> mycache = cacheManager.getCache("mycache", String.class, String.class);

        for(int i=0;i<200;i++) {
            mycache.put("name"+i,"v"+i);
        }

    }


    public static void main(String[] args) {


//            saveData();


        Cache<String, String> mycache = cacheManager.getCache("mycache", String.class, String.class);

        System.out.println(mycache.get("name1"));
        System.out.println(mycache.get("name120"));
        System.out.println(mycache.get("name168"));


        cacheManager.close();


    }


}
