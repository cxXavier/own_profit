package com.xavier.fast.utils;

import com.xavier.fast.annotation.ApiMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

/**
* @Description:    初始化所有service，以便反射调用
* @Author:         Wang
* @CreateDate:     2019/6/25 20:29
* @UpdateUser:
* @UpdateDate:     2019/6/25 20:29
* @UpdateRemark:
* @Version:        1.0
*/
@Configuration
public class RopServiceHandler implements ApplicationContextAware, InitializingBean {

    private Logger log = LoggerFactory.getLogger(RopServiceHandler.class);

    private ApplicationContext applicationContext;

    private static Map<String, Map<String, ServiceMethodHandler>> methodHandlerPool;

    public RopServiceHandler(){
        methodHandlerPool = new HashMap<>();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        //初始化 applicationContext
        try {
            String[] beanNames = this.applicationContext.getBeanNamesForType(Object.class);
            for (final String beanName : beanNames) {
                Class<?> handlerType = this.applicationContext.getType(beanName);
                ReflectionUtils.doWithMethods(handlerType, new ReflectionUtils.MethodCallback() {

                    public void doWith(Method method) throws IllegalArgumentException {
                        ApiMethod apimethod = method.getAnnotation(ApiMethod.class);
                        if(apimethod!=null){
                            ServiceMethodHandler serviceMethodHandler = new ServiceMethodHandler();
                            serviceMethodHandler.setHandler(applicationContext.getBean(beanName)); //handler
                            serviceMethodHandler.setHandlerMethod(method); //handler'method
                            log.info("初始化 服务方法："+apimethod.method()+"| 版本号："+apimethod.version());
                            try {
                                addHandlerMathod(apimethod.method(), apimethod.version(), serviceMethodHandler);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                },new ReflectionUtils.MethodFilter() {
                    public boolean matches(Method method) {
                        return AnnotationUtils.findAnnotation(method, ApiMethod.class)!=null;
                    }
                });
            }

            log.info("共 初始化 "+methodHandlerPool.size()+" 个服务方法");
        } catch(Exception ex){
            ex.printStackTrace();
        }

    }

    public void addHandlerMathod(String method, String version, ServiceMethodHandler handler) throws Exception {

        Map<String, ServiceMethodHandler> map =  methodHandlerPool.get(method);
        if(map==null){
            map = new HashMap<>();
        }

        ServiceMethodHandler targetHandler = map.get(version);

        if(targetHandler != null){
            log.error("重复定义方法和属性:"+ targetHandler.getVersion() + "|"
                    + targetHandler.getClass().getName() + "|" + targetHandler.getHandlerMethod().getName());
            throw new Exception();
        }
        map.put(version, handler);
        methodHandlerPool.put(method, map);
    }

    public ServiceMethodHandler getHandler(String method,String version) throws Exception{
        ServiceMethodHandler h = null;

        if(version==null){
            version = "ALL";
        }

        Map<String,ServiceMethodHandler> map = methodHandlerPool.get(method);

        if(map == null){
            throw new Exception(method+" 未找到");
        }

        h  = map.get(version);

        if(h==null){
            throw new Exception(method + MessageFormat.format(" 对应{0}版本 未找到", h));
        }

        return h;
    }
}
