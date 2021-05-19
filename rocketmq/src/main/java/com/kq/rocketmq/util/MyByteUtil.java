package com.kq.rocketmq.util;


/**
 * @author kq
 * @date 2020-12-29 18:39
 * @since 2020-0630
 */
public class MyByteUtil {

    public static void print(byte[] bytes) {
        if(bytes==null) return;

        int index = 0;
        for(byte b : bytes) {
            if(b!=0){
                System.out.print(",");
            }
            System.out.print(b);
            index++;
        }
        System.out.println();
    }

}
