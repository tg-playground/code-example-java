package com.taogen.example.bestpractice.exceptionhandling;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author Taogen
 */
public class TryWithResources {

    public static String read(String path) {
        StringBuilder stringBuilder = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            stringBuilder.append(br.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return stringBuilder.toString();
    }
}
