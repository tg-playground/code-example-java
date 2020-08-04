package com.taogen.example.wechat.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Taogen
 */
@RestController
@RequestMapping("/wxAccessToken")
public class WxAccessTokenController extends BasicWxController {

    private static final Logger logger = LogManager.getLogger();

    /**
     * Get Access Token
     *
     * @param type product, test
     * @return accessToken
     */
    @GetMapping("/get/{type}")
    public String getAccessToken(@PathVariable String type) {
        if (type == null) {
            return "error request URL!";
        }
        return accessTokenService.getAccessToken(type);
    }

}
