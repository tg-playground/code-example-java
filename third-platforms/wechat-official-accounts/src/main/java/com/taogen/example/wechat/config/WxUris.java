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

    @Value("${wechat.apis.jsapi.jsapi_ticket_get_uri}")
    public String JSAPI_TICKET_GET;

    @Value("${wechat.apis.jsapi.weixin_user_oauth_get_code_url}")
    public String JSAPI_OAUTH_GET_CODE_URL;

    @Value("${wechat.apis.jsapi.weixin_user_oauth_get_access_token_uri}")
    public String JSAPI_OAUTH_GET_ACCESS_TOKEN_URI;

    @Value("${wechat.apis.jsapi.weixin_user_oauth_refresh_access_token_uri}")
    public String JSAPI_OAUTH_REFRESH_ACCESS_TOKEN_URI;

    @Value("${wechat.apis.jsapi.weixin_user_oauth_get_user_info_uri}")
    public String JSAPI_OAUTH_GET_USER_INFO_URI;

    @Value("${wechat.apis.jsapi.weixin_user_oauth_verify_access_token_validity_uri}")
    public String JSAPI_VERIFY_ACCESS_TOKEN_URI;

}
