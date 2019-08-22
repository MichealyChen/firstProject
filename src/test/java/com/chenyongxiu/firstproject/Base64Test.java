package com.chenyongxiu.firstproject;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.util.StringUtil;
import org.junit.Test;
import org.springframework.util.DigestUtils;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;
import sun.misc.BASE64Encoder;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.nio.charset.Charset;
import java.security.*;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;

@Slf4j
public class Base64Test {
    @Test
    public void base64(){
        String s="密*?/";
        String encodeString = Base64Coder.encodeString(s);
        System.out.println(encodeString);
    }

    @Test
    public void sha() throws NoSuchAlgorithmException {
        String s="密*?/kkkk";
        String s1 = Base64Coder.encodeString(s);
        System.out.println(s1);
        System.out.println(Base64Coder.decodeString(s1));

    }

    @Test
    public void HDTest() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(512);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        Key aPublic = keyPair.getPublic();
        Key aPrivate = keyPair.getPrivate();

        byte[] publicKeyBytes = aPublic.getEncoded();

        byte[] privateKeyBytes = aPrivate.getEncoded();

        String publicKeyBase64 = Base64Coder.encodeLines(publicKeyBytes);

        String privateKeyBase64 = Base64Coder.encodeLines(privateKeyBytes);
        System.out.println("publicKeyBase64  "+publicKeyBase64);
        System.out.println("privateKeyBase64  "+privateKeyBase64);
    }
}
