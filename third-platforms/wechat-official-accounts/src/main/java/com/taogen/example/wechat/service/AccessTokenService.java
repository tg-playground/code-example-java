package com.taogen.example.wechat.service;

import com.taogen.example.wechat.config.WxConfig;
import com.taogen.example.wechat.config.WxDomains;
import com.taogen.example.wechat.config.WxUris;
import com.taogen.example.wechat.utils.MyHttpClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author Taogen
 */
@Service
public class AccessTokenService {
    private static final Logger logger = LogManager.getLogger();
    private static final Long ACCESS_TOKEN_VALID_TIME = 3600000L;

    private String accessToken;
    private long accessTokenAcquiredTimestamp;
    private String testAccessToken;
    private long testAccessTokenAcquiredTimestamp;

    @Value("${wechat.project_settings.environment}")
    private String WECHAT_ENVIRONMENT;

    @Autowired
    private WxConfig wxConfig;

    @Autowired
    private WxUris wxUris;

    @Autowired
    private WxDomains wxDomains;

    public String getAccessToken(String type) {
        String accessToken = null;
        if (isAccessTokenValid(type)) {
            logger.debug("Current accessToken is valid!");
            accessToken = returnAccessTokenByType(type);
        } else {
            logger.debug("To get accessToken...");
            accessToken = getAccessTokenByType(type);
        }
        logger.debug("AccessToken is {}", accessToken);
        return accessToken;
    }

    public String getCurrentAccessToken() {
        if ("test".equals(WECHAT_ENVIRONMENT)) {
            return getAccessToken("test");
        }
        if ("product".equals(WECHAT_ENVIRONMENT)) {
            return getAccessToken("product");
        }
        return "";
    }

    private boolean isAccessTokenValid(String type) {
        if ("product".equals(type)) {
            return accessToken != null &&
                    System.currentTimeMillis() - accessTokenAcquiredTimestamp < ACCESS_TOKEN_VALID_TIME;
        }
        if ("test".equals(type)) {
            return testAccessToken != null &&
                    System.currentTimeMillis() - testAccessTokenAcquiredTimestamp < ACCESS_TOKEN_VALID_TIME;
        }
        return false;
    }

    private String returnAccessTokenByType(String type) {
        if ("product".equals(type)) {
            return this.accessToken;
        }
        if ("test".equals(type)) {
            return this.testAccessToken;
        }
        return null;
    }

    private String getAccessTokenByType(String type) {
        logger.debug("Type is {}", type);
        String getAccessTokenUri = null;
        if ("product".equals(type)) {
            getAccessTokenUri = String.format(wxUris.ACCESS_TOKEN_GET_URI,
                    wxConfig.getAppId(), wxConfig.getAppSecret());
        }
        if ("test".equals(type)) {
            getAccessTokenUri = String.format(wxUris.ACCESS_TOKEN_GET_URI,
                    wxConfig.getTestAppId(), wxConfig.getTestAppSecret());
        }
        logger.debug("getAccessTokenUri is {}", getAccessTokenUri);
        logger.debug("Current Wechat domain is: {}", wxDomains.getWechatDomain());
        String result = MyHttpClient.doGet(wxDomains.getWechatDomain() + getAccessTokenUri);
        String accessToken = null;
        JSONObject json = new JSONObject(result);
        if (json != null) {
            accessToken = json.getString("access_token");
        }
        setAccessToken(type, accessToken);
        return accessToken;
    }

    private void setAccessToken(String type, String accessToken) {
        if ("product".equals(type)) {
            this.accessToken = accessToken;
            this.accessTokenAcquiredTimestamp = System.currentTimeMillis();
        }
        if ("test".equals(type)) {
            this.testAccessToken = accessToken;
            this.testAccessTokenAcquiredTimestamp = System.currentTimeMillis();
        }
    }
}
