package com.kq.okhttp3.util;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/**
 * OkHttpUtilTest1
 *
 * @author kq
 * @date 2019-11-18
 */
public class OkHttpUtilTest1 {

    public static final int size = 2000;
    private static CountDownLatch countDownLatch = new CountDownLatch(size);


    public static void main(String[] args) throws Exception{
        String url = "http://localhost:10001/helloworld";

        Runnable r = ()-> {
            try{
                String result = OkHttpUtil.post(url,"{}");
                System.out.println(Thread.currentThread().getName()+",得到返回值:"+result);
            }catch (Exception e){
                e.printStackTrace();
            } finally {
                countDownLatch.countDown();
            }

        };

        for(int i=0;i<size;i++) {
            new Thread(r,""+i).start();
        }


        System.out.println("-----------------开始等待--------------------");
        countDownLatch.await();
        System.out.println("-----------------结束等待--------------------");
    }


}
