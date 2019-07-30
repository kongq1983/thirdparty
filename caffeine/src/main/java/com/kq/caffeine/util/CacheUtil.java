package com.kq.caffeine.util;

import com.kq.caffeine.util.DateUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.concurrent.CompletableFuture;

/**
 * CacheUtil
 * @author kq
 * @date 2019-07-29
 */
public class CacheUtil {

    public static String getSyncValue(String key) {

        if(StringUtils.equals(key,"4")) return null;

        String value = "sync:"+ DateUtil.getNowHourMinute()+"_"+key;
        System.out.println("同步从库中读取数据="+value);

        return value;

    }


    public static String getASyncValue(final String key) {

        if(StringUtils.equals(key,"4")){
            try {
                Thread.sleep(3000l);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        return  "async:"+key;

    }


    public static CompletableFuture<String> getASyncValueCompletable(final String key) {

        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000l);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return  "async:"+key;
        });

        return future2;

    }

}
