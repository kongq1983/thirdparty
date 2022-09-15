package com.bytebuddy.demo2;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.MethodDelegation;

import static net.bytebuddy.matcher.ElementMatchers.named;

/**
 * @author kq
 * @date 2022-09-15 16:02
 * @since 2020-0630
 */
public class TargetTest {

    public static void main(String[] args) throws Exception{
        String helloWorld = new ByteBuddy()
                .subclass(Source.class)
                .method(named("hello")).intercept(MethodDelegation.to(Target.class)) // hello方法被代理了 ，执行Target的参数为string类型
                .make()
                .load(TargetTest.class.getClassLoader())
                .getLoaded()
                .newInstance()
                .hello("World");

        System.out.println(helloWorld);

    }

}
