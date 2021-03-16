package com.gs.lshly.common.utils;

import cn.hutool.core.util.StrUtil;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

/**
 * @author zhaoqiang
 * @Description
 * @date 2020/12/22 下午2:06
 */
public class RSAUtil {

    private static final String RSA_CIPHER_CHARSET = "UTF-8";

    static {
        try {
            Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取密钥对
     *
     * @return 密钥对
     */
    public static KeyPair getKeyPair() throws Exception {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA","BC");
        generator.initialize(1024);
        return generator.generateKeyPair();
    }

    /**
     * @param base64PriRsa
     * @param inputStr
     * @return
     */
    public static String sign2Base64(String base64PriRsa, String inputStr) {
        try {
            PrivateKey privateKey = genPrivateKey(base64PriRsa);
            Signature signature = Signature.getInstance("SHA1withRSA", "BC");
            signature.initSign(privateKey);
            signature.update(inputStr.getBytes(RSA_CIPHER_CHARSET));
            return Base64.encodeBase64String(signature.sign());
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("rsa签名异常");
        }
    }

    /**
     * @return
     */
    public static boolean verifySign(String base64PubRsa, String src, String base64SignValue) {
        try {
            PublicKey publicKey = genPublicKey(base64PubRsa);
            Signature signature = Signature.getInstance("SHA1withRSA", "BC");
            signature.initVerify(publicKey);
            signature.update(src.getBytes(RSA_CIPHER_CHARSET));
            return signature.verify(Base64.decodeBase64(base64SignValue));
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("rsa验证签名异常");
        }
    }

    private static PrivateKey genPrivateKey(String base64Rsa) {
        try {
            KeyFactory kf = KeyFactory.getInstance("RSA", "BC");
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.decodeBase64(base64Rsa));
            return kf.generatePrivate(keySpec);
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("初始化秘钥异常");
        }
    }

    private static PublicKey genPublicKey(String base64Rsa) {
        try {
            KeyFactory kf = KeyFactory.getInstance("RSA", "BC");
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.decodeBase64(base64Rsa));
            return kf.generatePublic(keySpec);
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("初始化秘钥失败");
        }
    }

    public static String encryptByRsaPub(String src, String base64PubRsa) {
        try {
            PublicKey publicKey = genPublicKey(base64PubRsa);
            byte[] encryptBytes = encryptByPublicKey(src.getBytes(RSA_CIPHER_CHARSET), publicKey);
            return Base64.encodeBase64String(encryptBytes);
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("rsa加密失败");
        }
    }

    public static String decryptByRsaPri(String base64Src, String base64PriRsa) {
        try {
            PrivateKey privateKey = genPrivateKey(base64PriRsa);
            return new String(decryptPrivateKey(Base64.decodeBase64(base64Src), privateKey), RSA_CIPHER_CHARSET);
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("rsa解密失败");
        }
    }

    /**
     * 公钥加密
     */
    private static byte[] encryptByPublicKey(byte[] srcData, PublicKey publicKey) {
        try {
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            // 分段加密
            int blockSize = cipher.getOutputSize(srcData.length) - 11;
            byte[] encryptedData = null;
            for (int i = 0; i < srcData.length; i += blockSize) {
                // 注意要使用2的倍数，否则会出现加密后的内容再解密时为乱码
                byte[] doFinal = cipher.doFinal(subarray(srcData, i, i + blockSize));
                encryptedData = addAll(encryptedData, doFinal);
            }
            return encryptedData;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 私钥算法
     *
     * @param srcData    源字节
     * @param privateKey 私钥
     * @return
     */
    private static byte[] decryptPrivateKey(byte[] srcData, PrivateKey privateKey) {
        try {
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            // 分段加密
            int blockSize = cipher.getOutputSize(srcData.length);
            byte[] decryptData = null;

            for (int i = 0; i < srcData.length; i += blockSize) {
                byte[] doFinal = cipher.doFinal(subarray(srcData, i, i + blockSize));

                decryptData = addAll(decryptData, doFinal);
            }
            return decryptData;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static byte[] subarray(byte[] array, int startIndexInclusive, int endIndexExclusive) {
        if (array == null) {
            return null;
        }
        if (startIndexInclusive < 0) {
            startIndexInclusive = 0;
        }
        if (endIndexExclusive > array.length) {
            endIndexExclusive = array.length;
        }
        int newSize = endIndexExclusive - startIndexInclusive;

        if (newSize <= 0) {
            return new byte[0];
        }

        byte[] subarray = new byte[newSize];

        System.arraycopy(array, startIndexInclusive, subarray, 0, newSize);

        return subarray;
    }

    private static byte[] addAll(byte[] array1, byte[] array2) {
        if (array1 == null) {
            return clone(array2);
        } else if (array2 == null) {
            return clone(array1);
        }
        byte[] joinedArray = new byte[array1.length + array2.length];
        System.arraycopy(array1, 0, joinedArray, 0, array1.length);
        System.arraycopy(array2, 0, joinedArray, array1.length, array2.length);
        return joinedArray;
    }

    public static byte[] clone(byte[] array) {
        if (array == null) {
            return null;
        }
        return (byte[]) array.clone();
    }


    /**
     * 验签
     *
     * @param base64PubRsa    公钥
     * @param src             明文
     * @param base64SignValue 签名
     * @return
     */
    public static boolean gongHuiVerifySign(String base64PubRsa, String src, String base64SignValue) {
        PublicKey publicKey = gongHuiRestorePublicKey(Base64.decodeBase64(base64PubRsa));
        return gongHuiVerifySign(publicKey, src, Base64.decodeBase64(base64SignValue));
    }

    /**
     * 还原公钥
     *
     * @param keyBytes
     * @return
     */
    public static PublicKey gongHuiRestorePublicKey(byte[] keyBytes) {
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(keyBytes);
        try {
            KeyFactory factory = KeyFactory.getInstance("RSA");
            PublicKey publicKey = factory.generatePublic(x509EncodedKeySpec);
            return publicKey;
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 验签
     *
     * @param publicKey  公钥
     * @param plain_text 明文
     * @param signed     签名
     */
    private static boolean gongHuiVerifySign(PublicKey publicKey, String plain_text, byte[] signed) {
        MessageDigest messageDigest;
        boolean signedSuccess = false;
        try {
            Signature verifySign = Signature.getInstance("SHA256withRSA");
            verifySign.initVerify(publicKey);
            verifySign.update(plain_text.getBytes());
            signedSuccess = verifySign.verify(signed);
            System.out.println("验证成功？---" + signedSuccess);
        } catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException e) {
            e.printStackTrace();
        }
        return signedSuccess;
    }

    public static String toSignStr(SortedMap<String, Object> params) {
        StringBuffer signStr = new StringBuffer();
        Set es = params.entrySet();  //所有参与传参的参数按照accsii排序（升序）
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            Object v = entry.getValue();

            if (StrUtil.isNotBlank(signStr.toString())) {
                signStr.append("&");
            }
            //空值不传递，不参与签名组串
            signStr.append(k + "=" + v);
        }
        return signStr.toString();
    }

    public static void main(String[] args) throws Exception {

        // 生成密钥对
        KeyPair keyPair = getKeyPair();
        String privateKey = new String(Base64.encodeBase64(keyPair.getPrivate().getEncoded()));
        String publicKey = new String(Base64.encodeBase64(keyPair.getPublic().getEncoded()));
        System.out.println("私钥:" + privateKey);
        System.out.println("公钥:" + publicKey);


//        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
//        // 初始化密钥对生成器，密钥大小为96-1024位
//        keyPairGen.initialize(1024, new SecureRandom());
//        // 生成一个密钥对，保存在keyPair中
//        KeyPair keyPair = keyPairGen.generateKeyPair();
//        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate(); // 得到私钥
//        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic(); // 得到公钥
//        String publicKey = new String(Base64.encodeBase64(publicKey.getEncoded()));
//        // 得到私钥字符串
//        String privateKey = new String(Base64.encodeBase64((privateKey.getEncoded())));
//
        String rsaPubBase64 = publicKey;
        /**
         * @Fields PRI_SIGN_KEY : 私钥
         */
        String rsaPriBase64 = privateKey;

//        rsaPriBase64 = "";
//        rsaPubBase64 = "";

//		String rsaPubBase64 = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAKwHTjMXJhagjboA16YWMbQxUBHFP7bNdp0FZiwoVXBISwVN55nsMTjXZKAcc4r46Y5ehyczQJjsFA3xtiZT7osCAwEAAQ==";
//		String rsaPriBase64 = "MIIBUwIBADANBgkqhkiG9w0BAQEFAASCAT0wggE5AgEAAkEArAdOMxcmFqCNugDXphYxtDFQEcU/ts12nQVmLChVcEhLBU3nmewxONdkoBxzivjpjl6HJzNAmOwUDfG2JlPuiwIDAQABAkBWV/xaDF5jsQYxu8aBaFZeOPoVUUmfeRT1zwXvV/c6OVwWShmtcV5rA3pURp9wAKmBJqOrXMqgIq4LFlOTKp1RAiEA2QQXug5FuUq5PKWg3S20p7JIq4UQeRIzYfTXYdgN1J8CIQDK7mBYGquXKprSkwuBRMgpAO1N6aN43kvg24ow3iwSlQIgbYeQRhpv/F4HceGSeC9aT40++jqVntWd02HU1xBJorcCIAOd2/m1//jhuYCnPj0/jJGugoww7cjprVCEmSHl08NBAiAPPmtd7s+9aeg9v1bc6kLeSW96DPJJ9lpkP2iK7nREkg==";
		String inputStr = "test验证rsa加密";
		String sign = sign2Base64(rsaPriBase64, inputStr);
		System.out.println("加密结果:" + sign);
		boolean verifySign = verifySign(rsaPubBase64, inputStr, sign);
		System.out.println("验签结果：" + verifySign);


//		genPrivateKey(rsaPriBase64);
//		System.out.println("==============================");
//		genPublicKey(rsaPubBase64);


//		String src = UUID.randomUUID().toString();
//        String src = "彭撒|null|0002900F8004138|DA09049533432879|中国工商银行股份有限公司上海九亭支行|小鸟|6217001055554678954|310227199612145555|2900|null|2019-10-09|小鸟|0.1";
//        System.out.println(src);
//
//        String sign = sign2Base64(rsaPriBase64, src);
//        System.out.println("sign: " + sign);
//        String encode = "c5wPKtyKp1wCmN2pHBni5so22zA8MQRY8n2R1wIHY578VVRgG1Kffu1Hq6WqoNksPNRSN3Xv0W0nm3en4R9RrmV1kpTfwkt4FId37Oe8NWTwIFLCHY/t/3hBB7NqgDbj6f70L5MdhU8Uk2GPr+fXTCGLYjAzDkwkZaf27lVvBSQ=";
//        System.out.println(encode);
//        System.out.println("11111" + verifySign(rsaPubBase64, src, encode));
//		String encryptByRsaPub = encryptByRsaPub(src, rsaPubBase64);
//		System.out.println(encryptByRsaPub);
//		String decryptByRsaPri = decryptByRsaPri(encryptByRsaPub, rsaPriBase64);
//		System.out.println(decryptByRsaPri);

    }
}
