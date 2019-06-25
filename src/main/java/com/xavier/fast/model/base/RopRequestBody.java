package com.xavier.fast.model.base;

import java.io.Serializable;

public class RopRequestBody<T> implements Serializable {

    private T t ;

    public T getT(){
        return t ;
    }

    public void setT(T t){
        this.t = t ;
    }
}
