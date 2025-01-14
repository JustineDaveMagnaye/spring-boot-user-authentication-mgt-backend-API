package com.rocs.user.service.key;

public interface EncryptionDecryptionAES {
    String encrypt (String plainText) throws Exception;

    String decrypt (String encryptedText) throws Exception;
}
