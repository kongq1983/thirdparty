package com.bytebuddy.accessingfield;

/**
 * @author kq
 * @date 2022-09-16 9:42
 * @since 2020-0630
 */
public interface InterceptionAccessor {

    Interceptor getInterceptor();
    void setInterceptor(Interceptor interceptor);

}
