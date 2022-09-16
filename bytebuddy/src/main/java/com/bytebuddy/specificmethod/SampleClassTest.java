package com.bytebuddy.specificmethod;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.description.modifier.ModifierContributor;
import net.bytebuddy.description.modifier.Visibility;
import net.bytebuddy.dynamic.scaffold.subclass.ConstructorStrategy;
import net.bytebuddy.implementation.MethodCall;

import java.util.Arrays;

/**
 * @author kq
 * @date 2022-09-15 17:17
 * @since 2020-0630
 */
public class SampleClassTest {

    public static void main(String[] args) throws Exception {

//        new ByteBuddy()
//                .subclass(Object.class, ConstructorStrategy.Default.NO_CONSTRUCTORS)
//                .defineConstructor(Arrays.<Class<?>>asList(int.class), Visibility.PUBLIC)
////                .defineConstructor((ModifierContributor.ForMethod) Arrays.<Class<?>>asList(int.class), Visibility.PUBLIC)
//                .intercept(MethodCall.invoke(Object.class.getDeclaredConstructor()))
//                .make();

    }
}
