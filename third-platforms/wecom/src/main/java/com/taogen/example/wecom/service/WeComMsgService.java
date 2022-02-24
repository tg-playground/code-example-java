package com.taogen.example.wecom.service;

import com.taogen.example.wecom.util.OkHttpUtil;
import okhttp3.HttpUrl;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author Taogen
 */
@Component
public class WeComMsgService extends BaseWeComService {
    @Autowired
    private WeComAccessTokenService weComAccessTokenService;

    public JSONObject sendMsgToUser(String json) throws IOException {
        System.out.println("To send msg to user...");
        HttpUrl httpUrl = HttpUrl.parse(weComApiRequestPrefix + "message/send").newBuilder()
                .addQueryParameter("access_token", weComAccessTokenService.getAccessToken())
                .build();
        String response = OkHttpUtil.sendPostWithJson(httpUrl, json);
        return new JSONObject(response);
    }
}
