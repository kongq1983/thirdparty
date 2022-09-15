package com.bytebuddy.demo;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.matcher.ElementMatchers;

/**
 * @author kq
 * @date 2022-09-15 15:19
 * @since 2020-0630
 */
public class HelloWorld {

    public static void main(String[] args) throws Exception {

        Class<?> dynamicType = new ByteBuddy()
                .subclass(Object.class)
                .method(ElementMatchers.named("toString"))
                .intercept(FixedValue.value("Hello World!"))
                .make()
                .load(HelloWorld.class.getClassLoader())
                .getLoaded();

        System.out.println(dynamicType.newInstance().toString());

//        assertThat(dynamicType.newInstance().toString(), is("Hello World!"));

    }

}
