package com.kq.caffeine.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * DateUtil
 * @author kq
 * @date 2019-05-25
 */
public class DateUtil {

    /**
     * 开始
     * @return
     */
    public static String getNowTime(){

        SimpleDateFormat aDate=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        return aDate.format(new Date());

    }

    /**
     * 小时和分
     * @return
     */
    public static String getNowHourMinute(){

        SimpleDateFormat aDate=new SimpleDateFormat("HH:mm:ss");

        return aDate.format(new Date());

    }

}
