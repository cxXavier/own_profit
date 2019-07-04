package com.xavier.fast.aop;

import com.xavier.fast.common.exception.ValidateException;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
* @Description:    接口token校验
* @Author:         Wang
* @CreateDate:     2019/7/3 16:18
* @UpdateUser:
* @UpdateDate:     2019/7/3 16:18
* @UpdateRemark:
* @Version:        1.0
*/
@Component
@Aspect
@Order(1)
public class TokenValidateAspect {

    private Logger log = LoggerFactory.getLogger(TokenValidateAspect.class);

    private static final String MD5_SALT = "Vkd0U1UxSkdWbFZYYldoYVZtMDRlRmt5TVhOTmF6" +
            "VllVbXhTYVdGc1dtRldhazVPVFZkSmVsSnVUazlXYlhoM1ZUTndWazFYU2xWWmVrWlBWMFZh" +
            "UjFSclVsTlNSazQyVm14d2JGWnRZM2hXUldoSFpXczFWVlp1YUZOV1JscG9Wa1JLTTAxV2JG" +
            "ZGFSV3hQWWtkNFNWVXdWVFZOVmxZMlZtNWFWVTFyV2tOYVJrNHdWa1UxV1ZGcVJtaFhSbG8y" +
            "VmtSR2EyRXlSWGRQVmxKUFVqSjNlRlpZY0dGaU1sSllVbXR3YUUxc1duTlhWbVJ2VlVkR1ZX" +
            "SklaR3RXVkd4RFdWWk9NR0ZHVFhwUmJYaHBWbFZ3ZVZONlJrNU5SMUpaVm01Q1dHSlVSWGhX" +
            "YWtKTFlqQnplVkpzYUdwTlJHd3dWakJvVjA1V1NraGpSelZVWVRKb01GbHJhRXRTVjBwSVkw" +
            "WndUMDFxVlhsV01uUnZUVzFTY21OSVdrNVNSbG96V2xaV2MxUnNXa2hqU0ZKcFVrWndXbGt3" +
            "YUVOa01rcDBUVlJHVkdWVWJIcFpNRll3WWxkS1NHRkhhR3hpVkVaM1dURmFiMk15UmxaaVNH" +
            "eHBZbFJHY0ZwSE1ERmtSMGw0Vlc1R1lVMUhlRFZaYTJSM1V6RndkR1JFUm1wWFNFSXhXV3hq" +
            "TlZaWFNraGpla3BZVWpOb00xWXhaR0ZrTVc5NFlrY3hhMkpzY0V4Wk1qRXdUVlpzVmxWdVVt" +
            "Rk5TR2Q1V1ZST1YyRkdiSFJQV0d4cVlURktlbGx0ZUhkU1YwVjZWbXR3YW1KWVVYaFpha3BM" +
            "WkVkR1ZtTkZiR2xpVkVZeVZtdGpOVTFzYkZoVGJrWmhUVWQzZVZsVVRsZFRiRXBJVFZoT2FV" +
            "MXNiM2xhUjNSelRtMUtkVlpVUWsxTmFrWXlWa1ZrZDJNeVRuVlJWRTVyWW14d1MxcFhNSGhs" +
            "Vm14WFZXNVNhRmRGV2xwWk1HUnJXVlpXU0dSRVJsVlRSWEF5V1d4YWQyVlhTa2hhUm5CaFls" +
            "UkdlbGt5ZEZOa01rcEZXa1JLYUdKWFVrdFZNVkYzVUZFOVBRPT0=";

    @Before("@annotation(com.xavier.fast.annotation.TokenValidate)")
    public void before(JoinPoint joinPoint) throws Throwable{
        HttpServletRequest request = getRequest();
        String sign = request.getParameter("pinkeKey");
        if (StringUtils.isBlank(sign)) {
            throw new ValidateException("请求参数不合法！");
        }
        Map<String, String[]> parameterMap = request.getParameterMap();
        String originSign = getSign(parameterMap);
        String expectSign = DigestUtils.md5Hex(originSign + MD5_SALT);
        boolean checkResult = sign.equals(expectSign);
        if(!checkResult) {
            throw new ValidateException("请求参数不合法！");
        }
    }

    private static String getSign(Map<String, String[]> parameterMap) {
        List<String> keys = new ArrayList<>(parameterMap.keySet());
        keys.remove("pinkeKey");

        Collections.sort(keys); //键值ASCII码递增排序

        StringBuilder sb = new StringBuilder();
        for (String key : keys) {
            Object value = parameterMap.get(key);
            if (value == null) {
                continue;
            }
            if (value.getClass().isArray()) {
                for (int i = 0; i < Array.getLength(value); i++) {
                    String item = Array.get(value, i).toString();
                    sb.append(key).append('=').append(item).append('&');
                }
            } else if(value instanceof List) {
                List<Object> items = (List<Object>) value;
                for (Object item : items) {
                    sb.append(key).append('=').append(item.toString()).append('&');
                }
            } else {
                String str = value.toString();
                sb.append(key).append('=').append(str).append('&');
            }
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    private HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

//    public static void main(String[] args) {
//        Map<String, String[]> sParaTemp = new HashMap<String, String[]>();
//        String query = "adultAmts=0&adultAmts=0&adultQuantities=2&adultQuantities=2&baseAdultQuantity=0" +
//                "&baseChildQuantity=0&categoryIds=16&categoryIds=3&childAmts=0&childAmts=0&childQuantities=0" +
//                "&childQuantities=0&combTickets=N&combTickets=N&contactEmail=nima@qq.com&contactMobile=13671500680" +
//                "&contactName=妮玛&couponCode=&departureDates=&departureDates=&detailIds=&detailIds=&goodsIds=2335441" +
//                "&goodsIds=967616&goodsTypes=&goodsTypes=&itemRelationVos=MAIN&itemRelationVos=RELATION" +
//                "&loadAdultQuantity=2&loadChildQuantity=0&lvsessionid=f5c31f46-8629-46f7-b9e3-59f1553b5de1_18715505" +
//                "&lvversion=7.5.0&method=api.com.route.order.createOrder&packageTypeFlag=false&productId=611924" +
//                "&quantities=1&quantities=2&routeBizType=AROUNDLINE&travellerBirths=&travellerBirths=1989-09-18" +
//                "&travellerEmails=atdawn@foxmail.com&travellerEmails=&travellerFirstNames=&travellerFirstNames=sjj" +
//                "&travellerGenders=&travellerGenders=MAN&travellerIdNos=310105198801112018" +
//                "&travellerIdNos=310XXXXXXXXXXX2018&travellerIdTypes=ID_CARD&travellerIdTypes=GANGAO" +
//                "&travellerLastNames=&travellerLastNames=wajs&travellerMobiles=13678226540" +
//                "&travellerMobiles=13800000006&travellerNames=秘籍&travellerNames=你摸过&version=1.0.0" +
//                "&visitDates=2016-04-30&visitDates=2016-04-30&test=";
//        for(String key : query.split("&")) {
//            String[] values = key.split("=");
//            List<String> list = new ArrayList<>();
//            if(values.length > 1) {
//                if(sParaTemp.containsKey(values[0])){
//                    String[] result = new String[sParaTemp.get(values[0]).length + 1];
//                    System.arraycopy(sParaTemp.get(values[0]), 0, result, 0, sParaTemp.get(values[0]).length);
//                    result[sParaTemp.get(values[0]).length] = values[1];
//                    sParaTemp.put(values[0], result);
//                }else{
//                    sParaTemp.put(values[0], new String[]{values[1]});
//                }
//            } else {
//                sParaTemp.put(values[0], new String[]{});
//            }
//        }
//        List<String> keys = new ArrayList<String>(sParaTemp.keySet());
//
//        Collections.sort(keys);
//        String originSign;
//        System.out.println(keys);
//        System.out.println(originSign = getSign(sParaTemp));
//        System.out.println(originSign + MD5_SALT);
//        System.out.println(DigestUtils.md5Hex(originSign + MD5_SALT));
//    }
}
