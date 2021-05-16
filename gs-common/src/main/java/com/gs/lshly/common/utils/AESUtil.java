package com.gs.lshly.common.utils;

import cn.hutool.core.util.StrUtil;
import com.gs.lshly.common.exception.InvalidEncryptedKeyException;
import org.apache.dubbo.common.utils.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User:  L.T.P
 * Date: 2019-10-16
 * Time: 17:08
 * Description: AES工具类
 */
public class AESUtil {
	
	public static void main(String args[]){
		System.out.println(aesEncrypt("18758529280"));
        System.out.println(aesDecrypt("ZZqMst7uTJBq4k4f9v5qBw=="));
	}

    private AESUtil(){}

    private static final String ALGORITHM = "AES/CBC/PKCS5Padding";
    private static final String PASSWORD = "21CTCCLOVECITYDO";
    private static final byte[] IV_BYTE = new byte[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
    
    public static String aesDecrypt(String encryptContent){
    	String ret = "";
    	try {
    		ret = aesDecrypt(encryptContent, PASSWORD);
		} catch (InvalidEncryptedKeyException e) {
			e.printStackTrace();
		}
    	return ret;
    }

    /**
     * aes加密-256位
     * @param encryptContent
     * @return
     */
    public static String aesEncrypt(String encryptContent){
    	String ret = "";
    	try {
    		ret = aesEncrypt(encryptContent, PASSWORD);
		} catch (InvalidEncryptedKeyException e) {
			e.printStackTrace();
		}
    	return ret;
    }
    /**
     * aes解密-256位
     */
    public static String aesDecrypt(String encryptContent, String password) throws InvalidEncryptedKeyException {
        if (StrUtil.isBlank(password)) {
            throw new InvalidEncryptedKeyException("password is not null");
        }
        if (password.length() != 16) {
            throw new InvalidEncryptedKeyException("password must be is 16 bytes");
        }
        try {
            //两个参数，第一个为私钥字节数组， 第二个为加密方式 AES或者DES
            Key keySpec = new SecretKeySpec(password.getBytes(), "AES");
            IvParameterSpec ivSpec = new IvParameterSpec(generateIv(password.getBytes(), IV_BYTE));
            //实例化加密类，参数为加密方式，要写全
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
            return new String(cipher.doFinal(Base64.decode(encryptContent)));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * aes加密-256位
     */
    public static String aesEncrypt(String content, String password) throws InvalidEncryptedKeyException {
        if (StringUtils.isBlank(password)) {
            throw new InvalidEncryptedKeyException("password is not null");
        }
        if (password.length() != 16) {
            throw new InvalidEncryptedKeyException("password must be is 16 bytes");
        }
        try {
            //两个参数，第一个为私钥字节数组， 第二个为加密方式 AES或者DES
            Key keySpec = new SecretKeySpec(password.getBytes(), "AES");
            IvParameterSpec ivSpec = new IvParameterSpec(generateIv(password.getBytes(), IV_BYTE));
            //实例化加密类，参数为加密方式，要写全
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
            //初始化，此方法可以采用三种方式，按服务器要求来添加。
            // （1）无第三个参数
            // （2）第三个参数为SecureRandom random = new SecureRandom();中random对象，随机数。(AES不可采用这种方法)
            // （3）采用此代码中的IVParameterSpec
            return Base64.encode(cipher.doFinal(content.getBytes("UTF-8")));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static byte[] generateIv(byte[] keys, byte[] iv) {
        byte[] tmpIv = new byte[iv.length];

        int i;
        for (i = 0; i < iv.length && i < keys.length; ++i) {
            tmpIv[i] = (byte) (iv[i] ^ keys[i]);
        }

        while (i < iv.length) {
            tmpIv[i] = iv[i];
            ++i;
        }

        return tmpIv;
    }

}
