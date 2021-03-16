package com.lakala.boss.api.utils;

import cn.hutool.core.util.ObjectUtil;
import com.gs.lshly.common.exception.InvalidEncryptedKeyException;
import com.gs.lshly.common.utils.AESUtil;
import com.lakala.boss.api.security.CAP12CertTool;
import com.lakala.boss.api.security.RSASignUtil;
import org.apache.commons.lang3.StringUtils;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

/**
 * @Project: boss-sdk
 * @Package: com.lakala.boss.api.utils
 * @Description: 加解密工具类
 * @author: LXF
 * @date Date: 2019年10月23日 18:00
 * @version: V1.0.0
 */
public class KeyUtil {

    private KeyUtil(){}

    /**
     * 加密敏感数据
     * @param str
     * @param secretKey
     * @return
     * @throws InvalidEncryptedKeyException
     */
    public static String encryptPrivateData(String str, String secretKey) throws InvalidEncryptedKeyException {
        if(StringUtils.isNotBlank(str) && StringUtils.isNotBlank(secretKey)) {
            return AESUtil.aesEncrypt(str, secretKey);
        } else {
            return null;
        }

    }

    /**
     * 解密敏感数据
     * @param str
     * @param secretKey
     * @return
     * @throws InvalidEncryptedKeyException
     */
    public static String decryptPrivateData(String str, String secretKey) throws InvalidEncryptedKeyException {
        if(StringUtils.isNotBlank(str) && StringUtils.isNotBlank(secretKey)) {
            return AESUtil.aesDecrypt(str, secretKey);
        } else {
            return null;
        }

    }

    /**
     * 加密临时密钥
     * @param client
     * @param secretKey
     * @return
     */
    public static String encryptSecretKey(BossClient client, String secretKey){
        if(ObjectUtil.isEmpty(client) || StringUtils.isBlank(secretKey)){
            return null;
        }

        CAP12CertTool cap12CertTool = client.getRsaSignUtil().getCap12CertTool();
        return RSASignUtil.rsaEcbEncryptBase64((RSAPrivateKey) cap12CertTool.getPrivateKey(), secretKey.getBytes());
    }

    /**
     * 解密临时密钥
     * @param client
     * @param encryptSecretKey
     * @return
     */
    public static String decryptSecretKey(BossClient client, String encryptSecretKey){
        if(ObjectUtil.isEmpty(client) || StringUtils.isBlank(encryptSecretKey)){
            return null;
        }
        CAP12CertTool cap12CertTool = client.getRsaSignUtil().getCap12CertTool();
        return RSASignUtil.rsaEcbDecryptBase64((RSAPublicKey) cap12CertTool.getPublicKey(), encryptSecretKey);
    }

    /**
     * 解密临时密钥
     * @param client
     * @param encryptSecretKey
     * @return
     */
    public static String decryptSecretKeyByPrivateKey(BossClient client, String encryptSecretKey){
        if(ObjectUtil.isEmpty(client) || StringUtils.isBlank(encryptSecretKey)){
            return null;
        }
        CAP12CertTool cap12CertTool = client.getRsaSignUtil().getCap12CertTool();
        return RSASignUtil.rsaEcbDecryptBase64((RSAPrivateKey) cap12CertTool.getPrivateKey(), encryptSecretKey);
    }

}
