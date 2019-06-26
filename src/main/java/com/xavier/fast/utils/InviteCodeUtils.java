package com.xavier.fast.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * 邀请码生成工具
 * @author bcc
 */
public final class InviteCodeUtils {

    private InviteCodeUtils(){}

    /**
     *  自定义进制55个字符
     *  去掉了易混淆的字符
     */
    private static final char[] binChar = new char[]{'6','8','4','T','n','r','h','p','M','9',
                                                    'D','J','N','S','w','q','A','X','Y','Z',
                                                    'a','b','Q','R','W','y','z','f','g','k',
                                                    'm','B','C','2','s','t','U','V','u','x',
                                                    'E','7','c','d','e','K','F','G','H','L',
                                                    'P','5','v','3','i','j'};
    /**
     * 进制长度
     */
    private static final int binLen = binChar.length;

    /**
     * 确保生成6位邀请码的起始数
     */
    private static final long startNumber = 550731777L;

    /**
     * 根据ID生成邀请码
     * @param id
     * @return
     */
    public static String generate(Long id){
        if(null == id || id <= 0){
            return null;
        }
        char [] buf = new char[binLen];
        id += startNumber ;
        int charPosition = binLen;
        while((id / binLen)>0){
            int ind =(int)(id % binLen);
            buf[--charPosition] = binChar[ind];
            id /= binLen;
        }
        buf[--charPosition]=binChar[(int)(id % binLen)];
        String str=new String(buf, charPosition, (binLen - charPosition));
        return str;
    }


    /**
     * 邀请码转成ID
     * @param code
     * @return
     */
    public static Long decode(String code){
        if(StringUtils.isBlank(code) || code.length() != 6){
            return null;
        }
        char chs[]=code.toCharArray();
        long res = 0L;
        for(int i=0; i < chs.length; i++) {
            int ind=0;
            for(int j=0; j < binLen; j++) {
                if(chs[i] == binChar[j]) {
                    ind=j;
                    break;
                }
            }
            if(i > 0) {
                res=res * binLen + ind;
            } else {
                res=ind;
            }
        }
        res -= startNumber;
        return res;
    }

}
