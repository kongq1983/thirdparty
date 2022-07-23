package com.kq.asm.demo.annotation;

/**
 * @author kq
 * @date 2022-07-23 11:03
 * @since 2020-0630
 */
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface  Table {

    public String name();

}