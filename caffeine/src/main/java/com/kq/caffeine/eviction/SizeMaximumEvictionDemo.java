package com.kq.caffeine.eviction;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import com.kq.caffeine.util.CacheUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * SizeMaximumEvictionDemo
 *
 * @author kq
 * @date 2019-07-30
 */
public class SizeMaximumEvictionDemo {

    private static final int size = 15;

    // Evict based on the number of vertices in the cache
    static LoadingCache<String, String> cache = Caffeine.newBuilder()
            .maximumSize(10)
            .removalListener(((key, value, cause) -> {
                String msg = "remove key=%s,value=%s,cause=%s \n";
                System.out.printf(msg,key,value,cause);
            }))
            .build(key -> CacheUtil.getSyncValue(key));


    public static void main(String[] args) throws Exception{

        List<String> keys = new ArrayList<>();
        for(int i=0;i<size;i++) {
            String val = cache.get(String.valueOf(i));
            System.out.println("1. key="+i+",val="+val);
            keys.add(String.valueOf(i));
        }

        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        Thread.sleep(3000l);

        System.out.println("----------------------------------------------------");

        for(int i=0;i<size;i++) {
            String val = cache.get(String.valueOf(i));
            System.out.println("2. key="+i+",val="+val);
        }

        Map<String,String> loadMap =cache.asMap();
        System.out.println("loadMap="+loadMap+", cache.stat="+cache.stats());

        Map<String,String> map = cache.getAllPresent(keys);
        System.out.println("map="+map+", cache.stat="+cache.stats());

    }

}
