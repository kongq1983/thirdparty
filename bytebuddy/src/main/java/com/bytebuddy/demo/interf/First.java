package com.bytebuddy.demo.interf;

/**
 * @author kq
 * @date 2022-09-15 16:21
 * @since 2020-0630
 */
public interface First {

    default String qux() { return "FOO"; }

}
