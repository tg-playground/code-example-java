package com.taogen.example.wechat.service;

import com.taogen.example.wechat.vo.WxJsapiConfig;

/**
 * @author Taogen
 */
public interface WxWebPageService {
    String getJsapiTicket(String accessToken);

    String getSignature(WxJsapiConfig jsapiSign);

    String refreshJsapiTicket(String currentAccessToken);
}
