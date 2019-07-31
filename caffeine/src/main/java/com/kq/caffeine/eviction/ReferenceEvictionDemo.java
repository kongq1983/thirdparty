package com.kq.caffeine.eviction;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import com.kq.caffeine.util.CacheUtil;

/**
 * ReferenceEvictionDemo
 *
 * @author kq
 * @date 2019-07-30
 */
public class ReferenceEvictionDemo {

    private static final int size = 10;


    final static LoadingCache<String, String> cache = Caffeine.newBuilder()
            .softValues()
            .removalListener(((key, value, cause) -> {
                String msg = "remove key=%s,value=%s,cause=%s \n";
                System.out.printf(msg,key,value,cause);
            }))
            .build(key -> CacheUtil.getSyncValue(key));


    public static void main(String[] args) {


        for(int i=0;i<size;i++) {
            String val = cache.get(String.valueOf(i));
            System.out.println("2. key="+i+",val="+val);

            if(i==6||i==8) {
                cache.put(String.valueOf(i),String.valueOf(i));
            }

        }

    }


}
