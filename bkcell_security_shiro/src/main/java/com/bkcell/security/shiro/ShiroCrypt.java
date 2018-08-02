package com.bkcell.security.shiro;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ShiroCrypt {

    public static String encrypt(String str) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        char[] charArray = str.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++) {
            byteArray[i] = (byte) charArray[i];
        }
        byte[] md5Bytes = md5.digest(byteArray);

        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString().toUpperCase();
    }
}
