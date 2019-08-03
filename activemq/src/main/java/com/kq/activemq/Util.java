package com.kq.activemq;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Util
 *
 * @author kq
 * @date 2019-07-31
 */
public class Util {


    public static final String ACTIVEMQ_URL = "tcp://mq.server1.com:61616";

    /**
     * 开始
     * @return
     */
    public static String getNowTime(){

        SimpleDateFormat aDate=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        return aDate.format(new Date());

    }


}
