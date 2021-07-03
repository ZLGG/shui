package com.gs.lshly.common.utils;

import java.security.MessageDigest;

public class MD5Util {

	/**
	 * MD5大写
	 * @param str
	 * @param charset
	 * @return
	 * @throws Exception
	 */
	public static String MD5Upper(String str, String charset) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(str.getBytes(charset));
        byte[] result = md.digest();
        StringBuffer sb = new StringBuffer(32);
        for (int i = 0; i < result.length; i++) {
            int val = result[i] & 0xff;
            if (val <= 0xf) {
                sb.append("0");
            }
            sb.append(Integer.toHexString(val));
        }
        return sb.toString().toUpperCase();
    }
	/**
	 * 小写
	 * @param str
	 * @param charset
	 * @return
	 * @throws Exception
	 */
	public static String MD5Lower(String str, String charset) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(str.getBytes(charset));
        byte[] result = md.digest();
        StringBuffer sb = new StringBuffer(32);
        for (int i = 0; i < result.length; i++) {
            int val = result[i] & 0xff;
            if (val <= 0xf) {
                sb.append("0");
            }
            sb.append(Integer.toHexString(val));
        }
        return sb.toString().toLowerCase();
    }
}
