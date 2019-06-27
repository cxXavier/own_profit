package com.xavier.fast.annotation.validate;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Target({ ElementType.FIELD, ElementType.METHOD })
public @interface Tel {
	String regex() default "^(13[0,1,2,3,4,5,6,7,8,9]|14[0,1,2,3,4,5,6,7,8,9]|15[0,1,2,3,4,5,6,7,8,9]|17[6,7,8]|18[0,1,2,3,4,5,6,7,8,9])\\d{8}$";

	String message() default "手机号码不正确";
}
