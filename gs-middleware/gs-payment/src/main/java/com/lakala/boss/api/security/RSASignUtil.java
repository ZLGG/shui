package com.lakala.boss.api.security;

import com.alibaba.fastjson.JSONArray;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Base64;
import org.springframework.core.io.ClassPathResource;

import javax.crypto.Cipher;
import java.io.*;
import java.security.*;
import java.security.cert.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.*;

@Slf4j
public class RSASignUtil {
    private String hexCert = null;

    private CAP12CertTool cap12CertTool;

    public RSASignUtil(String certFilePath, String password) throws IOException {
        try {
            ClassPathResource resource = new ClassPathResource(certFilePath);

            cap12CertTool = new CAP12CertTool(resource.getInputStream(), password);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    public static PublicKey getPublicKeyfromPath(String svrCertpath) throws SecurityException {
        X509Certificate cert = null;
        FileInputStream inStream = null;

        try {
            inStream = new FileInputStream(new File(svrCertpath));
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            cert = (X509Certificate) cf.generateCertificate(inStream);
        } catch (CertificateException var4) {
            throw new SecurityException(var4.getMessage());
        } catch (FileNotFoundException var5) {
            throw new SecurityException(var5.getMessage());
        }

        return cert.getPublicKey();
    }

    public static byte[] checkPEM(byte[] paramArrayOfByte) {
        String str1 = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789/+= \r\n-";

        for (int i = 0; i < paramArrayOfByte.length; ++i) {
            if (str1.indexOf(paramArrayOfByte[i]) == -1) {
                return null;
            }
        }

        StringBuffer localStringBuffer = new StringBuffer(paramArrayOfByte.length);
        String str2 = new String(paramArrayOfByte);

        for (int j = 0; j < str2.length(); ++j) {
            if (str2.charAt(j) != ' ' && str2.charAt(j) != '\r' && str2.charAt(j) != '\n') {
                localStringBuffer.append(str2.charAt(j));
            }
        }

        return localStringBuffer.toString().getBytes();
    }

    /**
     * RSA 加密
     *
     * @param privateKey 私钥
     * @param data       待加密数据
     * @return 加密结果
     */
    public static String rsaEcbEncryptBase64(RSAPrivateKey privateKey, byte[] data) {
        byte[] res = rsaEcbEncrypt(privateKey, data);
        return com.gs.lshly.common.utils.Base64.encode(res);
    }

    /**
     * 加密
     *
     * @param privateKey 私钥
     * @param data       加密数据
     * @return 加密结果
     */
    public static byte[] rsaEcbEncrypt(RSAPrivateKey privateKey, byte[] data) {
        String algorithm = "RSA/ECB/PKCS1Padding";
        if (privateKey == null) {
            throw new IllegalArgumentException("publicKey is null");
        } else {
            try {
                Cipher cipher = Cipher.getInstance(algorithm);
                cipher.init(1, privateKey);
                return cipher.doFinal(data);
            } catch (Exception e) {
                throw new IllegalArgumentException("Fail: RSA Ecb Encrypt", e);
            }
        }
    }

    /**
     * 解密
     *
     * @param publicKey 公钥
     * @param data      待解密数据
     * @return 解密后数据
     */
    public static String rsaEcbDecryptBase64(RSAPublicKey publicKey, String data) {
        byte[] res = rsaEcbDecrypt(publicKey, com.gs.lshly.common.utils.Base64.decode(data));
        return new String(res);
    }

    /**
     * 解密
     *
     * @param privateKey 私钥
     * @param data       待解密数据
     * @return 解密后数据
     */
    public static String rsaEcbDecryptBase64(RSAPrivateKey privateKey, String data) {
        byte[] res = rsaEcbDecrypt(privateKey, com.gs.lshly.common.utils.Base64.decode(data));
        return new String(res);
    }

    /**
     * 解密
     *
     * @param publicKey 公钥
     * @param data      待解密数据
     * @return 解密后数据
     */
    public static byte[] rsaEcbDecrypt(RSAPublicKey publicKey, byte[] data) {

        if (publicKey == null) {
            throw new IllegalArgumentException("privateKey is null");
        } else {
            Object var3 = null;

            try {
                Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
                cipher.init(2, publicKey);
                return cipher.doFinal(data);
            } catch (Exception e) {
                throw new IllegalArgumentException("Fail: RSA Ecb Decrypt", e);
            }
        }
    }

    /**
     * 解密
     *
     * @param privateKey 私钥
     * @param data       待解密数据
     * @return 解密后数据
     */
    public static byte[] rsaEcbDecrypt(RSAPrivateKey privateKey, byte[] data) {

        if (privateKey == null) {
            throw new IllegalArgumentException("privateKey is null");
        } else {
            Object var3 = null;

            try {
                Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
                cipher.init(2, privateKey);
                return cipher.doFinal(data);
            } catch (Exception e) {
                throw new IllegalArgumentException("Fail: RSA Ecb Decrypt", e);
            }
        }
    }

    public CAP12CertTool getCap12CertTool() {
        return cap12CertTool;
    }

    public String sign(String indata, String encoding) {
        String singData = null;
        try {
            X509Certificate cert = cap12CertTool.getCert();
            byte[] si = cap12CertTool.getSignData(indata.getBytes(encoding));
            byte[] cr = cert.getEncoded();
            this.hexCert = HexStringByte.byteToHex(cr);
            singData = HexStringByte.byteToHex(si);
        } catch (CertificateEncodingException var8) {
            var8.printStackTrace();
        } catch (UnsupportedEncodingException var10) {
            var10.printStackTrace();
        } catch (SecurityException var11) {
            var11.printStackTrace();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
        return singData;
    }

    public String getCertInfo() {
        return this.hexCert;
    }

    public boolean verify(String oriData, String signData, String svrCert, String encoding) {
        boolean res = false;

        try {
            byte[] signDataBytes = HexStringByte.hexToByte(signData.getBytes());
            byte[] inDataBytes = oriData.getBytes(encoding);
            byte[] signaturepem = checkPEM(signDataBytes);
            if (signaturepem != null) {
                signDataBytes = Base64.decode(signaturepem);
            }

            X509Certificate cert = this.getCertFromHexString(svrCert);
            if (cert != null) {
                PublicKey pubKey = cert.getPublicKey();
                Signature signet = Signature.getInstance("SHA256WITHRSA");
                signet.initVerify(pubKey);
                signet.update(inDataBytes);
                res = signet.verify(signDataBytes);
            }
        } catch (InvalidKeyException var12) {
            var12.printStackTrace();
        } catch (NoSuchAlgorithmException var13) {
            var13.printStackTrace();
        } catch (SignatureException var14) {
            var14.printStackTrace();
        } catch (SecurityException var15) {
            var15.printStackTrace();
        } catch (UnsupportedEncodingException var16) {
            var16.printStackTrace();
        }

        return res;
    }

    public X509Certificate getCertfromPath(String crt_path) throws SecurityException {
        X509Certificate cert = null;
        FileInputStream inStream = null;

        try {
            inStream = new FileInputStream(new File(crt_path));
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            cert = (X509Certificate) cf.generateCertificate(inStream);
            return cert;
        } catch (CertificateException var5) {
            throw new SecurityException(var5.getMessage());
        } catch (FileNotFoundException var6) {
            throw new SecurityException(var6.getMessage());
        }
    }

    public boolean verifyCert(X509Certificate userCert, X509Certificate rootCert) throws SecurityException {
        boolean res = false;

        try {
            PublicKey rootKey = rootCert.getPublicKey();
            userCert.checkValidity();
            userCert.verify(rootKey);
            res = true;
            if (!userCert.getIssuerDN().equals(rootCert.getSubjectDN())) {
                res = false;
            }

            return res;
        } catch (CertificateExpiredException var5) {
            throw new SecurityException(var5.getMessage());
        } catch (CertificateNotYetValidException var6) {
            throw new SecurityException(var6.getMessage());
        } catch (InvalidKeyException var7) {
            throw new SecurityException(var7.getMessage());
        } catch (CertificateException var8) {
            throw new SecurityException(var8.getMessage());
        } catch (NoSuchAlgorithmException var9) {
            throw new SecurityException(var9.getMessage());
        } catch (NoSuchProviderException var10) {
            throw new SecurityException(var10.getMessage());
        } catch (SignatureException var11) {
            throw new SecurityException(var11.getMessage());
        }
    }

    private X509Certificate getCertFromHexString(String hexCert) throws SecurityException {
        ByteArrayInputStream bIn = null;
        X509Certificate certobj = null;
        byte[] cert = HexStringByte.hexToByte(hexCert.getBytes());
        CertificateFactory fact = null;

        try {
            fact = CertificateFactory.getInstance("X.509");
        } catch (CertificateException var13) {
            var13.printStackTrace();
        }

        bIn = new ByteArrayInputStream(cert);

        try {
            certobj = (X509Certificate) fact.generateCertificate(bIn);
            bIn.close();
            bIn = null;
        } catch (CertificateException var11) {
            var11.printStackTrace();
        } catch (IOException var12) {
            var12.printStackTrace();
        }

        try {
            if (bIn != null) {
                bIn.close();
            }
        } catch (IOException var10) {
            var10.printStackTrace();
        }

        try {
            if (bIn != null) {
                bIn.close();
            }
        } catch (IOException var9) {
            ;
        }

        try {
            if (bIn != null) {
                bIn.close();
            }
        } catch (IOException var8) {
            ;
        }

        try {
            if (bIn != null) {
                bIn.close();
            }
        } catch (IOException var7) {
            ;
        }

        return certobj;
    }

    public String getFormValue(String respMsg, String name) {
        String[] resArr = StringUtils.split(respMsg, "&");
        Map<String, String> resMap = new HashMap<String, String>();

        for (int i = 0; i < resArr.length; ++i) {
            String data = resArr[i];
            int index = StringUtils.indexOf(data, '=');
            String nm = StringUtils.substring(data, 0, index);
            String val = StringUtils.substring(data, index + 1);
            resMap.put(nm, val);
        }

        return resMap.get(name) != null ? resMap.get(name) : "";
    }

    public String getValue(String respMsg, String name) {
        String[] resArr = StringUtils.split(respMsg, "&");
        Map<String, String> resMap = new HashMap<String, String>();

        for (int i = 0; i < resArr.length; ++i) {
            String data = resArr[i];
            int index = StringUtils.indexOf(data, '=');
            String nm = StringUtils.substring(data, 0, index);
            String val = StringUtils.substring(data, index + 1);
            resMap.put(nm, val);
        }

        return resMap.get(name) != null ? resMap.get(name) : "";
    }

    public Map<String, String> coverString2Map(String respMsg) {
        String[] resArr = StringUtils.split(respMsg, "&");
        Map<String, String> resMap = new HashMap<String, String>();

        for (int i = 0; i < resArr.length; ++i) {
            String data = resArr[i];
            int index = StringUtils.indexOf(data, '=');
            String nm = StringUtils.substring(data, 0, index);
            String val = StringUtils.substring(data, index + 1);
            resMap.put(nm, val);
        }

        return resMap;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public String coverMap2String(Map data) {
        TreeMap tree = new TreeMap();
        Iterator it = data.entrySet().iterator();

        while (it.hasNext()) {
            Map.Entry en = (Map.Entry) it.next();
            if (!"merchantSign".equals(((String) en.getKey()).trim()) && !"serverSign".equals(((String) en.getKey()).trim()) && !"serverCert".equals(((String) en.getKey()).trim())) {
                tree.put(en.getKey(), en.getValue());
            }
        }

        it = tree.entrySet().iterator();
        StringBuffer sf = new StringBuffer();

        while (it.hasNext()) {
            Map.Entry en = (Map.Entry) it.next();

            String tmp;
            if (en.getValue() instanceof String) {
                tmp = (String) en.getValue();
            } else if (en.getValue() instanceof List) {
//                tmp = JSONArray.fromObject(en.getValue()).toString();
                tmp = JSONArray.toJSON(en.getValue()).toString();
            } else {
                tmp = String.valueOf(en.getValue());
            }

            if (!StringUtils.equals(tmp, "null") && !StringUtils.isBlank(tmp)) {
                sf.append((String) en.getKey()).append("=").append(tmp).append("&");
            }
        }

        return sf.substring(0, sf.length() - 1);
    }

    public String encryptData(String dataString, String encoding, String svrCertPath) {
        try {
            byte[] data = encryptedPin(getPublicKeyfromPath(svrCertPath), dataString.getBytes(encoding));
            return new String(base64Encode(data), encoding);
        } catch (Exception var5) {
            var5.printStackTrace();
            return "";
        }
    }

    public byte[] encryptedPin(PublicKey publicKey, byte[] plainPin) throws Exception {
        try {
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding", new BouncyCastleProvider());
            cipher.init(1, publicKey);
            int blockSize = cipher.getBlockSize();
            int outputSize = cipher.getOutputSize(plainPin.length);
            int leavedSize = plainPin.length % blockSize;
            int blocksSize = leavedSize == 0 ? plainPin.length / blockSize : plainPin.length / blockSize + 1;
            byte[] raw = new byte[outputSize * blocksSize];

            for (int i = 0; plainPin.length - i * blockSize > 0; ++i) {
                if (plainPin.length - i * blockSize > blockSize) {
                    cipher.doFinal(plainPin, i * blockSize, blockSize, raw, i * outputSize);
                } else {
                    cipher.doFinal(plainPin, i * blockSize, plainPin.length - i * blockSize, raw, i * outputSize);
                }
            }

            return raw;
        } catch (Exception var9) {
            throw new Exception(var9.getMessage());
        }
    }

    public byte[] base64Encode(byte[] inputByte) throws IOException {
        return Base64.encode(inputByte);
    }

}
