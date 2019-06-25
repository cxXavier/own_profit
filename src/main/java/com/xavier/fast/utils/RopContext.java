package com.xavier.fast.utils;

import com.xavier.fast.model.base.RopRequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RopContext {

    private RopRequestBody<?> requestData;
    private ServiceMethodHandler serviceHandler;
    private HttpServletRequest request;
    private HttpServletResponse response;

    public RopRequestBody<?> getRequestData() {
        return requestData;
    }

    public void setRequestData(RopRequestBody<?> requestData) {
        this.requestData = requestData;
    }

    public ServiceMethodHandler getServiceHandler() {
        return serviceHandler;
    }

    public void setServiceHandler(ServiceMethodHandler serviceHandler) {
        this.serviceHandler = serviceHandler;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }
}
