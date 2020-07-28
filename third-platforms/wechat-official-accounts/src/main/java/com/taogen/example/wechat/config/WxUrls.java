package com.taogen.example.wechat.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Taogen
 */
@Component
public class WxUrls {

    @Value("${wechat.apis.access_token.access_token_get_url}")
    public String ACCESS_TOKEN_GET_URL;

    @Value("${wechat.apis.menu.menu_create_url}")
    public String MENU_CREATE_URL;

    @Value("${wechat.apis.menu.menu_query_url}")
    public String MENU_QUERY_URL;

    @Value("${wechat.apis.user.user_list_get_url}")
    public String USER_LIST_GET_URL;

    @Value("${wechat.apis.user.user_info_get_url}")
    public String USER_INFO_GET_URL;
}
