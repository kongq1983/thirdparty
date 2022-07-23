package com.kq.asm.demo.annotation;

/**
 * @author kq
 * @date 2022-07-23 11:04
 * @since 2020-0630
 */
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value={ElementType.TYPE,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface  Encode {

    String value();

}