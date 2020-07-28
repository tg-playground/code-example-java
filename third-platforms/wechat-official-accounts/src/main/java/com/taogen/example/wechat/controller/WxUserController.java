package com.taogen.example.wechat.controller;

import com.taogen.example.wechat.utils.MyHttpClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Taogen
 */
@RestController
public class WxUserController extends BasicWxController {

    private static final Logger logger = LogManager.getLogger();

    @GetMapping("/user/list")
    public String getUserList() {
        return doGetWithAccessTokenAndOthers(wxUris.USER_LIST_GET_URI, "");
    }

    @GetMapping("user/get")
    public String getUserInfo(@RequestParam("openid") String openid) {
        return doGetWithAccessTokenAndOthers(wxUris.USER_INFO_GET_URI, openid);
    }
}
