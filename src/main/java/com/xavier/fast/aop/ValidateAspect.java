package com.xavier.fast.aop;

import com.xavier.fast.annotation.validate.*;
import com.xavier.fast.common.exception.ValidateException;
import com.xavier.fast.model.base.RopRequestBody;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
* @Description:    参数校验
* @Author:         Wang
* @CreateDate:     2019/6/26 11:58
* @UpdateUser:
* @UpdateDate:     2019/6/26 11:58
* @UpdateRemark:
* @Version:        1.0
*/
@Aspect
@Component
@Order(1)
public class ValidateAspect {

    private Logger logger = LoggerFactory.getLogger(ValidateAspect.class);

    @Before("@annotation(com.xavier.fast.annotation.ApiMethod)")
    public void before(JoinPoint joinPoint) throws ValidateException,
            IllegalAccessException, InvocationTargetException {
        logger.debug("ValidateInterceptor...Object:" + joinPoint.getTarget()
                + ",Method:" + joinPoint.getSignature().getName());
        Object[] objects = joinPoint.getArgs();
        for (Object obj : objects) {
            // 传入的参数为空
            if (null == obj) {
                continue;
            }
            if (obj instanceof RopRequestBody) {
                RopRequestBody<?> ropRequestBody = (RopRequestBody<?>) obj;
                obj = ropRequestBody.getT();
                if (ropRequestBody.getT() != null) {
                    logger.debug(ropRequestBody.getT().toString());
                }
            }
            for (Class<?> clazz = obj.getClass(); clazz != Object.class; clazz = clazz.getSuperclass()) {
                Annotation[] annotations = clazz.getAnnotations();
                // 处理clazz annotation
                handlerClazz(annotations, obj);

                Field[] fields = clazz.getDeclaredFields();
                for (Field field : fields) {
                    Annotation[] fieldAnnotations = field.getAnnotations();
                    // 处理field annotation
                    handlerField(fieldAnnotations, obj, field);
                }

                Method[] methods = clazz.getDeclaredMethods();
                for (Method method : methods) {
                    Annotation[] methodAnnotations = method.getAnnotations();
                    // 处理getter annotation
                    handlerMethod(methodAnnotations, obj, method);
                }
            }
        }
    }

    /**
     * 处理clazz annotation
     * @param annotations
     * @param obj
     * @throws Exception
     */
    public static void handlerClazz(Annotation[] annotations, Object obj) throws ValidateException {
        if (null != annotations && annotations.length > 0) {
            for (Annotation annotation : annotations) {
                validate(annotation, obj);
            }
        }
    }

    /**
     * 处理field annotation
     * @param annotations
     * @param obj
     * @param field
     * @throws Exception
     */
    public static void handlerField(Annotation[] annotations, Object obj, Field field)
            throws ValidateException, IllegalAccessException {
        if (null != annotations && annotations.length > 0) {
            for (Annotation annotation : annotations) {
                field.setAccessible(true);
                Object result = field.get(obj);
                validate(annotation, result);
            }
        }
    }

    /**
     * 处理getter annotation
     *
     * @param annotations
     * @param obj
     * @param method
     * @throws Exception
     */
    public static void handlerMethod(Annotation[] annotations, Object obj, Method method)
            throws ValidateException, InvocationTargetException, IllegalAccessException {
        if (null != annotations && annotations.length > 0) {
            for (Annotation annotation : annotations) {
                Object result = method.invoke(obj);
                validate(annotation, result);
            }
        }
    }

    /**
     * 验证POJO
     * @param annotation
     * @param result
     * @throws Exception
     */
    public static void validate(Annotation annotation, Object result) throws ValidateException {
        if (annotation instanceof NotNull) {
            if (null == result) {
                throw new ValidateException(((NotNull) annotation).message());
            }
            return;
        } else if (annotation instanceof NotEmpty) {
            if (null == result || "" == result.toString()) {
                throw new ValidateException(((NotEmpty) annotation).message());
            }
            return;
        } else if (annotation instanceof Size) {
            int min = ((Size) annotation).min();
            int max = ((Size) annotation).max();
            if (result instanceof String) {
                if (((String) result).length() < min
                        || ((String) result).length() > max) {
                    throw new ValidateException(((Size) annotation).message());
                }
            }
        } else if (annotation instanceof Email) {
            if (null != result) {
                String sResult = (String) result;
                String regex = ((Email) annotation).regex();
                Pattern p = Pattern.compile(regex);
                Matcher m = p.matcher(sResult);
                if (!m.find()) {
                    throw new ValidateException(((Email) annotation).message());
                }
            } else {
                throw new ValidateException(((Email) annotation).message());
            }
        } else if (annotation instanceof Tel) {
            if (null != result) {
                String sResult = (String) result;
                String regex = ((Tel) annotation).regex();
                Pattern p = Pattern.compile(regex);
                Matcher m = p.matcher(sResult);
                if (!m.find()) {
                    throw new ValidateException(((Tel) annotation).message());
                }
            } else {
                throw new ValidateException(((Tel) annotation).message());
            }

        } else if (annotation instanceof Length) {
            int min = ((Length) annotation).min();
            int max = ((Length) annotation).max();
            if (result instanceof String) {
                if (((String) result).length() < min || ((String) result).length() > max) {
                    throw new ValidateException(((Length) annotation).message());
                }
            }
        } else if (annotation instanceof NotZero) {
            if (result instanceof Integer) {
                if ((Integer) result <= 0) {
                    throw new ValidateException(((NotZero) annotation).message());
                }
            } else if (result instanceof Long) {
                if ((Long) result == 0) {
                    throw new ValidateException(((NotZero) annotation).message());
                }
            } else if (result instanceof Float) {
                if ((Float) result == 0) {
                    throw new ValidateException(((NotZero) annotation).message());
                }
            } else if (result instanceof Double) {
                if ((Double) result == 0) {
                    throw new ValidateException(((NotZero) annotation).message());
                }
            }
            return;
        } else if (annotation instanceof Range) {
            int min = ((Range) annotation).min();
            int max = ((Range) annotation).max();
            if (result instanceof Integer) {
                if ((((Integer) result) < min) || ((Integer) result) > max) {
                    throw new ValidateException(((Range) annotation).message());
                }
            } else if (result instanceof Long) {
                if ((((Long) result) < min) || ((Long) result) > max) {
                    throw new ValidateException(((Range) annotation).message());
                }
            } else if (result instanceof Float) {
                if ((((Float) result) < min) || ((Float) result) > max) {
                    throw new ValidateException(((Range) annotation).message());
                }
            } else if (result instanceof Double) {
                if ((((Double) result) < min) || ((Double) result) > max) {
                    throw new ValidateException(((Range) annotation).message());
                }
            }
            return;
        }
    }

}
