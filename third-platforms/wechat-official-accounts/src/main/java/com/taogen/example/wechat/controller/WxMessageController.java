package com.taogen.example.wechat.controller;

import com.taogen.example.wechat.service.AccessTokenService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Taogen
 */
@RestController
public class WxMessageController extends BasicWxController {
    private static final Logger logger = LogManager.getLogger();

    @Autowired
    private AccessTokenService accessTokenService;
}
