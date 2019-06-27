package com.xavier.fast.model.base;

import java.io.Serializable;

/**
* @Description:    请求实体
* @Author:         Wang
* @CreateDate:     2019/6/26 10:34
* @UpdateUser:
* @UpdateDate:     2019/6/26 10:34
* @UpdateRemark:
* @Version:        1.0
*/
public class RopRequestBody<T> implements Serializable {

    private static final long serialVersionUID = -7388576053303070750L;

    private T t ;

    public T getT(){
        return t ;
    }

    public void setT(T t){
        this.t = t ;
    }
}
