package com.taogen.example.wechat.controller;

import com.taogen.example.wechat.service.AccessTokenService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Taogen
 */
@RestController
public class WxAccessTokenController extends BasicWxController {

    private static final Logger logger = LogManager.getLogger();

    @Autowired
    private AccessTokenService accessTokenService;

    /**
     * Get Access Token
     *
     * @param type product, test
     * @return accessToken
     */
    @GetMapping("/access_tokens/{type}")
    public String getAccessToken(@PathVariable String type) {
        if (type == null) {
            return "error request URL!";
        }
        return accessTokenService.getAccessToken(type);
    }

}
