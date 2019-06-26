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
}
