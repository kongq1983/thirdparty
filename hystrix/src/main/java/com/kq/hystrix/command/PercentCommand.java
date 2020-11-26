package com.kq.hystrix.command;

import com.kq.hystrix.service.PercentService;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.strategy.properties.HystrixPropertiesCommandDefault;

/**
 * PercentCommand
 *
 * @author kq
 * @date 2019-12-02
 */
public class PercentCommand extends HystrixCommand<String> {

    private final String group;
    private PercentService service;
    private int seed;

    /**
     *
     *  circuitBreaker.errorThresholdPercentage
     * 设定错误百分比，默认值50%，例如一段时间（10s）内有100个请求，其中有55个超时或者异常返回了，
     * 那么这段时间内的错误百分比是55%，大于了默认值50%，这种情况下触发熔断器-打开


        circuitBreaker.requestVolumeThreshold
        默认值20.意思是至少有20个请求才进行errorThresholdPercentage错误百分比计算。
        比如一段时间（10s）内有19个请求全部失败了。错误百分比是100%，但熔断器不会打开，
        因为requestVolumeThreshold的值是20. 这个参数非常重要

         circuitBreaker.sleepWindowInMilliseconds
         半开试探休眠时间，默认值5000ms。当熔断器开启一段时间之后比如5000ms，
         会尝试放过去一部分流量进行试探，确定依赖服务是否恢复

         metricsRollingPercentileWindowInMilliseconds
         执行时间统计窗口，默认60s

         metricsRollingPercentileWindowBuckets
         执行时间统计窗口Bucket数量，默认6个

         metricsRollingPercentileBucketSize
         执行时间Bucket的最大值，最多保留的数量

     */

    public PercentCommand(String group, int seed) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey(group))
                .andCommandPropertiesDefaults(HystrixPropertiesCommandDefault.Setter()
                        .withCircuitBreakerErrorThresholdPercentage(50)//错误率超过50%,快速失败
                        .withExecutionTimeoutInMilliseconds(2000)
                        .withCircuitBreakerRequestVolumeThreshold(5)
                        .withCircuitBreakerSleepWindowInMilliseconds(10000)//10s后放部分流量过去重试
                        .withMetricsRollingPercentileBucketSize(10)
                        .withMetricsRollingPercentileWindowInMilliseconds(5000)));
        this.group = group;
        this.seed = seed;
    }

    @Override
    protected String run() throws Exception {
        return service.ex(seed);
    }

    public void setService(PercentService service) {
        this.service = service;
    }

    @Override
    protected String getFallback() {
        return seed + "失败了";
    }
}
