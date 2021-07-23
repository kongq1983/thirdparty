package com.kq.guava.eventbus.demo1;

/**
 * @author kq
 * @date 2021-07-23 13:39
 * @since 2020-0630
 */
import com.google.common.eventbus.Subscribe;

/**
 * Created by zhangzh on 2017/1/10.
 */
public class DataObserver1 {

    /**
     * 只有通过@Subscribe注解的方法才会被注册进EventBus
     * 而且方法有且只能有1个参数
     *
     * @param msg
     */
    @Subscribe
    public void func(String msg) {
        System.out.println("DataObserver1 String msg: " + msg);
    }

    @Subscribe
    public void func(Integer msg) {
        System.out.println("DataObserver1 Integer msg: " + msg);
    }

}