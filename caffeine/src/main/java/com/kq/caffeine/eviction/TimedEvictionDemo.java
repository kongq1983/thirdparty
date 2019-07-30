package com.kq.caffeine.eviction;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.Expiry;
import com.github.benmanes.caffeine.cache.LoadingCache;
import com.kq.caffeine.util.CacheUtil;
import com.kq.caffeine.util.DateUtil;

import java.util.concurrent.TimeUnit;

/**
 * TimedEvictionDemo
 *
 * @author kq
 * @date 2019-07-30
 */
public class TimedEvictionDemo {

    private static final int size = 10;

    static final LoadingCache<String, String> cache = Caffeine.newBuilder()

            .expireAfter(new Expiry<String, String>() {

                public long expireAfterCreate(String key, String graph, long currentTime) {
                    // Use wall clock time, rather than nanotime, if from an external resource
                    String msg = "expireAfterCreate key=%s,value=%s,currentTime=%s \n";
                    System.out.printf(msg,key,graph,currentTime);

                    return TimeUnit.SECONDS.toNanos(5);
                }
                public long expireAfterUpdate(String key, String graph,
                                              long currentTime, long currentDuration) {

                   String msg = "expireAfterUpdate key=%s,value=%s,currentTime=%s,currentDuration=%s \n";

                    System.out.printf(msg,key,graph,currentTime,currentDuration);

                    return currentDuration;
                }

                public long expireAfterRead(String key, String graph,
                                            long currentTime, long currentDuration) {

                    String msg = "expireAfterRead key=%s,value=%s,currentTime=%s,currentDuration=%s \n";

                    System.out.printf(msg,key,graph,currentTime,currentDuration);

                    return currentDuration;
                }
            })
            .removalListener(((key, value, cause) -> {
                String msg = "remove date=%s,key=%s,value=%s,cause=%s \n";
                System.out.printf(msg, DateUtil.getNowTime(),key,value,cause);
            }))
            .build(k -> CacheUtil.getSyncValue(k));


    public static void main(String[] args) throws Exception{

//        for(int i=0;i<size;i++) {
//            String val = cache.get(String.valueOf(i));
//            System.out.println("2. key="+i+",val="+val);
//
//            if(i==6||i==8) {
//                cache.put(String.valueOf(i),String.valueOf(i));
//            }
//
//        }


        String msg = cache.get("1");
        System.out.println("msg="+msg);

        Thread.sleep(8000);

        msg = cache.get("2");
        System.out.println("msg="+msg);


    }


}
