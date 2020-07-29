package com.taogen.example.wechat.service;

/**
 * @author Taogen
 */
public interface WxAccessTokenService {
    String getAccessToken(String type);

    public String getCurrentAccessToken();
}
