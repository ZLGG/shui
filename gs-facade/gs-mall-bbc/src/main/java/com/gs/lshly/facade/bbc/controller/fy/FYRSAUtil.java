package com.gs.lshly.facade.bbc.controller.fy;

/**
 * @author zhaoqiang
 * @Description
 * @date 2021/1/4 下午3:12
 */

import org.apache.commons.codec.binary.Base64;

import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;


public class FYRSAUtil {
    public static final String KEY_ALGORITHM = "RSA";
    public static final String CIPHER_ALGORITHM = "RSA/ECB/PKCS1Padding";
    public static final String PUBLIC_KEY = "publicKey";
    public static final String PRIVATE_KEY = "privateKey";
    public static final int KEY_SIZE = 2048;

    public static final String SIGNATURE_ALGORITHM = "SHA256withRSA";
    public static final String PLAIN_TEXT = "test string";
    private static final String ENCODING = "UTF-8";


    /**
     * 获取密钥对
     *
     * @return 密钥对
     */
    public static KeyPair getKeyPair() throws Exception {
        KeyPairGenerator generator = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        generator.initialize(KEY_SIZE);
        return generator.generateKeyPair();
    }

    /**
     * 生成密钥对
     *
     * @return
     */
    public static Map<String, byte[]> generateKeyBytes() {

        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator
                    .getInstance(KEY_ALGORITHM);
            keyPairGenerator.initialize(KEY_SIZE);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
            RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
            Map<String, byte[]> keyMap = new HashMap<String, byte[]>();
            keyMap.put(PUBLIC_KEY, publicKey.getEncoded());
            keyMap.put(PRIVATE_KEY, privateKey.getEncoded());
            return keyMap;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 还原公钥
     *
     * @param keyBytes
     * @return
     */
    public static PublicKey restorePublicKey(byte[] keyBytes) {
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(keyBytes);
        try {
            KeyFactory factory = KeyFactory.getInstance(KEY_ALGORITHM);
            PublicKey publicKey = factory.generatePublic(x509EncodedKeySpec);
            return publicKey;
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 还原私钥
     *
     * @param keyBytes
     * @return
     */
    public static PrivateKey restorePrivateKey(byte[] keyBytes) {
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(
                keyBytes);
        try {
            KeyFactory factory = KeyFactory.getInstance(KEY_ALGORITHM);
            PrivateKey privateKey = factory
                    .generatePrivate(pkcs8EncodedKeySpec);
            return privateKey;
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 签名返回base64字符串
     *
     * @param base64PriRsa
     * @param inputStr
     * @return
     */
    public static String sign2Base64(String base64PriRsa, String inputStr) {
        PrivateKey privateKey = restorePrivateKey(Base64.decodeBase64(base64PriRsa));
        return Base64.encodeBase64String(sign(privateKey, inputStr));
    }

    /**
     * 签名
     *
     * @param privateKey 私钥
     * @param plain_text 明文
     * @return
     */
    private static byte[] sign(PrivateKey privateKey, String plain_text) {
        MessageDigest messageDigest;
        byte[] signed = null;
        try {
            Signature Sign = Signature.getInstance(SIGNATURE_ALGORITHM);
            Sign.initSign(privateKey);
            Sign.update(plain_text.getBytes());
            signed = Sign.sign();
        } catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException e) {
            e.printStackTrace();
        }
        return signed;
    }

    /**
     * 验签
     *
     * @param base64PubRsa    公钥
     * @param src             明文
     * @param base64SignValue 签名
     * @return
     */
    public static boolean verifySign(String base64PubRsa, String src, String base64SignValue) {
        PublicKey publicKey = restorePublicKey(Base64.decodeBase64(base64PubRsa));
        return verifySign(publicKey, src, Base64.decodeBase64(base64SignValue));
    }

    /**
     * 验签
     *
     * @param publicKey  公钥
     * @param plain_text 明文
     * @param signed     签名
     */
    private static boolean verifySign(PublicKey publicKey, String plain_text, byte[] signed) {

        MessageDigest messageDigest;
        boolean signedSuccess = false;
        try {
            Signature verifySign = Signature.getInstance(SIGNATURE_ALGORITHM);
            verifySign.initVerify(publicKey);
            verifySign.update(plain_text.getBytes());
            signedSuccess = verifySign.verify(signed);
            System.out.println("验证成功？---" + signedSuccess);
        } catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException e) {
            e.printStackTrace();
        }
        return signedSuccess;
    }

    /**
     * bytes[]换成16进制字符串
     *
     * @param src
     * @return
     */
    public static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) throws Exception {

        // 生成密钥对
        KeyPair keyPair = getKeyPair();
        String privateKey = new String(Base64.encodeBase64(keyPair.getPrivate().getEncoded()));
        String publicKey = new String(Base64.encodeBase64(keyPair.getPublic().getEncoded()));
        System.out.println("私钥:" + privateKey);
        System.out.println("公钥:" + publicKey);

        publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAyTvTyB88W2A1O/tt6gucvrgnWABvP7OIsEjU1hJviiEjbClxGGZoqekVYDLuzp4jafBlstR8mdAoDHkAsbznNF9bMC5+co1DY23p9y81XJIiaHhCMJ8BB290+eBIvs7VIvOcNAm7uFORXHth4viuBA1EDNzce2Lkk+gqPz9iNDO66eTWjQVCHf/wjMf71Icgt8M7GL+xqafwIaknLj/m1E2T98S3CmgMEdQSbJVSb08aIdH841VogA6eEhqzQMG6fdH5saoW2737Q2o3ufzQ0bU8T0dwWu7LIKOW8Z95PoP6q4Vrb4zQ1nQASK3HMu3wUfK0DUpiAOOE389qaPYVbQIDAQAB";

        String signBase64 = sign2Base64(privateKey, PLAIN_TEXT);
        signBase64 = "XR2dahMMOO/CyXBchO/LBEBxDZwk6H29F79W/yutbbZPNK8u28+LuGSDPDsZlP7JRlywnyDn8NK7c7C6Ei7eP9tGDYQiDUqLq7KMgeho1QM+eTMYk/lz4+IF1LQrYEpC/sUxIp8qLpGbVjAq8dFcWpWPzZnZ3RL2CRS/8JI9yzoenERvRe2A+hlfE7A1uT4PXytB0Ljf6c4UgJMjH1moHFHMHgwTLsBQ6kvXnTQk+SRRbAGePJE93rJDKukXEhgU2sW3B8w2m4R4wvnQIg3HX24RRv89fBAylq7zaRTxKiglyCuGfBZ0RTG3k4UwOUFLQ/5wC+wn4GlKjqO2KfEvIQ==";
        System.out.println("-----signBase64----" + signBase64);
        verifySign(publicKey, PLAIN_TEXT, signBase64);


        // 公私钥对
//        Map<String, byte[]> keyMap = generateKeyBytes();
//        PublicKey publicKey = restorePublicKey(keyMap.get(PUBLIC_KEY));
//        PrivateKey privateKey = restorePrivateKey(keyMap.get(PRIVATE_KEY));
//        // 签名
//        byte[] sing_byte = sign(privateKey, PLAIN_TEXT);
//
//        // 验签
//        verifySign(publicKey, PLAIN_TEXT, sing_byte);
    }

}