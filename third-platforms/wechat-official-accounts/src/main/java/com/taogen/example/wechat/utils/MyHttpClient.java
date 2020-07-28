package com.taogen.example.wechat.utils;

import okhttp3.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

/**
 * @author Taogen
 */
public class MyHttpClient {
    private static final Logger logger = LogManager.getLogger();
    private static final OkHttpClient client = new OkHttpClient();
    private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    public static String doGet(String urlStr) {
        logger.debug("Connecting to {}...", urlStr);
        long start = System.currentTimeMillis();
        Request request = new Request.Builder()
                .url(urlStr)
                .build();
        String result = null;
        result = getResponseBodyByRequest(request);
        long elapsedTime = System.currentTimeMillis() - start;
        logger.debug("HTTP response is: {}", result);
        logger.debug("HTTP cost time: {} ms.", elapsedTime);
        return result;
    }

    public static String doPost(String urlStr, String jsonStr) {
        logger.debug("Connecting to {}...", urlStr);
        long start = System.currentTimeMillis();
        RequestBody requestBody = RequestBody.create(jsonStr, JSON);
        Request request = new Request.Builder()
                .url(urlStr)
                .post(requestBody)
                .build();
        String result = getResponseBodyByRequest(request);
        long elapsedTime = System.currentTimeMillis() - start;
        logger.debug("HTTP response is: {}", result);
        logger.debug("HTTP cost time: {} ms.", elapsedTime);
        return result;
    }

    private static String getResponseBodyByRequest(Request request){
        String result = null;
        try (Response response = client.newCall(request).execute()) {
            result = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
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
