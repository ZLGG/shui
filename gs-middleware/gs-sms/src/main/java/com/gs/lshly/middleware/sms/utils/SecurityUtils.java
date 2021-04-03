package com.gs.lshly.middleware.sms.utils;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Map;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

import com.aliyuncs.auth.AcsURLEncoder;
import com.aliyuncs.utils.Base64Helper;
//import com.citydo.xclouddesk.exception.AesException;
import com.gs.lshly.common.exception.BusinessException;

/**
 * Created by jack on 17/12/8.
 */
public class SecurityUtils {


    private final static String AGLORITHM_NAME = "HmacSHA256";

     static  String composeStringToSign(Map<String, String> queries) {
        String[] sortedKeys = queries.keySet().toArray(new String[queries.size()]);
        Arrays.sort(sortedKeys);
        StringBuilder canonicalizeQueryString = new StringBuilder();
        try {
            for(String key : sortedKeys) {
                canonicalizeQueryString.append('&')
                        .append(AcsURLEncoder.percentEncode(key)).append('=')
                        .append(AcsURLEncoder.percentEncode(queries.get(key)));
            }
            return AcsURLEncoder.percentEncode(canonicalizeQueryString.substring(1));
        } catch (UnsupportedEncodingException exp) {
//            throw new AesException(AesException.EncodingNotSupported);
            throw new BusinessException("composeStringToSign出错");
        }
    }

    public static String getAesKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128);
        SecretKey secretKey = keyGenerator.generateKey();
        return Base64.encodeBase64String(secretKey.getEncoded());
    }

     static String signString(String source, String accessSecret)
            throws InvalidKeyException, IllegalStateException {
        try {
            Mac mac = Mac.getInstance(AGLORITHM_NAME);
            mac.init(new SecretKeySpec(
                    accessSecret.getBytes(AcsURLEncoder.URL_ENCODING),AGLORITHM_NAME));
            byte[] signData = mac.doFinal(source.getBytes(AcsURLEncoder.URL_ENCODING));
            return Base64Helper.encode(signData);
        } catch (NoSuchAlgorithmException e) {
//            throw new AesException(AesException.ComputeSignatureError);
        	throw new BusinessException("signString出错");
        } catch (UnsupportedEncodingException e) {
//            throw new AesException(AesException.EncodingNotSupported);
            throw new BusinessException("signString出错");
        }
    }
     
}
