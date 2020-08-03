package com.taogen.example.wechat.service;

import com.taogen.example.wechat.vo.JsapiConfig;

/**
 * @author Taogen
 */
public interface WxWebPageService {
    String getJsapiTicket(String accessToken);

    String getSignature(JsapiConfig jsapiSign);
}
