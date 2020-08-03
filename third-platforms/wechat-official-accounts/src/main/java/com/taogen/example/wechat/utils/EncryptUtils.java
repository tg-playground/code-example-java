package com.taogen.example.wechat.utils;

import javax.sound.midi.Soundbank;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Taogen
 */
public class EncryptUtils {
    public static String toSHA1(String source) {
        MessageDigest crypt = null;
        try {
            crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(source.toString().getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        int radix = 16;
        return new BigInteger(1, crypt.digest()).toString(radix);
    }
}
