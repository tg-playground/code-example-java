package com.taogen.example.wechat.controller;

import com.taogen.example.wechat.config.WxConfig;
import com.taogen.example.wechat.config.WxDomains;
import com.taogen.example.wechat.config.WxUris;
import com.taogen.example.wechat.service.AccessTokenService;
import com.taogen.example.wechat.utils.MyHttpClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Taogen
 */
@Component
public class BasicWxController {
    private static final Logger logger = LogManager.getLogger();

    @Autowired
    protected WxConfig wxConfig;
    @Autowired
    protected WxUris wxUris;
    @Autowired
    protected WxDomains wxDomains;
    @Autowired
    protected AccessTokenService accessTokenService;

    protected String doGetWithAccessTokenAndOtherParams(String uri, Object... params) {
        String accessToken = accessTokenService.getCurrentAccessToken();
        Object[] args = null;
        if (params != null && params.length > 0) {
            args = new Object[params.length + 1];
            for (int i = 0; i < params.length; i++) {
                args[i + 1] = params[i];
            }
        } else {
            args = new Object[1];
        }
        args[0] = accessToken;
        String requestUri = String.format(uri, args);
        return MyHttpClient.doGet(wxDomains.getWechatDomain() + requestUri);
    }

    protected String doPostWithAccessTokenAndBody(String uri, String json) {
        String accessToken = accessTokenService.getCurrentAccessToken();
        String requestUri = String.format(uri, accessToken);
        return MyHttpClient.doPost(wxDomains.getWechatDomain() + requestUri, json);
    }
}
