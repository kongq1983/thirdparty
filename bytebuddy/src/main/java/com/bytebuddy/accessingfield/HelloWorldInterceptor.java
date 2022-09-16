package com.bytebuddy.accessingfield;

/**
 * @author kq
 * @date 2022-09-16 9:46
 * @since 2020-0630
 */
public class HelloWorldInterceptor implements Interceptor {

    @Override
    public String doSomethingElse() {
        return "Hello World!";
    }

}
