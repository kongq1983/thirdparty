package com.bytebuddy.demo2;

/**
 * @author kq
 * @date 2022-09-15 16:01
 * @since 2020-0630
 */
public class Target {

//    public static String hello(String name) {
//        return "Hello " + name + "!";
//    }

    // 名称可以随便，但相同参数类型的不能有多个同时存在
    //  ambiguous delegation of public java.lang.String com.bytebuddy.demo2.Source.hello(java.lang.String)
//    public static String intercept1111(String name) { return "1. Hello " + name + "!"; }

    public static String intercept11112(String name) { return "2. Hello " + name + "!"; }

    public static String intercept(int i) { return Integer.toString(i); }

    public static String intercept(Object o) { return o.toString(); }

}
