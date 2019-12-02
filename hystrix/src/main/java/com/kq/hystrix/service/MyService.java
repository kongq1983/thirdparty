package com.kq.hystrix.service;

/**
 * MyService
 *
 * @author kq
 * @date 2019-12-02
 */
public class MyService {
    public void doSth(String thing) {
        System.out.println("doSth->" + thing + "->begin");
        String threadName = Thread.currentThread().getName();
        System.out.println(threadName + ":doSth->" + thing + "->doing");
        try {
            //这里让线程等待15s，保持线程不释放
            Thread.sleep(15000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("doSth->" + thing + "->end");
    }
}