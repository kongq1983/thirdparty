package com.kq.guava.utilites;

import com.google.common.base.Stopwatch;

import java.util.concurrent.TimeUnit;

/**
 * Created by qikong on 2019/3/23.
 */
public class StopWatchTest {

    public static void main(String[] args) throws Exception{
        Stopwatch stopwatch = Stopwatch.createStarted();

        Thread.sleep(1000l);

        long spentTime = stopwatch.elapsed(TimeUnit.SECONDS);

        System.out.println("spenTime="+spentTime);

        stopwatch.reset();
        stopwatch.start();
        TimeUnit.SECONDS.sleep(2);

        spentTime = stopwatch.stop().elapsed(TimeUnit.SECONDS);

        System.out.println("sepntTime="+spentTime);

        stopwatch.reset(); // 不reset 开始时间从最初  或者上次reset的时候  开始计算
        stopwatch.start();
        TimeUnit.SECONDS.sleep(3);

        spentTime = stopwatch.stop().elapsed(TimeUnit.SECONDS);;

        System.out.println("spentTime="+spentTime);


    }

}
