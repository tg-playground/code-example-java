package com.taogen.example.wechat.vo;

/**
 * @author Taogen
 */
public class JsapiConfig {
    private String appId;
    private String jsapiTicket;
    private String nonceStr;
    private int timestamp;
    private String url;
    private String signature;

    public JsapiConfig(String jsapiTicket, String nonceStr, int timestamp, String url) {
        this.jsapiTicket = jsapiTicket;
        this.nonceStr = nonceStr;
        this.timestamp = timestamp;
        this.url = url;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getJsapiTicket() {
        return jsapiTicket;
    }

    public void setJsapiTicket(String jsapiTicket) {
        this.jsapiTicket = jsapiTicket;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}
