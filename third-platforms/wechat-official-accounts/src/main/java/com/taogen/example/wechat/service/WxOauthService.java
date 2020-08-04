package com.taogen.example.wechat.service;

import com.taogen.example.wechat.vo.WxJsapiOauthParam;
import com.taogen.example.wechat.vo.WxUserInfo;

/**
 * @author Taogen
 */
public interface WxOauthService {
    WxJsapiOauthParam getJsapiAccessToken(String code);

    WxUserInfo getWxUserInfo(WxJsapiOauthParam jsapiAccessToken);
}
