package com.taogen.example.httpclient.okhttp;

import com.taogen.example.httpclient.okhttp.vo.HttpResponse;
import okhttp3.*;
import org.springframework.http.HttpMethod;
import org.springframework.util.MultiValueMap;

import java.io.File;
import java.io.IOException;
import java.net.URLConnection;
import java.util.List;
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

    public static HttpResponse requestWithoutBody(String url,
                                                  HttpMethod method,
                                                  MultiValueMap<String, String> queryStringParams,
                                                  Headers headers) throws IOException {
        Request.Builder requestBuilder = new Request.Builder()
                .url(getHttpUrl(url, queryStringParams))
                .headers(headers);
        addRequestMethod(requestBuilder, method);
        try (Response response = OKHTTP_CLIENT.newCall(requestBuilder.build()).execute()) {
            // after response closed, the response body can't be read again.
            // so we need to save to a wrapper class object
            return new HttpResponse(response.code(), response.headers(), response.body().string());
        }
    }

    public static HttpResponse requestWithJson(String url,
                                               HttpMethod method,
                                               MultiValueMap<String, String> queryStringParams,
                                               Headers headers,
                                               String json) throws IOException {
        if (method == HttpMethod.GET) {
            throw new IllegalArgumentException("GET method can't use json body");
        }
        RequestBody requestBody = getJsonRequestBody(json);
        Request.Builder requestBuilder = new Request.Builder()
                .url(getHttpUrl(url, queryStringParams))
                .headers(headers);
        addRequestBody(requestBuilder, requestBody, method);
        try (Response response = OKHTTP_CLIENT.newCall(requestBuilder.build()).execute()) {
            return new HttpResponse(response.code(), response.headers(), response.body().string());
        }
    }

    public static HttpResponse requestWithFormUrlEncoded(String url,
                                                         HttpMethod method,
                                                         MultiValueMap<String, String> queryStringParams,
                                                         Headers headers,
                                                         MultiValueMap<String, String> formData) throws IOException {
        if (method == HttpMethod.GET) {
            throw new IllegalArgumentException("GET method can't use form data body");
        }
        Request.Builder requestBuilder = new Request.Builder()
                .url(getHttpUrl(url, queryStringParams))
                .headers(headers);
        addRequestBody(requestBuilder, getFormDataBody(formData), method);
        try (Response response = OKHTTP_CLIENT.newCall(requestBuilder.build()).execute()) {
            return new HttpResponse(response.code(), response.headers(), response.body().string());
        }
    }

    /**
     * @param url
     * @param method
     * @param queryStringParams
     * @param headers
     * @param formData
     * @return
     * @throws IOException
     */
    public static HttpResponse requestWithFormData(String url,
                                                   HttpMethod method,
                                                   MultiValueMap<String, String> queryStringParams,
                                                   Headers headers,
                                                   MultiValueMap<String, Object> formData) throws IOException {
        if (method == HttpMethod.GET) {
            throw new IllegalArgumentException("GET method can't use form data body");
        }
        Request.Builder requestBuilder = new Request.Builder()
                .url(getHttpUrl(url, queryStringParams))
                .headers(headers);
        addRequestBody(requestBuilder, getMultipartBody(formData), method);
        try (Response response = OKHTTP_CLIENT.newCall(requestBuilder.build()).execute()) {
            return new HttpResponse(response.code(), response.headers(), response.body().string());
        }
    }

    private static RequestBody getJsonRequestBody(String json) {
        return RequestBody.create(
                json, MediaType.parse("application/json"));
    }

    private static void addRequestBody(Request.Builder requestBuilder, RequestBody requestBody, HttpMethod method) {
        if (method == HttpMethod.POST) {
            requestBuilder.post(requestBody);
        } else if (method == HttpMethod.PUT) {
            requestBuilder.put(requestBody);
        } else if (method == HttpMethod.DELETE) {
            requestBuilder.delete(requestBody);
        }
    }

    private static void addRequestMethod(Request.Builder requestBuilder, HttpMethod method) {
        if (method == HttpMethod.GET) {
            requestBuilder.get();
        } else if (method == HttpMethod.POST) {
            requestBuilder.post(null);
        } else if (method == HttpMethod.PUT) {
            requestBuilder.put(null);
        } else if (method == HttpMethod.DELETE) {
            requestBuilder.delete();
        }
    }

    private static HttpUrl getHttpUrl(String url,
                                      MultiValueMap<String, String> queryStringParams) {
        HttpUrl.Builder builder = HttpUrl.parse(url).newBuilder();
        if (queryStringParams != null) {
            for (String key : queryStringParams.keySet()) {
                builder.addQueryParameter(key, queryStringParams.getFirst(key));
            }
        }
        return builder.build();
    }

    private static RequestBody getMultipartBody(MultiValueMap<String, Object> formData) {
        MultipartBody.Builder builder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM);
        if (formData != null) {
            for (String key : formData.keySet()) {
                List<Object> values = formData.get(key);
                if (values != null) {
                    values.forEach(item -> {
                        if (item instanceof File) {
                            File file = (File) item;
                            String fileName = file.getName();
                            String mediaType = URLConnection.guessContentTypeFromName(fileName);
                            if (mediaType == null) {
                                mediaType = "application/octet-stream";
                            }
                            builder.addFormDataPart(key, fileName,
                                    RequestBody.create(file, MediaType.parse(mediaType)));
                        } else {
                            builder.addFormDataPart(key, item.toString());
                        }
                    });
                }
            }
        }
        return builder.build();
    }

    private static RequestBody getFormDataBody(MultiValueMap<String, String> formData) {
        FormBody.Builder builder = new FormBody.Builder();
        if (formData != null) {
            for (String key : formData.keySet()) {
                List<String> values = formData.get(key);
                if (values != null) {
                    values.forEach(item -> builder.add(key, item));
                }
            }
        }
        return builder.build();
    }
}
