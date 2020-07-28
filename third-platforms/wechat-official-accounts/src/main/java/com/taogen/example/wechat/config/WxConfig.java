package com.taogen.example.wechat.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Taogen
 */
@Component
public class WxConfig {

    private static final Logger logger = LogManager.getLogger();

    @Value("${wechat.token}")
    private String token;

    @Value("${wechat.appId}")
    private String appId;

    @Value("${wechat.appSecret}")
    private String appSecret;

    @Value("${wechat.testAppId}")
    private String testAppId;

    @Value("${wechat.testAppSecret}")
    private String testAppSecret;

    public String getToken() {
        return token;
    }

    public String getAppId() {
        return appId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public String getTestAppId() {
        return testAppId;
    }

    public String getTestAppSecret() {
        return testAppSecret;
    }
}
