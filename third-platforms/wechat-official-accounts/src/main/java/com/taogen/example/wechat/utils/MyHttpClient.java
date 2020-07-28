package com.taogen.example.wechat.utils;

import okhttp3.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

/**
 * @author Taogen
 */
public class MyHttpClient {
    private static final Logger logger = LogManager.getLogger();
    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");
    private static OkHttpClient client = new OkHttpClient();

    public static String doGet(String urlStr){
        Request request = new Request.Builder()
                .url(urlStr)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String doPost(String urlStr, String jsonStr) {
        RequestBody body = RequestBody.create(jsonStr, JSON);
        Request request = new Request.Builder()
                .url(urlStr)
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

//    public static String doGet(String urlStr) {
//        try {
//            URL url = new URL(urlStr);
//            HttpURLConnection con = (HttpURLConnection) url.openConnection();
//            con.setRequestMethod("GET");
//            int responseCode = con.getResponseCode();
//            logger.debug("GET Response Code :: " + responseCode);
//            if (responseCode == HttpURLConnection.HTTP_OK) {
//                BufferedReader in = new BufferedReader(new InputStreamReader(
//                        con.getInputStream()));
//                String inputLine;
//                StringBuffer response = new StringBuffer();
//
//                while ((inputLine = in.readLine()) != null) {
//                    response.append(inputLine);
//                }
//                in.close();
//                return response.toString();
//            } else {
//                logger.info("GET request not worked");
//            }
//            con.setDoOutput(true);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return "";

//    }
}
