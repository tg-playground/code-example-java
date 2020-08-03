package com.taogen.example.wechat.utils;

import java.util.Random;

/**
 * @author Taogen
 */
public class StringUtils {
    public static String getRandomStringByCharAndNumber(int length){
        // 0-9(48-57), A-Z(65-90), a-z(97-122), 10+26+26=62
        int numberStart = 48;
        int numberCount = 10;
        int upperCharStart = 65;
        int lowerCharStart = 97;
        int charCount = 26;
        char[] chars = new char[length];
        Random random = new Random();
        for (int i = 0; i<length; i++) {
            char current = '0';
            int randomNumber = random.nextInt(62);
            if (randomNumber < numberCount) {
                current = (char) (numberStart + randomNumber);
            } else if (randomNumber < numberCount + charCount) {
                current = (char) (upperCharStart + (randomNumber - numberCount));
            } else if (randomNumber < numberCount + charCount * 2) {
                current = (char) (lowerCharStart + (randomNumber - numberCount - charCount));
            }
            chars[i] = current;
        }
        return new String(chars);
    }
}
