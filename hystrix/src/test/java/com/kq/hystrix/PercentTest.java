package com.kq.hystrix;

import com.kq.hystrix.command.PercentCommand;
import com.kq.hystrix.service.PercentService;
import org.junit.Test;
import rx.Observable;
import rx.Subscriber;

import java.util.concurrent.TimeUnit;

/**
 * PercentTest
 *
 * @author kq
 * @date 2019-12-02
 */
public class PercentTest {

    /**
     * 在service里面我们直接抛出异常，test里每隔1秒发出一个请求，前面的5个请求都执行了service的代码，
     * 抛出了异常，到达第五个的时候，熔断器监控到失败率高达100%，那么熔断器打开，执行快速失败，
     * 直接执行getFailBack方法了，配合上面的withCircuitBreakerSleepWindowInMilliseconds（30000），
     * 默认是5s的，也就是30s才会去重试，所以后面的全部快速失败了，试着更改这个值为3000看看，
     * 结果应该不一样
     * @throws Exception
     */
    @Test
    public void test() throws Exception {
        PercentService service = new PercentService();
        startRun(service,0,15);

        TimeUnit.SECONDS.sleep(10);

        startRun(service,0,15);
//        startRun(service,20,30);

    }

    private void startRun(PercentService service,int start,int end) throws InterruptedException {
        for (int i = start; i < end; i++) {
            Thread.sleep(1000);
            PercentCommand command = new PercentCommand("TestGroup", i);
            command.setService(service);
            Observable<String> observable = command.toObservable();
            observable.subscribe(new Subscriber<String>() {
                @Override
                public void onCompleted() {
                    System.out.println("----------the end--------------");
                }

                @Override
                public void onError(Throwable e) {
                    System.out.println("onError: eeeeeeeeeeeeee");
                }

                @Override
                public void onNext(String s) {
                    System.out.println("onNext:"+s+"  isCircuitBreakerOpen="+command.isCircuitBreakerOpen());
                }
            });
        }
    }
}
