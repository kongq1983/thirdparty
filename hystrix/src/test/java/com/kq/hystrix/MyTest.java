package com.kq.hystrix;

import com.kq.hystrix.command.MyCommand;
import com.kq.hystrix.service.MyService;
import org.junit.Test;
import rx.Observable;
import rx.Subscriber;


/**
 * MyTest
 *
 * @author kq
 * @date 2019-12-02
 */
public class MyTest {

    /**
     * 测试线程池满了
     * 可以看到从第10个开始就失败了，因为线程池的10个线程已经被全部占满了
     * @throws Exception
     */
    @Test
    public void test() throws Exception {
        MyService service = new MyService();

        for (int i = 0; i < 15; i++) {
            Thread.sleep(1000);
            MyCommand command = new MyCommand("TestGroup", "fishing" + (i * i));
            command.setService(service);
            Observable<String> observable = command.toObservable();
            observable.subscribe(new Subscriber<String>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {
                    System.out.println("执行command发生错误！");
                    e.printStackTrace();
                }

                @Override
                public void onNext(String s) {
                    System.out.println(s);
                }
            });
        }
    }
}
