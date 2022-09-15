package com.bytebuddy.demo.interf;

import com.bytebuddy.demo1.FooTest;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.ClassFileVersion;
import net.bytebuddy.implementation.DefaultMethodCall;

import static net.bytebuddy.matcher.ElementMatchers.named;

/**
 * @author kq
 * @date 2022-09-15 16:22
 * @since 2020-0630
 */
public class DefaultTest {


    public static void main(String[] args) throws Exception{

        Second second = (Second) new ByteBuddy(ClassFileVersion.JAVA_V8)
                .subclass(Object.class)
                .implement(First.class)
                .implement(Second.class)
                .method(named("qux")).intercept(DefaultMethodCall.prioritize(First.class)) // 执行默认方法
                .make()
                .load(DefaultTest.class.getClassLoader())
                .getLoaded()
                .newInstance();

        // 执行First.qux()
        System.out.println(second.qux());

    }

}
