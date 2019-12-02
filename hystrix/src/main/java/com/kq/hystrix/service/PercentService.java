package com.kq.hystrix.service;

/**
 * PercentService
 *
 * @author kq
 * @date 2019-12-02
 */
public class PercentService {
    public String ex(int seed) {
        System.out.println("seed="+seed);
        if (seed < 10) {
            throw new RuntimeException("抛个异常测试提高异常率！");
        }
        return seed + "想返回吗？";
    }
}
