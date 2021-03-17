package com.gs.lshly.common.utils;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

import com.gs.lshly.common.exception.BusinessException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CcsMsgCrypt {

    private static Base64 base64 = new Base64();

    //private static final String AES_ALGORITHM = "AES/GCM/NoPadding";

    private static final String AES_ALGORITHM = "AES/ECB/PKCS5Padding";

    /**
     * 对明文进行加密.
     *
     * @param content 需要加密的明文
     * @return 加密后base64编码的字符串
     */
    public static String encrypt(String content, String aesKey) {
        try {
            byte[] raw = Base64.decodeBase64(aesKey);
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance(AES_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
            byte[] encrypted = cipher.doFinal(content.getBytes(StandardCharsets.UTF_8));
            // 使用BASE64对加密后的字符串进行编码
            return base64.encodeToString(encrypted);
        } catch (Exception e) {
            log.info(e.getMessage(), e);
            throw new BusinessException("使用BASE64对加密后的字符串进行编码出错");
        }
    }

    /**
     * 对密文进行解密.
     *
     * @param encryptedContent 需要解密的密文
     * @return 解密得到的明文
     */
    public static String decrypt(String encryptedContent, String aesKey) {
        byte[] original;
        try {
            byte[] raw = Base64.decodeBase64(aesKey);
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance(AES_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);
            byte[] encrypted = base64.decode(encryptedContent);
            // 解密
            original = cipher.doFinal(encrypted);
            return new String(original, StandardCharsets.UTF_8);
        } catch (Exception e) {
            log.info(e.getMessage(), e);
//            throw new AesException(AesException.DecryptAESError);
            throw new BusinessException("对密文进行解密");
        }
    }

    /**
     * 生成签名字符串
     */
    public static String sign(Map<String, String> source, String key) {
        String context = SecurityUtils.composeStringToSign(source);
        try {
            return SecurityUtils.signString(context, key);
        } catch (InvalidKeyException e) {
            log.info(e.getMessage(), e);
//            throw new AesException(AesException.ComputeSignatureError);
            throw new BusinessException("生成签名字符串出错");
        }
    }

    /**
     * 验证签名有效性
     */
    public static void verifySignature(Map<String, String> source, String key, String msgSignature) {
        String context = SecurityUtils.composeStringToSign(source);
        try {
            String sign = SecurityUtils.signString(context, key);
            if (!sign.equals(msgSignature)) {
//                throw new AesException(AesException.ValidateSignatureError);
                throw new BusinessException("验证签名有效性出错");
            }
        } catch (InvalidKeyException e) {
            log.info(e.getMessage(), e);
//            throw new AesException(AesException.ValidateSignatureError);
            throw new BusinessException("验证签名有效性出错");
        }
    }

    private CcsMsgCrypt() {
    }

    public static void main(String[] args) {
        String src = "{\"phoneNumber\":\"18267495869\",\"uname\":\"summer\",\"gender\":\"男\",\"headImg\":\"\",\"tenantId\":\"_1LWHJ9M\",\"userId\":\"bbq1221\"}";
        String content = decrypt("x5BjTkg7Ieky+MTqaRJ/QprOxs0TDgIVKQBrxbE7mrQ4ncEM3o4s+5ukMaXDltJbfwKiV8cq9SErn0G3asiVts4YzGLCSYaVHQo6kgNeiEFdZGc5/Mk3Yf72zy6sGd6RAZ6Znu8PVgW4BEtD3+qDlw==", "aHV6aG91SHp6c3RIenpzdA==");
        System.out.println(content);
        System.out.println(encrypt(src,"aHV6aG91SHp6c3RIenpzdA=="));

    }

}