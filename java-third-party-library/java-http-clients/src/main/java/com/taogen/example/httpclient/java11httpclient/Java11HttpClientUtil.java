package com.taogen.example.httpclient.java11httpclient;

import com.taogen.example.util.HttpRequestUtil;
import org.springframework.http.HttpMethod;
import org.springframework.util.MultiValueMap;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Java 11 HttpClient Util
 * <p>
 * The HttpClient doesn't provide any high level API to compose or format data in POST requests.
 * You could either compose and format your post data manually, and then use either one of BodyPublishers.ofString(), BodyPublishers.ofInputStream(),
 * or BodyPublishers.ofByteArrays() etc... to send it, or write your own implementation of BodyPublisher.
 *
 * @author Taogen
 */
public class Java11HttpClientUtil {
    public static String MULTIPART_BODY_BOUNDARY = new BigInteger(256, new Random()).toString();

    public static HttpResponse<String> requestWithoutBody(String url,
                                                          HttpMethod method,
                                                          MultiValueMap<String, String> params,
                                                          MultiValueMap<String, String> headers) throws IOException, InterruptedException, URISyntaxException {
        HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                .uri(new URI(url + HttpRequestUtil.multiValueMapToQueryString(params)));
        addMethod(requestBuilder, method, HttpRequest.BodyPublishers.noBody());
        addHeaders(requestBuilder, headers);
        HttpResponse<String> response = HttpClient.newHttpClient().send(
                requestBuilder.build(), HttpResponse.BodyHandlers.ofString());
        return response;
    }

    public static HttpResponse<String> requestWithJson(String url,
                                                       HttpMethod method,
                                                       MultiValueMap<String, String> params,
                                                       MultiValueMap<String, String> headers,
                                                       String body) throws IOException, InterruptedException, URISyntaxException {
        HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                .uri(new URI(url + HttpRequestUtil.multiValueMapToQueryString(params)));
        addMethod(requestBuilder, method, HttpRequest.BodyPublishers.ofString(body, StandardCharsets.UTF_8));
        headers.add("Content-Type", "application/json");
        addHeaders(requestBuilder, headers);
        HttpResponse<String> response = HttpClient.newHttpClient().send(
                requestBuilder.build(), HttpResponse.BodyHandlers.ofString());
        return response;
    }

    /**
     * application/x-www-form-urlencoded request example
     * <p>
     * POST /test HTTP/1.1
     * Host: foo.example
     * Content-Type: application/x-www-form-urlencoded
     * Content-Length: 27
     *
     * field1=value1&field2=value2
     * <p>
     * FileUploadException: the request was rejected because no multipart boundary was found
     * Solution: "Content-Type", "multipart/form-data; boundary=" + boundary
     *
     * @param url
     * @param method
     * @param params
     * @param headers
     * @param formData
     * @return
     * @throws URISyntaxException
     * @throws IOException
     * @throws InterruptedException
     */
    public static HttpResponse<String> requestWithFormUrlEncoded(String url,
                                                                 HttpMethod method,
                                                                 MultiValueMap<String, String> params,
                                                                 MultiValueMap<String, String> headers,
                                                                 MultiValueMap<String, String> formData) throws URISyntaxException, IOException, InterruptedException {
        HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                .uri(new URI(url + HttpRequestUtil.multiValueMapToQueryString(params)));
        headers.add("Content-Type", "application/x-www-form-urlencoded");
        addMethod(requestBuilder, method, HttpRequest.BodyPublishers.ofString(HttpRequestUtil.multiValueMapToQueryString(formData).substring(1), StandardCharsets.UTF_8));
        addHeaders(requestBuilder, headers);
        HttpResponse<String> response = HttpClient.newHttpClient().send(
                requestBuilder.build(), HttpResponse.BodyHandlers.ofString());
        return response;
    }

    /**
     * The file value is File object.
     *
     * @param url
     * @param method
     * @param params
     * @param headers
     * @param formData
     * @return
     * @throws URISyntaxException
     * @throws IOException
     * @throws InterruptedException
     */
    public static HttpResponse<String> requestWithFormData(String url,
                                                           HttpMethod method,
                                                           MultiValueMap<String, String> params,
                                                           MultiValueMap<String, String> headers,
                                                           MultiValueMap<String, Object> formData) throws URISyntaxException, IOException, InterruptedException {
        HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                .uri(new URI(url + HttpRequestUtil.multiValueMapToQueryString(params)));
        headers.add("Content-Type", "multipart/form-data; boundary=" + MULTIPART_BODY_BOUNDARY);
        addMethod(requestBuilder, method, ofMimeMultipartData(formData, MULTIPART_BODY_BOUNDARY));
        addHeaders(requestBuilder, headers);
        HttpRequest httpRequest = requestBuilder.build();
        System.out.println(httpRequest.bodyPublisher().toString());
        HttpResponse<String> response = HttpClient.newHttpClient().send(
                httpRequest, HttpResponse.BodyHandlers.ofString());
        return response;
    }

    /**
     * multipart/form-data request example
     * <p>
     * POST /test HTTP/1.1
     * Host: foo.example
     * Content-Type: multipart/form-data;boundary="boundary"
     *
     * --boundary
     * Content-Disposition: form-data; name="field1"
     *
     * value1
     * --boundary
     * Content-Disposition: form-data; name="field2"; filename="example.txt"
     *
     * value2
     * --boundary--
     *
     * @param data
     * @param boundary
     * @return
     * @throws IOException
     */
    public static HttpRequest.BodyPublisher ofMimeMultipartData(MultiValueMap<String, Object> data,
                                                                String boundary) throws IOException {
        return HttpRequest.BodyPublishers.ofByteArray(HttpRequestUtil.multiValueMapToMultipartData(data, boundary));
    }

    private static void addMethod(HttpRequest.Builder requestBuilder,
                                  HttpMethod method,
                                  HttpRequest.BodyPublisher bodyPublisher) {
        if (method != null) {
            if (method == HttpMethod.GET) {
                requestBuilder.GET();
            } else if (method == HttpMethod.POST) {
                requestBuilder.POST(bodyPublisher);
            } else if (method == HttpMethod.PUT) {
                requestBuilder.PUT(bodyPublisher);
            } else if (method == HttpMethod.DELETE) {
                requestBuilder.DELETE();
            }
        }
    }

    private static void addHeaders(HttpRequest.Builder requestBuilder, MultiValueMap<String, String> headers) {
        if (headers != null) {
            headers.forEach((key, value) -> {
                if (value != null && value.size() > 0) {
                    value.stream().forEach(v -> requestBuilder.header(key, v));
                }
            });
        }
    }

}
