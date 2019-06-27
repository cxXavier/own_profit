package com.xavier.fast.annotation.validate;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Target({ ElementType.TYPE,ElementType.FIELD,ElementType.METHOD })
public @interface Pattern {
	String regex();
	String message() default "匹配失败";
}
