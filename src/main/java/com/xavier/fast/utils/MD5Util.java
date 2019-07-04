package com.xavier.fast.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author yirenjie
 * createDate:  2018/11/20
 */
public class MD5Util {

    public static String gen(String str) throws NoSuchAlgorithmException {
        MessageDigest m = MessageDigest.getInstance("MD5");
        m.update(str.getBytes());
        byte s[] = m.digest();
        StringBuilder checkSign = new StringBuilder();
        for (byte value : s) {
            checkSign.append(Integer.toHexString((0x000000ff & value) | 0xffffff00).substring(6));
        }
        return checkSign.toString().toUpperCase();
    }

    public final static String MD5Encoder(String s, String charset) {
        try {
            byte[] btInput = s.getBytes(charset);
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            mdInst.update(btInput);
            byte[] md = mdInst.digest();
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < md.length; i++) {
                int val = ((int) md[i]) & 0xff;
                if (val < 16) {
                    sb.append("0");
                }
                sb.append(Integer.toHexString(val));
            }
            return sb.toString();
        } catch (Exception e) {
            return null;
        }
    }
}
