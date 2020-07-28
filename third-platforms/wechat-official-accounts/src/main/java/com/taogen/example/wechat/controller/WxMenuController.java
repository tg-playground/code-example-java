package com.taogen.example.wechat.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Taogen
 */
@RestController
public class WxMenuController extends BasicWxController {

    private static final Logger logger = LogManager.getLogger();

    @PostMapping("/menu/create")
    public String menuCreate(HttpEntity<String> httpEntity) {
        String menuJson = httpEntity.getBody();
        logger.debug("menu json is:\n {}", menuJson);
        if (menuJson == null) {
            return "error!";
        }
        return doPostWithAccessTokenAndBody(wxUris.MENU_CREATE_URI, menuJson);
    }


    @GetMapping("menu/get")
    public String menuGet() {
        return doGetWithAccessTokenAndOtherParams(wxUris.MENU_QUERY_URI);
    }

}
