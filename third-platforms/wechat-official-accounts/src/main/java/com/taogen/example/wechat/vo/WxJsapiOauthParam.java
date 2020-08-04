package com.taogen.example.wechat.vo;

/**
 * @author Taogen
 */
public class WxJsapiOauthParam {
    private String openId;
    private String jsapiAccessToken;

    public WxJsapiOauthParam(String openId, String jsapiAccessToken) {
        this.openId = openId;
        this.jsapiAccessToken = jsapiAccessToken;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getJsapiAccessToken() {
        return jsapiAccessToken;
    }

    public void setJsapiAccessToken(String jsapiAccessToken) {
        this.jsapiAccessToken = jsapiAccessToken;
    }

    @Override
    public String toString() {
        return "WxJsapiOauthParam{" +
                "openId='" + openId + '\'' +
                ", jsapiAccessToken='" + jsapiAccessToken + '\'' +
                '}';
    }
}
