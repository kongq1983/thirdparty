package com.bytebuddy.demo.interf;

/**
 * @author kq
 * @date 2022-09-15 16:21
 * @since 2020-0630
 */
public interface Second {

    default String qux() { return "BAR"; }

}
