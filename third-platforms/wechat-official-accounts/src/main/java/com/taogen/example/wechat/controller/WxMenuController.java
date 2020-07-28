package com.taogen.example.wechat.controller;

import com.taogen.example.wechat.service.AccessTokenService;
import com.taogen.example.wechat.utils.MyHttpClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private AccessTokenService accessTokenService;

    @PostMapping("/menu/create")
    public String menuCreate(HttpEntity<String> httpEntity) {
        logger.debug("call /menu/create!");
        String menuJson = httpEntity.getBody();
        logger.debug("menu json is:\n {}", menuJson);
        if (menuJson == null) {
            return "error!";
        }
        String accessToken = accessTokenService.getCurrentAccessToken();
        logger.debug("AccessToken is {}", accessToken);
        String requestUri = wxUris.MENU_CREATE_URL;
        logger.debug("requestUri is {}", requestUri);
        requestUri = String.format(requestUri, accessToken);
        logger.debug("formatted requestUri is {}", requestUri);
        String result = MyHttpClient.doPost(wxDomains.getWechatDomain() + requestUri, menuJson);
        logger.debug("Weixin API return: {}", result);
        return result;
    }

    @GetMapping("menu/get")
    public String menuGet() {
        String accessToken = accessTokenService.getCurrentAccessToken();
        String requestUri = String.format(wxUris.MENU_QUERY_URL, accessToken);
        String result = MyHttpClient.doGet(wxDomains.getWechatDomain() + requestUri);
        logger.debug("Weixin API return: {}", result);
        return result;
    }

}
