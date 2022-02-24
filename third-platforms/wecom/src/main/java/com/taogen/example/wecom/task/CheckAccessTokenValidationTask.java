package com.taogen.example.wecom.task;

import com.taogen.example.wecom.service.WeComAccessTokenService;
import com.taogen.example.wecom.cache.AccessTokenCacheHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author Taogen
 */
@Component
public class CheckAccessTokenValidationTask {

    private static final String ACCESS_TOKEN_KEY = "access_token";

    @Autowired
    private WeComAccessTokenService weComAccessTokenService;

    @Autowired
    private AccessTokenCacheHandler accessTokenCacheHandler;

    @Scheduled(initialDelay = 1000, fixedRateString = "${app.wecom.tokenCache.checkTokenValidationTaskRate}")
    public void checkAccessTokenValidation() throws IOException {
        System.out.println("To check access token validation...");
        if (!weComAccessTokenService.isAccessTokenValid()) {
            System.out.println("access token is invalid");
            String accessToken = weComAccessTokenService.getAccessTokenFromWeCom();
            accessTokenCacheHandler.setKeyValue(ACCESS_TOKEN_KEY, accessToken);
        } else {
            System.out.println("access token is valid");
        }
    }
}
