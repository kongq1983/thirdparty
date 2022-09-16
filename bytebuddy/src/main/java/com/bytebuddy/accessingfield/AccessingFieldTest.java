package com.bytebuddy.accessingfield;

import com.bytebuddy.util.Utils;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.description.modifier.Visibility;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.FieldAccessor;
import net.bytebuddy.implementation.MethodDelegation;

import static net.bytebuddy.matcher.ElementMatchers.isDeclaredBy;
import static net.bytebuddy.matcher.ElementMatchers.not;

/**
 * @author kq
 * @date 2022-09-16 9:44
 * @since 2020-0630
 */
public class AccessingFieldTest {

    public static void main(String[] args) throws Exception {

        test1();

        classGenerate();

    }


    public static void test1() throws Exception {

        Class<? extends UserType> dynamicUserType = new ByteBuddy()
                .subclass(UserType.class)
                .method(not(isDeclaredBy(Object.class)))
                .intercept(MethodDelegation.toField("interceptor"))
                .defineField("interceptor", Interceptor.class, Visibility.PRIVATE)
                .implement(InterceptionAccessor.class).intercept(FieldAccessor.ofBeanProperty())
                .make()
                .load(AccessingFieldTest.class.getClassLoader())
                .getLoaded();

//        UserType userType =  dynamicUserType.newInstance();
//        System.out.println(userType.doSomething());


        InstanceCreator factory = new ByteBuddy()
                .subclass(InstanceCreator.class)
                .method(not(isDeclaredBy(Object.class)))
//                .intercept(MethodDelegation.construct(dynamicUserType)) // 原始
                .intercept(MethodDelegation.toConstructor(dynamicUserType))
                .make()
                .load(dynamicUserType.getClassLoader())
                .getLoaded().newInstance();

        Object o = factory.makeInstance();

        System.out.println(o);
    }

    public static void classGenerate() {

        Class<? extends UserType> dynamicUserType = new ByteBuddy()
                .subclass(UserType.class)
                .method(not(isDeclaredBy(Object.class)))
                .intercept(MethodDelegation.toField("interceptor"))
                .defineField("interceptor", Interceptor.class, Visibility.PRIVATE)
                .implement(InterceptionAccessor.class).intercept(FieldAccessor.ofBeanProperty())
                .make()
                .load(AccessingFieldTest.class.getClassLoader())
                .getLoaded();


//        Utils.outClass(dynamicUserType.getBytes(),"InstanceCreator");

        DynamicType.Unloaded<?> dynamicType = new ByteBuddy()
                .subclass(InstanceCreator.class)
                .method(not(isDeclaredBy(Object.class)))
//                .intercept(MethodDelegation.construct(dynamicUserType)) // 原始
                .intercept(MethodDelegation.toConstructor(dynamicUserType))
                .make();


        Utils.outClass(dynamicType.getBytes(),"InstanceCreator");

    }

}
