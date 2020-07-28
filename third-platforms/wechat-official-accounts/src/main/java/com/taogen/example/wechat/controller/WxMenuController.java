package com.taogen.example.wechat.controller;

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
public class WxMenuController extends BasicWxController{

    private static final Logger logger = LogManager.getLogger();

    @Autowired
    private WxAccessTokenController accessTokenController;

    @PostMapping("/menu/create")
    public String menuCreate(HttpEntity<String> httpEntity) {
        logger.debug("call /menu/create!");
        String menuJson = httpEntity.getBody();
        logger.debug("menu json is:\n {}", menuJson);
        if (menuJson == null) {
            return "error!";
        }
        String accessToken = accessTokenController.getAccessTokenHelper("test");
        logger.debug("AccessToken is {}", accessToken);
        String requestUrl = wxUrls.MENU_CREATE_URL;
        logger.debug("requestUrl is {}", requestUrl);
        requestUrl = String.format(requestUrl, accessToken);
        logger.debug("formatted requestUrl is {}", requestUrl);
        String result = MyHttpClient.doPost(requestUrl, menuJson);
        logger.debug("Weixin API return: {}", result);
        return result;
    }

    @GetMapping("menu/get")
    public String menuGet(){
        String accessToken = accessTokenController.getAccessTokenHelper("test");
        String requestUrl = String.format(wxUrls.MENU_QUERY_URL, accessToken);
        String result = MyHttpClient.doGet(requestUrl);
        logger.debug("Weixin API return: {}", result);
        return result;
    }

}
