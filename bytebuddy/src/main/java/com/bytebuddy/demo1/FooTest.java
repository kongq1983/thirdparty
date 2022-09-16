package com.bytebuddy.demo1;

import com.bytebuddy.util.Utils;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.FixedValue;

import static net.bytebuddy.matcher.ElementMatchers.*;

/**
 * @author kq
 * @date 2022-09-15 15:37
 * @since 2020-0630
 */
public class FooTest {

    public static void main(String[] args) throws Exception{

        Foo dynamicFoo = new ByteBuddy()
                .subclass(Foo.class)
                .method(isDeclaredBy(Foo.class)).intercept(FixedValue.value("One!"))
                .method(named("foo")).intercept(FixedValue.value("Two!"))
                .method(named("foo").and(takesArguments(1))).intercept(FixedValue.value("Three!"))
                .make()
                .load(FooTest.class.getClassLoader())
                .getLoaded()
                .newInstance();


        // 打印class字节码
        DynamicType.Unloaded<?> dynamicType = new ByteBuddy()
                .subclass(Foo.class)
                .method(isDeclaredBy(Foo.class)).intercept(FixedValue.value("One!"))
                .method(named("foo")).intercept(FixedValue.value("Two!"))
                .method(named("foo").and(takesArguments(1))).intercept(FixedValue.value("Three!"))
                .make();

        Utils.outClass(dynamicType.getBytes(),"Foo");

                // Cannot return value of type int for public java.lang.String com.bytebuddy.demo1.Foo.foo(java.lang.Object)
//        dynamicFoo = new ByteBuddy()
//                .subclass(Foo.class)
//                .method(isDeclaredBy(Foo.class)).intercept(FixedValue.value(0)) // 会报错  返回类型不匹配
//                .make().load(FooTest.class.getClassLoader())
//                .getLoaded()
//                .newInstance();


        System.out.println(dynamicFoo.bar()); // one
        System.out.println(dynamicFoo.foo()); // two
        System.out.println(dynamicFoo.foo("hello")); // three
        System.out.println(dynamicFoo.toString());

    }

}
