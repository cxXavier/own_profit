package com.xavier.fast.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Inherited
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ApiMethod {

    /**
     * 映射的方法
     * @return
     */
    String method() default "";

    /**
     * 支持的api版本
     * @return
     */
    String version() default "ALL";
}
