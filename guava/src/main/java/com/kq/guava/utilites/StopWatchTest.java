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

        spentTime = stopwatch.elapsed(TimeUnit.SECONDS);

        System.out.println("sepntTime="+spentTime);


    }

}
