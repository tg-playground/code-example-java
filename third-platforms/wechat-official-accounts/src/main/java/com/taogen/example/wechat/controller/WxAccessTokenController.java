package com.taogen.example.wechat.controller;

import com.taogen.example.wechat.utils.MyHttpClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Taogen
 */
@RestController
public class WxAccessTokenController extends BasicWxController {

    private static final Logger logger = LogManager.getLogger();
    private static final Long ACCESS_TOKEN_VALID_TIME = 3600000L;

    private String accessToken;
    private long accessTokenAcquiredTimestamp;
    private String testAccessToken;
    private long testAccessTokenAcquiredTimestamp;

    /**
     * Get Access Token
     * @param type product, test
     * @return accessToken
     */
    @GetMapping("/access_tokens/{type}")
    public String getAccessToken(@PathVariable String type) {
        String accessToken = getAccessTokenHelper(type);
        if (accessToken == null) {
            return "error request URL!";
        }
        return accessToken;
    }

    public String getAccessTokenHelper(String type) {
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
        String getAccessTokenUrl = null;
        if ("product".equals(type)) {
            getAccessTokenUrl = String.format(wxUrls.ACCESS_TOKEN_GET_URL,
                    wxConfig.getAppId(), wxConfig.getAppSecret());
        }
        if ("test".equals(type)) {
            getAccessTokenUrl = String.format(wxUrls.ACCESS_TOKEN_GET_URL,
                    wxConfig.getTestAppId(), wxConfig.getTestAppSecret());
        }
        logger.debug("getAccessTokenUrl is {}", getAccessTokenUrl);
        String result = MyHttpClient.doGet(getAccessTokenUrl);
        logger.debug("Weixin API return: {}", result);
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
