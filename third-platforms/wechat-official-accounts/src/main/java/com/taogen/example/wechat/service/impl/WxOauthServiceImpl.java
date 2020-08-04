package com.taogen.example.wechat.service.impl;

import com.taogen.example.wechat.service.WxOauthService;
import com.taogen.example.wechat.utils.JsonObjectUtils;
import com.taogen.example.wechat.utils.MyHttpClient;
import com.taogen.example.wechat.vo.WxJsapiOauthParam;
import com.taogen.example.wechat.vo.WxUserInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Taogen
 */
@Service
public class WxOauthServiceImpl extends BasicServiceImpl implements WxOauthService {
    private static final Logger logger = LogManager.getLogger();
//    private static final Long JSAPI_ACCESS_TOKEN_VALID_TIME = 3600000L;
//
//    private String jsapiAccessToken;
//    private long jsapiAccessTokenAcquiredTimestamp;
//    private String refreshToken;
//    private String scope;

    @Override
    public WxJsapiOauthParam getJsapiAccessToken(String code) {
        logger.debug("To get jsapiAccessToken...");
        WxJsapiOauthParam wxJsapiOauthParam = toGetJsapiAccessToken(code);
        logger.debug("wxJsapiOauthParam is {}", wxJsapiOauthParam);
        return wxJsapiOauthParam;
    }

//    private boolean isJsapiAccessTokenValid() {
//        return this.jsapiAccessToken != null &&
//                System.currentTimeMillis() - this.jsapiAccessTokenAcquiredTimestamp < JSAPI_ACCESS_TOKEN_VALID_TIME;
//    }

    private WxJsapiOauthParam toGetJsapiAccessToken(String code) {
        String requestUrl = wxDomains.getWechatDomain() +
                String.format(wxUris.JSAPI_OAUTH_GET_ACCESS_TOKEN_URI, wxConfig.getAppId(), wxConfig.getAppSecret(), code);
        String returnMessage = MyHttpClient.doGet(requestUrl);
        JSONObject jsonObject = JsonObjectUtils.getJsonObjectFromStr(returnMessage);
        String jsapiAccessToken = null;
        String openId = null;
        String refreshToken;
        String scope;
        if (jsonObject != null) {
            jsapiAccessToken = JsonObjectUtils.getString(jsonObject, "access_token");
            refreshToken = JsonObjectUtils.getString(jsonObject, "refresh_token");
            scope = JsonObjectUtils.getString(jsonObject, "scope");
            openId = JsonObjectUtils.getString(jsonObject, "openid");
        }
//        updateJsapiAccessToken(jsonObject);
        return new WxJsapiOauthParam(openId, jsapiAccessToken);
    }

//    private void updateJsapiAccessToken(JSONObject jsonObject) {
//        if (jsonObject != null) {
//            this.jsapiAccessToken = jsonObject.getString("access_token");
//            this.refreshToken = jsonObject.getString("refresh_token");
//            this.scope = jsonObject.getString("scope");
//            this.jsapiAccessTokenAcquiredTimestamp = System.currentTimeMillis();
//        }
//    }


    @Override
    public WxUserInfo getWxUserInfo(WxJsapiOauthParam jsapiAccessToken) {
        logger.debug("To get Wechat user Info...");
        String requestUrl = wxDomains.getWechatDomain() + String.format(
                wxUris.JSAPI_OAUTH_GET_USER_INFO_URI, jsapiAccessToken.getJsapiAccessToken(),
                jsapiAccessToken.getOpenId());
        String returnMessage = MyHttpClient.doGet(requestUrl);
        JSONObject jsonObject = JsonObjectUtils.getJsonObjectFromStr(returnMessage);
        WxUserInfo wxUserInfo = new WxUserInfo();
        if (jsonObject != null) {
            wxUserInfo.setOpenId(JsonObjectUtils.getString(jsonObject, "openid"));
            wxUserInfo.setNickname(JsonObjectUtils.getString(jsonObject, "nickname"));
            wxUserInfo.setSex(JsonObjectUtils.getInt(jsonObject, "sex"));
            wxUserInfo.setProvince(JsonObjectUtils.getString(jsonObject, "province"));
            wxUserInfo.setCity(JsonObjectUtils.getString(jsonObject, "city"));
            wxUserInfo.setCountry(JsonObjectUtils.getString(jsonObject, "country"));
            wxUserInfo.setHeadimgurl(JsonObjectUtils.getString(jsonObject, "headimgurl"));
            JSONArray privilege = JsonObjectUtils.getJsonArray(jsonObject, "privilege");
            if (privilege != null) {
                wxUserInfo.setPrivilege(privilege.toList());
            }
            wxUserInfo.setUnionid(JsonObjectUtils.getString(jsonObject, "unionid"));
        }
        logger.debug("WxUserInfo is {}", wxUserInfo);
        return wxUserInfo;
    }
}
