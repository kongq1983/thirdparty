package com.bytebuddy.demo1;

/**
 * @author kq
 * @date 2022-09-15 15:36
 * @since 2020-0630
 */
public class Foo {

    public String bar() { return null; }
    public String foo() { return null; }
    public String foo(Object o) { return null; }

}

// 最终生成类
//public class Foo$ByteBuddy$OF2hPviB extends Foo {
//    public String bar() {
//        return "One!";
//    }
//
//    public String foo(Object o) {
//        return "Three!";
//    }
//
//    public String foo() {
//        return "Two!";
//    }
//
//    public Foo$ByteBuddy$OF2hPviB() {
//    }
//}