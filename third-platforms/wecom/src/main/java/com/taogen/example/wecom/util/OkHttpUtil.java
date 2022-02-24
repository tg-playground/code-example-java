package com.taogen.example.wecom.util;

import okhttp3.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author Taogen
 */
public class OkHttpUtil {

    private static OkHttpClient OKHTTP_CLIENT = new OkHttpClient.Builder()
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .build();

    public static String sendGet(HttpUrl httpUrl) throws IOException {
        Request request = new Request.Builder()
                .url(httpUrl)
                .build();
        try (Response response = OKHTTP_CLIENT.newCall(request).execute()) {
            return response.body().string();
        }
    }

    public static String sendPostWithJson(HttpUrl httpUrl, String json) throws IOException {
        RequestBody requestBody = RequestBody.create(
                json, MediaType.parse("application/json"));
        Request request = new Request.Builder()
                .url(httpUrl)
                .post(requestBody)
                .build();
        try (Response response = OKHTTP_CLIENT.newCall(request).execute()) {
            return response.body().string();
        }
    }
}
