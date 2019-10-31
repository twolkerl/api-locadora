package com.twl.apilocadora.util;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

public class EncryptUtils {

    private static final String key = "OVbch4L7QYHYIuK9OYPk";

    public static String encryptPassword(String senha) {

        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword(key);

        return encryptor.encrypt(senha);
    }

    public static String decryptPassword(String senha) {

        StandardPBEStringEncryptor decryptor = new StandardPBEStringEncryptor();
        decryptor.setPassword(key);

        return decryptor.decrypt(senha);
    }
}
