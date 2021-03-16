package com.lakala.boss.api.security;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.Enumeration;

/**
 * 密钥工具类
 */
@Slf4j
public class CAP12CertTool {

    private SignedPack signedPack;

    public CAP12CertTool(InputStream fileInputStream, String keyPass) throws SecurityException {
        signedPack = this.getP12(fileInputStream, keyPass);
    }

    public CAP12CertTool(String path, String keyPass) throws SecurityException, FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(new File(path));
        signedPack = this.getP12(fileInputStream, keyPass);
    }

    public static SignedPack getKeyPkcs12(String keyfile, String keypwd) {
        KeyStore keyStore = getKeyStore(keyfile, keypwd, "PKCS12");
        PrivateKey privateKey = null;

        try {
            Enumeration<String> aliasenum = keyStore.aliases();
            String keyAlias = null;
            if (aliasenum.hasMoreElements()) {
                keyAlias = (String) aliasenum.nextElement();
            }

            privateKey = (PrivateKey) keyStore.getKey(keyAlias, keypwd.toCharArray());
            Certificate cert = keyStore.getCertificate(keyAlias);
            PublicKey publicKey = cert.getPublicKey();
            SignedPack sp = new SignedPack();
            sp.setCert((X509Certificate) cert);
            sp.setPubKey(publicKey);
            sp.setPriKey(privateKey);
            return sp;
        } catch (Exception e) {
            throw new IllegalArgumentException("Fail: get private key from private certificate", e);
        }
    }

    private static KeyStore getKeyStore(String keyfile, String keypwd, String type) {
        FileInputStream fis = null;

        KeyStore keyStoreOut;
        try {
            KeyStore keyStore = null;
            keyStore = KeyStore.getInstance(type);
            fis = new FileInputStream(keyfile);
            char[] nPassword = null != keypwd && !"".equals(keypwd.trim()) ? keypwd.toCharArray() : null;
            keyStore.load(fis, nPassword);
            fis.close();
            keyStoreOut = keyStore;
        } catch (Exception e) {
            if (Security.getProvider("BC") == null) {
                throw new IllegalArgumentException("BC Provider not installed.", e);
            }

            throw new IllegalArgumentException("Fail: load privateKey certificate", e);
        } finally {
            if (null != fis) {
                try {
                    fis.close();
                } catch (Exception ignored) {
                }
            }
        }

        return keyStoreOut;
    }

    private SignedPack getP12(InputStream fileInputStream, String keyPass) throws SecurityException {
        SignedPack sp = new SignedPack();

        try {
            KeyStore ks = KeyStore.getInstance("PKCS12");
            char[] nPassword = (char[]) null;
            if (keyPass != null && !keyPass.trim().equals("")) {
                nPassword = keyPass.toCharArray();
            } else {
                nPassword = (char[]) null;
            }

            ks.load(fileInputStream, nPassword);
            Enumeration enum2 = ks.aliases();
            String keyAlias = null;
            if (enum2.hasMoreElements()) {
                keyAlias = (String) enum2.nextElement();
            }

            PrivateKey priKey = (PrivateKey) ks.getKey(keyAlias, nPassword);
            Certificate cert = ks.getCertificate(keyAlias);
            PublicKey pubKey = cert.getPublicKey();
            sp.setCert((X509Certificate) cert);
            sp.setPubKey(pubKey);
            sp.setPriKey(priKey);
        } catch (Exception var13) {
            log.error(var13.getMessage(), var13);
            throw new SecurityException(var13.getMessage(), var13);
        }

        if (fileInputStream != null) {
            try {
                fileInputStream.close();
            } catch (IOException var12) {
                throw new RuntimeException(var12.getMessage(), var12);
            }
        }

        if (fileInputStream != null) {
            try {
                fileInputStream.close();
            } catch (IOException var11) {
                throw new RuntimeException(var11.getMessage(), var11);
            }
        }

        return sp;
    }

    public X509Certificate getCert() {
        return signedPack.getCert();
    }

    public PublicKey getPublicKey() {
        return signedPack.getPubKey();
    }

    public PrivateKey getPrivateKey() {
        return signedPack.getPriKey();
    }

    public byte[] getSignData(byte[] indata) throws SecurityException {
        byte[] res = (byte[]) null;

        try {
            Signature signet = Signature.getInstance("SHA256WITHRSA");
            signet.initSign(this.getPrivateKey());
            signet.update(indata);
            res = signet.sign();
            return res;
        } catch (InvalidKeyException var4) {
            throw new SecurityException(var4.getMessage());
        } catch (NoSuchAlgorithmException var5) {
            throw new SecurityException(var5.getMessage());
        } catch (SignatureException var6) {
            throw new SecurityException(var6.getMessage());
        }
    }

}
