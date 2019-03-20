package com.kq.guava.ratelimter.demo1;

/**
 * RateLimterDemo1
 *
 * @author kq
 * @date 2019-03-19
 */
public class RateLimterDemo1 {

    public static void main(String[] args) {
        CommonData data = new CommonData();

        Runnable r = () -> data.call();

        Thread[] t = new Thread[100];

        for(int i=0;i<t.length;i++) {
            t[i] = new Thread(r,"thread"+i);
            t[i].start();
        }

        try {
            Thread.sleep(Integer.MAX_VALUE);
        }catch (Exception e){
            e.printStackTrace();
        }

    }



}
