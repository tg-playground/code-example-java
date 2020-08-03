package com.taogen.example.wechat.service.impl;

import com.taogen.example.wechat.config.WxDomains;
import com.taogen.example.wechat.config.WxUris;
import com.taogen.example.wechat.service.WxAccessTokenService;
import com.taogen.example.wechat.service.WxWebPageService;
import com.taogen.example.wechat.utils.EncryptUtils;
import com.taogen.example.wechat.utils.MyHttpClient;
import com.taogen.example.wechat.vo.JsapiConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @author Taogen
 */
@Service
public class WxWebPageServiceImpl implements WxWebPageService {

    public static final Logger logger = LogManager.getLogger();
    private static final Long JSAPI_TICKET_VALID_TIME = 3600000L;

    private String jsapiTicket;
    private long jsapiTicketAcquiredTimestamp;

    @Autowired
    private WxUris wxUris;
    @Autowired
    private WxDomains wxDomains;
    @Autowired
    private WxAccessTokenService wxAccessTokenService;

    @Override
    public String getJsapiTicket(String accessToken) {
        if (accessToken == null) {
            return null;
        }
        if (isJsapiTicketValid()) {
            logger.debug("Current jsapi ticket is valid!");
            return jsapiTicket;
        } else {
            logger.debug("To get JSAPI Ticket...");
            return toGetJsapiTicket(accessToken);
        }
    }

    private String toGetJsapiTicket(String accessToken) {
        String result = MyHttpClient.doGet(wxDomains.getWechatDomain() +
                String.format(wxUris.JSAPI_TICKET_GET, wxAccessTokenService.getCurrentAccessToken()));
        JSONObject jsonObject = new JSONObject(result);
        this.jsapiTicket = (String) jsonObject.get("ticket");
        this.jsapiTicketAcquiredTimestamp = System.currentTimeMillis();
        return this.jsapiTicket;
    }

    private boolean isJsapiTicketValid() {
        return jsapiTicket != null &&
                System.currentTimeMillis() - jsapiTicketAcquiredTimestamp < JSAPI_TICKET_VALID_TIME;
    }

    @Override
    public String getSignature(JsapiConfig jsapiConfig) {
        SortedMap<String, Object> params = new TreeMap<>();
        params.put("noncestr", jsapiConfig.getNonceStr());
        params.put("jsapi_ticket", jsapiConfig.getJsapiTicket());
        params.put("timestamp", jsapiConfig.getTimestamp());
        params.put("url", jsapiConfig.getUrl());
        StringBuilder stringBuilder = new StringBuilder();
        for (String key : params.keySet()) {
            stringBuilder.append(key);
            stringBuilder.append("=");
            stringBuilder.append(params.get(key));
            stringBuilder.append("&");
        }
        String toSignString = stringBuilder.substring(0, stringBuilder.length() - 1);
        logger.debug("toSignString is {}", toSignString);
        return EncryptUtils.toSHA1(toSignString);
    }
}
