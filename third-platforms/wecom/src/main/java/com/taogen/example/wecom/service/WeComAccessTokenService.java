package com.taogen.example.wecom.service;

import com.taogen.example.wecom.cache.AccessTokenCacheHandler;
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
public class WeComAccessTokenService extends BaseWeComService {

    private static final String ACCESS_TOKEN_KEY = "access_token";

    private static final Integer SUCCESS_CODE = 0;

    @Autowired
    private AccessTokenCacheHandler cacheHandler;

    public String getAccessToken() throws IOException {
        String accessToken = cacheHandler.getKeyValue(ACCESS_TOKEN_KEY);
        if (accessToken == null) {
            System.out.println("access token is null or expired in cache");
            accessToken = getAccessTokenFromWeCom();
            cacheHandler.setKeyValue(ACCESS_TOKEN_KEY, accessToken);
        } else {
            System.out.println("access token exists in cache");
        }
        return accessToken;
    }

    /**
     * {"errcode":40014,"errmsg":"invalid access_token"}
     *
     * @return
     * @throws IOException
     */
    public boolean isAccessTokenValid() throws IOException {
        String url = weComApiRequestPrefix + "get_api_domain_ip";
        String responseBody = OkHttpUtil.sendGet(HttpUrl.parse(url).newBuilder()
                .addQueryParameter(ACCESS_TOKEN_KEY, cacheHandler.getKeyValue(ACCESS_TOKEN_KEY))
                .build());
        return SUCCESS_CODE.equals(new JSONObject(responseBody).getInt("errcode"));
    }

    public void removeAccessTokenFromCache() {
        cacheHandler.deleteKey(ACCESS_TOKEN_KEY);
    }

    public String getAccessTokenFromWeCom() throws IOException {
        // {"errcode":0,"errmsg":"ok","access_token":"","expires_in":7200}
        // 开发者需要缓存access_token，用于后续接口的调用（注意：不能频繁调用gettoken接口，否则会受到频率拦截）。当access_token失效或过期时，需要重新获取。
        // access_token的有效期通过返回的expires_in来传达，正常情况下为7200秒（2小时），有效期内重复获取返回相同结果，过期后获取会返回新的access_token。
        // 由于企业微信每个应用的access_token是彼此独立的，所以进行缓存时需要区分应用来进行存储。
        System.out.println("To get access token from weCom...");
        String url = weComApiRequestPrefix + "gettoken";
        HttpUrl httpUrl = HttpUrl.parse(url).newBuilder()
                .addQueryParameter("corpid", weComProperties.getCorpId())
                .addQueryParameter("corpsecret", weComProperties.getAppSecret())
                .build();
        String responseBody = OkHttpUtil.sendGet(httpUrl);
        JSONObject jsonObject = new JSONObject(responseBody);
        System.out.println("Get access token from weCom successfully");
        return jsonObject.getString(ACCESS_TOKEN_KEY);
    }
}
