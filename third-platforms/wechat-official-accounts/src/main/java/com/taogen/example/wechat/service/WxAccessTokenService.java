package com.taogen.example.wechat.service;

/**
 * @author Taogen
 */
public interface WxAccessTokenService {
    String getAccessToken(String type);

    String getCurrentAccessToken();
}
