package com.taogen.example.httpclient.apachehttpclient;

import com.taogen.example.httpclient.apachehttpclient.vo.ApacheHttpResponse;
import org.apache.hc.client5.http.classic.methods.HttpDelete;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.classic.methods.HttpPut;
import org.apache.hc.client5.http.entity.UrlEncodedFormEntity;
import org.apache.hc.client5.http.entity.mime.FileBody;
import org.apache.hc.client5.http.entity.mime.MultipartEntityBuilder;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.*;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.apache.hc.core5.http.message.BasicNameValuePair;
import org.apache.hc.core5.net.URIBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.util.MultiValueMap;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author Taogen
 */
public class ApacheHttpClientUtil {

    public static ApacheHttpResponse requestWithoutBody(String url,
                                                        HttpMethod method,
                                                        MultiValueMap<String, String> params,
                                                        MultiValueMap<String, String> headers) throws IOException, URISyntaxException, ParseException {
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            URIBuilder builder = new URIBuilder(url);
            addQueryStringParams(builder, params);
            ClassicHttpRequest httpRequest = getHttpRequest(method, builder.build());
            addHeaders(httpRequest, headers);
            try (CloseableHttpResponse httpResponse = httpclient.execute(httpRequest)) {
                ApacheHttpResponse apacheResponse = getApacheResponse(httpResponse);
                EntityUtils.consume(httpResponse.getEntity());
                return apacheResponse;
            }
        }
    }

    public static ApacheHttpResponse requestWithJson(String url,
                                                     HttpMethod method,
                                                     MultiValueMap<String, String> params,
                                                     MultiValueMap<String, String> headers,
                                                     String json) throws IOException, URISyntaxException, ParseException {
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            URIBuilder builder = new URIBuilder(url);
            addQueryStringParams(builder, params);
            ClassicHttpRequest httpRequest = getHttpRequest(method, builder.build());
            addHeaders(httpRequest, headers);
            httpRequest.setEntity(new StringEntity(json, ContentType.APPLICATION_JSON));
            try (CloseableHttpResponse httpResponse = httpclient.execute(httpRequest)) {
                ApacheHttpResponse apacheResponse = getApacheResponse(httpResponse);
                EntityUtils.consume(httpResponse.getEntity());
                return apacheResponse;
            }
        }
    }

    public static ApacheHttpResponse requestWithForm(String url,
                                                     HttpMethod method,
                                                     MultiValueMap<String, String> params,
                                                     MultiValueMap<String, String> headers,
                                                     MultiValueMap<String, String> formData) throws IOException, URISyntaxException, ParseException {
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            URIBuilder builder = new URIBuilder(url);
            addQueryStringParams(builder, params);
            ClassicHttpRequest httpRequest = getHttpRequest(method, builder.build());
            addHeaders(httpRequest, headers);
            httpRequest.setEntity(new UrlEncodedFormEntity(convertMultiValueMapToNameValuePairList(formData)));
            try (CloseableHttpResponse httpResponse = httpclient.execute(httpRequest)) {
                ApacheHttpResponse apacheResponse = getApacheResponse(httpResponse);
                EntityUtils.consume(httpResponse.getEntity());
                return apacheResponse;
            }
        }
    }

    public static ApacheHttpResponse requestWithMultipart(String url,
                                                          HttpMethod method,
                                                          MultiValueMap<String, String> params,
                                                          MultiValueMap<String, String> headers,
                                                          MultiValueMap<String, Object> formData) throws IOException, URISyntaxException, ParseException {
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            URIBuilder builder = new URIBuilder(url);
            addQueryStringParams(builder, params);
            ClassicHttpRequest httpRequest = getHttpRequest(method, builder.build());
            addHeaders(httpRequest, headers);
            httpRequest.setEntity(getMultipartEntity(formData));
            try (CloseableHttpResponse httpResponse = httpclient.execute(httpRequest)) {
                ApacheHttpResponse apacheResponse = getApacheResponse(httpResponse);
                EntityUtils.consume(httpResponse.getEntity());
                return apacheResponse;
            }
        }
    }

    private static ClassicHttpRequest getHttpRequest(HttpMethod method, URI build) {
        if (method == HttpMethod.GET) {
            return new HttpGet(build);
        } else if (method == HttpMethod.POST) {
            return new HttpPost(build);
        } else if (method == HttpMethod.PUT) {
            return new HttpPut(build);
        } else if (method == HttpMethod.DELETE) {
            return new HttpDelete(build);
        }
        return null;
    }

    private static HttpEntity getMultipartEntity(MultiValueMap<String, Object> formData) {
        MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
        if (formData != null) {
            for (Map.Entry<String, List<Object>> entry : formData.entrySet()) {
                List<Object> values = entry.getValue();
                for (Object value : values) {
                    if (value instanceof File) {
                        File file = (File) value;
                        multipartEntityBuilder.addPart(entry.getKey(), new FileBody(file));
                    } else {
                        multipartEntityBuilder.addTextBody(entry.getKey(), value.toString());
                    }
                }
            }
        }
        return multipartEntityBuilder.build();
    }

    private static List<NameValuePair> convertMultiValueMapToNameValuePairList(MultiValueMap<String, String> formData) {
        List<NameValuePair> nameValuePairs = new ArrayList<>();
        if (formData != null) {
            for (Map.Entry<String, List<String>> entry : formData.entrySet()) {
                for (String value : entry.getValue()) {
                    nameValuePairs.add(new BasicNameValuePair(entry.getKey(), value));
                }
            }
        }
        return nameValuePairs;
    }

    private static ApacheHttpResponse getApacheResponse(CloseableHttpResponse httpResponse) throws IOException, ParseException {
        HttpEntity entity = httpResponse.getEntity();
        ApacheHttpResponse response = new ApacheHttpResponse();
        response.setStatusCode(httpResponse.getCode());
        response.setHeaders(Arrays.asList(httpResponse.getHeaders()));
        response.setBody(EntityUtils.toString(entity));
        return response;
    }

    private static void addHeaders(ClassicHttpRequest classicHttpRequest, MultiValueMap<String, String> headers) {
        if (headers != null) {
            for (String key : headers.keySet()) {
                headers.get(key).forEach(value -> classicHttpRequest.addHeader(key, value));
            }
        }
    }

    private static void addQueryStringParams(URIBuilder builder, MultiValueMap<String, String> params) {
        if (params != null) {
            for (String key : params.keySet()) {
                params.get(key).forEach(value -> builder.addParameter(key, value));
            }
        }
    }
}
