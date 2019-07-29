package com.kq.caffeine.demo;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import java.util.concurrent.TimeUnit;

/**
 * ManualDemo
 * 手动填充
 * @author kq
 * @date 2019-07-29
 */
public class ManualDemo {

    public static final int MAX = 15;
    private int curPos = 0;

    static final Cache<String, String> cache = Caffeine.newBuilder()
            .expireAfterWrite(5, TimeUnit.SECONDS)
            .maximumSize(10)
            .build();

    public ManualDemo(){
        init();
    }

    public void init() {
        for(int i=0;i<MAX;i++) {
            cache.put(String.valueOf(i),String.valueOf(i));
        }
    }

    public void getTest(){

        while (true) {
            curPos++;
            if(curPos>6) break;
            System.out.println(curPos+". ---------------------------------------------------");
            for(int i=0;i<MAX;i++) {
                String value = cache.getIfPresent(String.valueOf(i));
                System.out.println("key="+i+", value="+value);
            }
            try {
                Thread.sleep(1000l);
            } catch (Exception e){
                e.printStackTrace();
            }

        }

    }

    public void putNull(){
//        手动填充： key和value都不能为null， 否则NullPointerException
        try {
            cache.put(null,""); //NullPointerException
        }catch (Exception e) {
            e.printStackTrace();
        }

        try {
            cache.put("",null); //NullPointerException
        }catch (Exception e) {
            e.printStackTrace();
        }

        try {
            cache.put("","");
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String args[]) {
        ManualDemo t = new ManualDemo();
        t.getTest();
        t.putNull();
    }


}
