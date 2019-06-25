package com.xavier.fast.utils;

import com.xavier.fast.model.base.RopRequest;
import com.xavier.fast.model.base.RopRequestBody;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

@Configuration
public class RopContextFilter implements InitializingBean {

    private Logger log = LoggerFactory.getLogger(RopServiceHandler.class);

    @Autowired
    RopServiceHandler ropServiceHandler;

    @Override
    public void afterPropertiesSet() throws Exception {
//        String method = getRequest().getParameter("method");
//        String version = getRequest().getParameter("version");
//        ServiceMethodHandler handler = null;
//        handler = ropServiceHandler.getHandler(method, version);
//                .getInvocationContext().getParameters();
//        RopRequestBody<?> o = this.buildParams(handler, parameterMap);
//        log.debug("method:" + method + "|version:" +version);
//        RopContext rc = new RopContext();
//        rc.setRequestData(o);
//        rc.setServiceHandler(handler);
//        rc.setRequest(getRequest());
//        rc.setResponse(getResponse());
//
//        invocation.getInvocationContext().getParameters()
//                .put("ropContext", rc);
//        return invocation.invoke();
    }

    private RopRequestBody<?> buildParams(ServiceMethodHandler handler,
                                          final Map<String, Object> parameterMap)
            throws InstantiationException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException {
        Type[] typs = handler.getHandlerMethod().getGenericParameterTypes();
        Type t = typs[0];
        Class<?> obj = null;
        if (t instanceof ParameterizedType)/**//* 如果是泛型类型 */{
            Type[] types = ((ParameterizedType) t).getActualTypeArguments();// 泛型类型列表
            for (Type type : types) {
                obj = (Class<?>) type;
            }
        }
        Object object = obj;
        object = obj.newInstance();

        final RopRequest requestBody = (RopRequest) object;

        RopRequestBody<Object> body  = null;
        try {
            BeanUtils.populate(requestBody, parameterMap);
            body = new RopRequestBody<Object>();
            body.setT(requestBody);

        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return body;
    }

    private HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    private HttpServletResponse getResponse() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
    }
}
