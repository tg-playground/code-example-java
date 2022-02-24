package com.taogen.example.util;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

/**
 * @author Taogen
 */
@Component
public class AccessTokenCacheHandler {

    @Value("${app.wecom.tokenCache.type}")
    private String cacheType;

    @Value("${app.wecom.tokenCache.expiredTimeInSecond}")
    private Long accessTokenExpiredTimeInSecond;

    private LoadingCache<String, String> ACCESS_TOKEN_CACHE;

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    private ValueOperations<String, Object> valueOperations;


    @PostConstruct
    private void initializeHashOperations() {
        ACCESS_TOKEN_CACHE = CacheBuilder.newBuilder()
                .expireAfterWrite(accessTokenExpiredTimeInSecond, TimeUnit.SECONDS)
                .maximumSize(10)
                .build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String key) {
                        return key.toUpperCase();
                    }
                });
        valueOperations = redisTemplate.opsForValue();
    }

    public String getKeyValue(String key) {
        if (cacheType.equals("redis")) {
            Object value = valueOperations.get(key);
            return value != null ? value.toString() : null;
        }
        if (cacheType.equals("guava")) {
            return ACCESS_TOKEN_CACHE.getIfPresent(key);
        }
        return null;
    }

    public void setKeyValue(String key, String value) {
        if (cacheType.equals("redis")) {
            valueOperations.set(key, value, accessTokenExpiredTimeInSecond, TimeUnit.SECONDS);
        }
        if (cacheType.equals("guava")) {
            ACCESS_TOKEN_CACHE.put(key, value);
        }
    }

    public void deleteKey(String key) {
        if (cacheType.equals("redis")) {
            valueOperations.getOperations().delete(key);
        }
        if (cacheType.equals("guava")) {
            ACCESS_TOKEN_CACHE.invalidate(key);
        }
    }
}
