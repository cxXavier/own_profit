package com.xavier.fast.utils;


import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 参数签名工具
 * @author bcc
 */
public class ParamEncodeUtils {

    private static final Logger log = LoggerFactory.getLogger(ParamEncodeUtils.class);

    public static final Pattern PATTERN =Pattern.compile("(\\\\u(\\p{XDigit}{4}))");

    /**
     * 根据参数获取md5签名
     * @param param
     * @param clientSecret
     * @return
     */
    public static String getMd5Signature(Map<String,String> param,String clientSecret){

        try {
            Map<String, String> sortMap = sortMap(param);

            StringBuilder sb = new StringBuilder();
            sb.append(clientSecret);

            for(Map.Entry entry:sortMap.entrySet()){

                sb.append(entry.getKey().toString());
                sb.append(entry.getValue().toString());
            }
            sb.append(clientSecret);
            return MD5Util.gen(sb.toString());
        } catch (Exception e) {
            log.error("", e);
            return null;
        }

    }

    /**
     * map按键值排序
     * @param param
     * @return
     */
    private  static  Map<String,String> sortMap(Map<String,String> param){
        if(null == param || param.size() == 0){
            return new TreeMap<>();
        }
        Map<String,String> rs = new TreeMap<>(param);
        return rs;
    }


    /**
     * unicode转中文
     * @param str
     * @return
     */
    public static String unicodeToString(String str) {

        Matcher matcher = PATTERN.matcher(str);

        char ch;
        while (matcher.find()) {
            ch = (char) Integer.parseInt(matcher.group(2), 16);
            str = str.replace(matcher.group(1), ch + "");
        }
        return str;
    }

    public static String list2String(List<Object> list){
        StringBuilder sb = new StringBuilder("[");
        if(CollectionUtils.isNotEmpty(list)){
            for(int i=0;i<list.size();i++){
                if(i>0){
                    sb.append(",");
                }
                if(list.get(i) instanceof  String) {
                    sb.append("\""+list.get(i)+"\"");
                }
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
