package com.rocs.user.service.key.impl;

import com.rocs.user.service.key.EncryptionDecryptionAES;
import org.springframework.stereotype.Service;

import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

@Service
public class EncryptionDecryptionAESImpl implements EncryptionDecryptionAES {

    private static final String STATIC_KEY = "Thelazytruckjumpoverthebigfatfox";
    private static final SecretKey SECRET_KEY;
    private static Cipher cipher;

    static {
        try {
            SECRET_KEY = new SecretKeySpec(STATIC_KEY.getBytes(), "AES");
            cipher = Cipher.getInstance("AES");
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize Cipher or SecretKey", e);
        }
    }

    @Override
    public String encrypt(String plainText) throws Exception {
        byte[] plainTextByte = plainText.getBytes();
        cipher.init(Cipher.ENCRYPT_MODE, SECRET_KEY);
        byte[] encryptedByte = cipher.doFinal(plainTextByte);
        Base64.Encoder encoder = Base64.getEncoder();
        return encoder.encodeToString(encryptedByte);
    }

    @Override
    public String decrypt(String encryptedText) throws Exception {
            Base64.Decoder decoder = Base64.getDecoder();
            byte[] encryptedTextByte = decoder.decode(encryptedText);
            cipher.init(Cipher.DECRYPT_MODE, SECRET_KEY);
            byte[] decryptedByte = cipher.doFinal(encryptedTextByte);
            return new String(decryptedByte);
    }

}
