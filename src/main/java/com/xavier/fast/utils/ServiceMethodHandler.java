package com.xavier.fast.utils;

import java.lang.reflect.Method;

public class ServiceMethodHandler {

    /**
     * 处理器对象
     */
    private Object handler;

    /**
     * 处理器的处理方法
     */
    private Method handlerMethod;

    /**
     * 版本号
     */
    private String version;

    public Object getHandler() {
        return handler;
    }

    public void setHandler(Object handler) {
        this.handler = handler;
    }

    public Method getHandlerMethod() {
        return handlerMethod;
    }

    public void setHandlerMethod(Method handlerMethod) {
        this.handlerMethod = handlerMethod;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
