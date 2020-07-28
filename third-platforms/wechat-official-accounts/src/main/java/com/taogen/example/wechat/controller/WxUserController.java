package com.taogen.example.wechat.controller;

import com.taogen.example.wechat.service.AccessTokenService;
import com.taogen.example.wechat.utils.MyHttpClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Taogen
 */
@RestController
public class WxUserController extends BasicWxController {

    private static final Logger logger = LogManager.getLogger();

    @Autowired
    private AccessTokenService accessTokenService;

    @GetMapping("/user/list")
    public String getUserList() {
        String accessToken = accessTokenService.getCurrentAccessToken();
        String requestUri = String.format(wxUris.USER_LIST_GET_URL, accessToken, "");
        String result = MyHttpClient.doGet(wxDomains.getWechatDomain() + requestUri);
        logger.debug("Weixin API return: {}", result);
        return result;
    }

    @GetMapping("user/get")
    public String getUserInfo(@RequestParam("openid") String openid) {
        String accessToken = accessTokenService.getCurrentAccessToken();
        String requestUri = String.format(wxUris.USER_INFO_GET_URL, accessToken, openid);
        String result = MyHttpClient.doGet(wxDomains.getWechatDomain() + requestUri);
        logger.debug("Weixin API return: {}", result);
        return result;
    }
}
