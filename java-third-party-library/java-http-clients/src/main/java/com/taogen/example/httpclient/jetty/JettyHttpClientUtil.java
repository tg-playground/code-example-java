package com.taogen.example.httpclient.jetty;

import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentResponse;
import org.eclipse.jetty.client.api.Request;
import org.eclipse.jetty.client.util.FormRequestContent;
import org.eclipse.jetty.client.util.InputStreamRequestContent;
import org.eclipse.jetty.client.util.MultiPartRequestContent;
import org.eclipse.jetty.client.util.StringRequestContent;
import org.eclipse.jetty.http.HttpHeader;
import org.eclipse.jetty.util.Fields;
import org.springframework.http.HttpMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

/**
 * TODO to resolve conflicts of maven dependencies between jetty-client, jetty-io, and jetty-util. Class not found org/eclipse/jetty/util/Trie, org/eclipse/jetty/util/thread/AutoLock
 * TODO to add junit test
 *
 * @author Taogen
 */
public class JettyHttpClientUtil {
    public static String MULTIPART_BODY_BOUNDARY = new BigInteger(256, new Random()).toString();

    private static HttpClient httpClient;

    static {
        try {
            httpClient = new HttpClient();
            httpClient.setConnectTimeout(10000);
            httpClient.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ContentResponse requestWithoutBody(String url,
                                                     HttpMethod method,
                                                     Map<String, List<String>> queryStringParams,
                                                     Map<String, List<String>> headers) throws ExecutionException, InterruptedException, TimeoutException {
        Request request = httpClient.newRequest(url);
        addParams(request, queryStringParams);
        request.method(method.name());
        addHeaders(request, headers);
        ContentResponse response = request.send();
        return response;
    }

    public static ContentResponse requestWithBody(String url,
                                                  HttpMethod method,
                                                  Map<String, List<String>> queryStringParams,
                                                  Map<String, List<String>> headers,
                                                  String jsonStr) throws ExecutionException, InterruptedException, TimeoutException {
        Request request = httpClient.newRequest(url);
        addParams(request, queryStringParams);
        request.method(method.name());
        addHeaders(request, headers);
        request.header(HttpHeader.CONTENT_TYPE, "application/json");
        request.body(new StringRequestContent("application/json", jsonStr, StandardCharsets.UTF_8));
        ContentResponse response = request.send();
        return response;
    }

    public static ContentResponse requestWithForm(String url,
                                                  HttpMethod method,
                                                  Map<String, List<String>> queryStringParams,
                                                  Map<String, List<String>> headers,
                                                  Map<String, List<String>> formData) throws ExecutionException, InterruptedException, TimeoutException {
        Request request = httpClient.newRequest(url);
        addParams(request, queryStringParams);
        request.method(method.name());
        addHeaders(request, headers);
        request.header(HttpHeader.CONTENT_TYPE, "application/x-www-form-urlencoded");
        request.body(new FormRequestContent(getFieldsByMap(formData), StandardCharsets.UTF_8));
        ContentResponse response = request.send();
        return response;
    }

    public static ContentResponse requestWithMultipart(String url,
                                                       HttpMethod method,
                                                       Map<String, List<String>> queryStringParams,
                                                       Map<String, List<String>> headers,
                                                       Map<String, List<Object>> formData) throws ExecutionException, InterruptedException, TimeoutException, IOException {
        Request request = httpClient.newRequest(url);
        addParams(request, queryStringParams);
        request.method(method.name());
        addHeaders(request, headers);
        request.header(HttpHeader.CONTENT_TYPE, "multipart/form-data; boundary=" + MULTIPART_BODY_BOUNDARY);
        request.body(getMultipartRequestContent(formData, MULTIPART_BODY_BOUNDARY));
        ContentResponse response = request.send();
        return response;
    }

    private static MultiPartRequestContent getMultipartRequestContent(Map<String, List<Object>> formData, String boundary) throws IOException {
        MultiPartRequestContent multiPartRequestContent = new MultiPartRequestContent(boundary);
        if (formData != null) {
            for (Map.Entry<String, List<Object>> entry : formData.entrySet()) {
                String key = entry.getKey();
                List<Object> values = entry.getValue();
                if (values != null) {
                    for (Object value : values) {
                        if (value instanceof File) {
                            File file = (File) value;
                            multiPartRequestContent.addFilePart(key, file.getName(), new InputStreamRequestContent(new FileInputStream(file)), null)
                            ;
                        } else {
                            multiPartRequestContent.addFieldPart(key, new StringRequestContent(value.toString()), null);
                        }
                    }
                }
            }
        }
        return multiPartRequestContent;
    }

    private static Fields getFieldsByMap(Map<String, List<String>> formData) {
        if (formData != null) {
            Fields fields = new Fields();
            for (Map.Entry<String, List<String>> entry : formData.entrySet()) {
                String key = entry.getKey();
                List<String> value = entry.getValue();
                if (value != null) {
                    for (String v : value) {
                        fields.add(key, v);
                    }
                }
            }
        }
        return null;
    }

    private static void addParams(Request request, Map<String, List<String>> queryStringParams) {
        if (queryStringParams != null) {
            for (Map.Entry<String, List<String>> entry : queryStringParams.entrySet()) {
                String key = entry.getKey();
                List<String> values = entry.getValue();
                if (values != null) {
                    for (String value : values) {
                        request.param(key, value);
                    }
                }
            }
        }
    }

    private static void addHeaders(Request request, Map<String, List<String>> headers) {
        if (headers != null) {
            for (Map.Entry<String, List<String>> entry : headers.entrySet()) {
                String key = entry.getKey();
                List<String> values = entry.getValue();
                if (values != null) {
                    for (String value : values) {
                        request.header(key, value);
                    }
                }
            }
        }
    }

}
