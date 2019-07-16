package com.xavier.fast.utils;

import com.xavier.fast.properties.WechatConfig;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

/**
* @Description:    签名工具类
* @Author:         Wang
* @CreateDate:     2019/6/22 22:44
* @UpdateUser:
* @UpdateDate:     2019/6/22 22:44
* @UpdateRemark:
* @Version:        1.0
*/
public class SignUtils {

    public static String createSign(String characterEncoding, SortedMap<Object, Object> parameters) throws Exception {
        StringBuffer sf = new StringBuffer();
        Set es = parameters.entrySet();
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String key = (String) entry.getKey();
            Object value = entry.getValue();
            if(null != value && !"".equals(value) && !"sign".equals(key) && !"key".equals(key)){
                sf.append(key + "=" + value + "&");
            }
        }
        sf.append("key=" + new WechatConfig().getApiKey());
        String sign = MD5Util.MD5Encoder(sf.toString(), characterEncoding).toUpperCase();
        System.out.println(sign);
        return sign;
    }
}
