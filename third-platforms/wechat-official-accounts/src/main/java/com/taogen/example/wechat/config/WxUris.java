package com.taogen.example.wechat.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Taogen
 */
@Component
public class WxUris {

    @Value("${wechat.apis.access_token.access_token_get_uri}")
    public String ACCESS_TOKEN_GET_URI;

    @Value("${wechat.apis.menu.menu_create_uri}")
    public String MENU_CREATE_URI;

    @Value("${wechat.apis.menu.menu_query_uri}")
    public String MENU_QUERY_URI;

    @Value("${wechat.apis.user.user_list_get_uri}")
    public String USER_LIST_GET_URI;

    @Value("${wechat.apis.user.user_info_get_uri}")
    public String USER_INFO_GET_URI;

    @Value("${wechat.apis.jsapi.jsapi_ticket_get}")
    public String JSAPI_TICKET_GET;
}
