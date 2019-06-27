package com.xavier.fast.aop;

import com.xavier.fast.model.base.RopRequest;
import com.xavier.fast.model.base.RopRequestBody;
import com.xavier.fast.utils.RopContext;
import com.xavier.fast.utils.RopServiceHandler;
import com.xavier.fast.utils.ServiceMethodHandler;
import org.apache.commons.beanutils.BeanUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

/**
* @Description:    RopContext初始化
* @Author:         Wang
* @CreateDate:     2019/6/26 10:27
* @UpdateUser:
* @UpdateDate:     2019/6/26 10:27
* @UpdateRemark:
* @Version:        1.0
*/
@Component
@Aspect
public class RopContextInitAspect {

    private Logger log = LoggerFactory.getLogger(RopContextInitAspect.class);

    @Autowired
    RopServiceHandler ropServiceHandler;

    @Before("@annotation(com.xavier.fast.annotation.RopContextInit)")
    public void before(JoinPoint joinPoint) throws Throwable {
        String method = getRequest().getParameter("method");
        String version = getRequest().getParameter("version");
        Map<String, String[]> parameterMap = getRequest().getParameterMap();
        ServiceMethodHandler handler = ropServiceHandler.getHandler(method, version);
        //构建请求参数
        RopRequestBody<?> o = this.buildParams(handler, parameterMap);
        log.debug("method:" + method + "|version:" +version);
        //初始化赋值
        RopContext rc = (RopContext) joinPoint.getArgs()[0];
        rc.setRequestData(o);
        rc.setServiceHandler(handler);
        rc.setRequest(getRequest());
        rc.setResponse(getResponse());
    }

    /**
    * 构建请求参数
    * @author      Wang
    * @param       handler
    * @param       parameterMap
    * @return
    * @exception   InstantiationException
    * @exception   IllegalAccessException
    * @exception   IllegalArgumentException
    * @date        2019/6/26 10:30
    */
    private RopRequestBody<?> buildParams(ServiceMethodHandler handler,
                                          final Map<String, String[]> parameterMap)
            throws InstantiationException, IllegalAccessException, IllegalArgumentException{
        Type[] typs = handler.getHandlerMethod().getGenericParameterTypes();
        Type t = typs[0];
        Class<?> obj = null;
        if (t instanceof ParameterizedType)/**//* 如果是泛型类型 */{
            Type[] types = ((ParameterizedType) t).getActualTypeArguments();// 泛型类型列表
            for (Type type : types) {
                obj = (Class<?>) type;
            }
        }
        Object object = obj.newInstance();

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
