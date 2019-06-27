package com.xavier.fast.annotation.validate;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE,ElementType.FIELD,ElementType.METHOD })
public @interface NotNull {
	String message() default "对象不能为空";
}
