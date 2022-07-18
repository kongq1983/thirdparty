package com.kq.asm.demo.helloworld;

/**
 * @author kq
 * @date 2022-07-18 15:16
 * @since 2020-0630
 */
public class HelloWorldRun {

    public static void main(String[] args) throws Exception {
        MyClassLoader classLoader = new MyClassLoader();
        Class<?> clazz = classLoader.loadClass("sample.HelloWorld");
        Object instance = clazz.newInstance();
        System.out.println(instance);
    }

}
