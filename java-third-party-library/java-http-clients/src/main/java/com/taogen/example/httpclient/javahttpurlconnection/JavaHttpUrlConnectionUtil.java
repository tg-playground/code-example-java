package com.taogen.example.httpclient.javahttpurlconnection;

import com.taogen.example.httpclient.javahttpurlconnection.vo.HttpUrlConnectionHttpResponse;
import com.taogen.example.util.HttpRequestUtil;
import org.springframework.http.HttpMethod;

import java.io.*;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @author Taogen
 */
public class JavaHttpUrlConnectionUtil {

    public static String MULTIPART_BODY_BOUNDARY = new BigInteger(256, new Random()).toString();

    public static HttpUrlConnectionHttpResponse requestWithoutBody(String url,
                                                                   HttpMethod method,
                                                                   Map<String, List<String>> params,
                                                                   Map<String, List<String>> headers) throws IOException {
        URL urlObj = new URL(url + HttpRequestUtil.multiValueMapToQueryString(params));
        HttpURLConnection urlConnection = (HttpURLConnection) urlObj.openConnection();
        urlConnection.setRequestMethod(method.name());
        addHeaders(urlConnection, headers);
        try {
            return getResponse(urlConnection);
        } finally {
            urlConnection.disconnect();
        }
    }

    public static HttpUrlConnectionHttpResponse requestWithJson(String url,
                                                                HttpMethod method,
                                                                Map<String, List<String>> params,
                                                                Map<String, List<String>> headers,
                                                                String jsonString) throws IOException {
        URL urlObj = new URL(url + HttpRequestUtil.multiValueMapToQueryString(params));
        HttpURLConnection urlConnection = (HttpURLConnection) urlObj.openConnection();
        urlConnection.setRequestMethod(method.name());
        addHeaders(urlConnection, headers);
        urlConnection.setRequestProperty("Content-Type", "application/json");
        urlConnection.setDoOutput(true);
        byte[] postData = jsonString.getBytes(StandardCharsets.UTF_8);
        urlConnection.setRequestProperty("Content-Length", String.valueOf(postData.length));
        OutputStream out = urlConnection.getOutputStream();
        out.write(postData);
        try {
            return getResponse(urlConnection);
        } finally {
            urlConnection.disconnect();
        }
    }

    public static HttpUrlConnectionHttpResponse requestWithForm(String url,
                                                                HttpMethod method,
                                                                Map<String, List<String>> params,
                                                                Map<String, List<String>> headers,
                                                                Map<String, List<String>> formData) throws IOException {
        URL urlObj = new URL(url + HttpRequestUtil.multiValueMapToQueryString(params));
        HttpURLConnection urlConnection = (HttpURLConnection) urlObj.openConnection();
        urlConnection.setRequestMethod(method.name());
        addHeaders(urlConnection, headers);
        urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        urlConnection.setDoOutput(true);
        byte[] postData = HttpRequestUtil.multiValueMapToQueryString(formData)
                .substring(1)
                .getBytes(StandardCharsets.UTF_8);
        urlConnection.setRequestProperty("Content-Length", String.valueOf(postData.length));
        OutputStream out = urlConnection.getOutputStream();
        out.write(postData);
        try {
            return getResponse(urlConnection);
        } finally {
            urlConnection.disconnect();
        }
    }

    public static HttpUrlConnectionHttpResponse requestWithMultipart(String url,
                                                                     HttpMethod method,
                                                                     Map<String, List<String>> params,
                                                                     Map<String, List<String>> headers,
                                                                     Map<String, List<Object>> formData) throws IOException {
        URL urlObj = new URL(url + HttpRequestUtil.multiValueMapToQueryString(params));
        HttpURLConnection urlConnection = (HttpURLConnection) urlObj.openConnection();
        urlConnection.setRequestMethod(method.name());
        addHeaders(urlConnection, headers);
        urlConnection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + MULTIPART_BODY_BOUNDARY);
        urlConnection.setDoOutput(true);
        byte[] postData = HttpRequestUtil.multiValueMapToMultipartData(formData, MULTIPART_BODY_BOUNDARY);
        urlConnection.setRequestProperty("Content-Length", String.valueOf(postData.length));
        OutputStream out = urlConnection.getOutputStream();
        out.write(postData);
        try {
            return getResponse(urlConnection);
        } finally {
            urlConnection.disconnect();
        }
    }

    private static HttpUrlConnectionHttpResponse getResponse(HttpURLConnection urlConnection) throws IOException {
        HttpUrlConnectionHttpResponse httpResponse = new HttpUrlConnectionHttpResponse();
        InputStream in = new BufferedInputStream(urlConnection.getInputStream());
        httpResponse.setBody(convertStreamToString(in));
        httpResponse.setStatusCode(urlConnection.getResponseCode());
        httpResponse.setHeaders(urlConnection.getHeaderFields());
        return httpResponse;
    }

    private static void addHeaders(HttpURLConnection urlConnection, Map<String, List<String>> headers) {
        if (headers != null) {
            for (Map.Entry<String, List<String>> entry : headers.entrySet()) {
                String key = entry.getKey();
                List<String> values = entry.getValue();
                for (String value : values) {
                    urlConnection.addRequestProperty(key, value);
                }
            }
        }
    }

    private static String convertStreamToString(InputStream in) throws IOException {
        byte[] buffer = new byte[1024];
        int length;
        try (
                ByteArrayOutputStream out = new ByteArrayOutputStream();
        ) {
            while ((length = in.read(buffer)) != -1) {
                out.write(buffer, 0, length);
            }
            return new String(out.toByteArray(), StandardCharsets.UTF_8);
        }
    }
}
