package com.xavier.fast.annotation.validate;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
* @Description:    注解用于标识非筛选条件属性
* @Author:         Wang
* @CreateDate:     2019/6/26 11:55
* @UpdateUser:
* @UpdateDate:     2019/6/26 11:55
* @UpdateRemark:
* @Version:        1.0
*/
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Target(ElementType.FIELD)
public @interface NotSelectCondition {

}
