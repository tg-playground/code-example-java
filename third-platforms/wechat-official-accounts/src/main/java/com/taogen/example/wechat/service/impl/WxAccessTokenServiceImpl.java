package com.taogen.example.wechat.service.impl;

import com.taogen.example.wechat.service.WxAccessTokenService;
import com.taogen.example.wechat.utils.JsonObjectUtils;
import com.taogen.example.wechat.utils.MyHttpClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

/**
 * @author Taogen
 */
@Service
public class WxAccessTokenServiceImpl extends BasicServiceImpl implements WxAccessTokenService {
    private static final Logger logger = LogManager.getLogger();
    private static final Long ACCESS_TOKEN_VALID_TIME = 3600000L;

    private String accessToken;
    private long accessTokenAcquiredTimestamp;
    private String testAccessToken;
    private long testAccessTokenAcquiredTimestamp;

    @Override
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

    @Override
    public String getCurrentAccessToken() {
        if ("test".equals(WECHAT_ENVIRONMENT)) {
            return getAccessToken("test");
        }
        if ("product".equals(WECHAT_ENVIRONMENT)) {
            return getAccessToken("product");
        }
        return "";
    }

    @Override
    public String refreshAccessToken() {
        return getAccessTokenByType(WECHAT_ENVIRONMENT);
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
        JSONObject jsonObject = JsonObjectUtils.getJsonObjectFromStr(result);
        if (jsonObject != null) {
            accessToken = JsonObjectUtils.getString(jsonObject, "access_token");
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
