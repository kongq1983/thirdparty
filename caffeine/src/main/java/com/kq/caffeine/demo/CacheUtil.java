package com.kq.caffeine.demo;

import org.apache.commons.lang3.StringUtils;

/**
 * CacheUtil
 * @author kq
 * @date 2019-07-29
 */
public class CacheUtil {

    public static String getSyncValue(String key) {

        if(StringUtils.equals(key,"4")) return null;

        String value = "sync:"+key;
        System.out.println("从库中读取数据="+value);

        return value;

    }

}
