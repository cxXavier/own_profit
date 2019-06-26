package com.xavier.fast.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class JSONStringUnderscoreToCamel {
    private JSONStringUnderscoreToCamel(){}

    private static String regx = "\"\\w+\":";

    private static Pattern pattern = Pattern.compile(regx);

    public static String transform(String jsonStr){
        //转换后的字符串
        String str = jsonStr;
        Matcher matcher = pattern.matcher(jsonStr);
        while(matcher.find()){
            str = str.replaceFirst(matcher.group(),camelCase(matcher.group()));
        }
        return str;
    }


    public static String camelCase(String str){
        String camelCase = "";
        String [] arr = str.split("_");
        List<String> list = new ArrayList<String>();

        //将数组中非空字符串添加至list
        for(String a : arr){
            if(a.length() > 0){
                list.add(a);
            }
        }

        for(int i=0;i<list.size();i++){
            if(i>0){    //后面单词首字母大写
                char c = list.get(i).charAt(0);
                String s = String.valueOf(c).toUpperCase() + list.get(i).substring(1).toLowerCase();
                camelCase+=s;
            }else{  //首个单词小写
                camelCase+=list.get(i).toLowerCase();
            }
        }
        return camelCase;
    }

}
