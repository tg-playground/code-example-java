package com.taogen.example.httpclient.okhttp.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import okhttp3.Headers;

/**
 * @author Taogen
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OkHttpResponse {
    private Integer statusCode;
    private Headers headers;
    private String body;
}
