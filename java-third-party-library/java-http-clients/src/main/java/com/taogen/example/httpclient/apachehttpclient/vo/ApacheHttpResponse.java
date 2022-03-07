package com.taogen.example.httpclient.apachehttpclient.vo;

import lombok.Data;
import okhttp3.Headers;
import org.apache.hc.core5.http.Header;

import java.util.List;

/**
 * @author Taogen
 */
@Data
public class ApacheHttpResponse {
    private Integer statusCode;
    private List<Header> headers;
    private String body;
}
