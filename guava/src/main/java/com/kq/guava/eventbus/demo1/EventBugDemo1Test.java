package com.kq.guava.eventbus.demo1;

/**
 * @author kq
 * @date 2021-07-23 13:40
 * @since 2020-0630
 */
public class EventBugDemo1Test {


    public static void main(String[] args) throws InterruptedException {

        DataObserver1 observer1 = new DataObserver1();
        DataObserver2 observer2 = new DataObserver2();

        EventBusCenter.register(observer1);
        EventBusCenter.register(observer2);

        System.out.println("============   start  ====================");

        // 只有注册的参数类型为String的方法会被调用
        EventBusCenter.post("post string method");
        EventBusCenter.post(123);

        System.out.println("============ after unregister ============");
        // 注销observer2
        EventBusCenter.unregister(observer2);
        EventBusCenter.post("post string method");
        EventBusCenter.post(123);

        System.out.println("============    end           =============");
    }

}
