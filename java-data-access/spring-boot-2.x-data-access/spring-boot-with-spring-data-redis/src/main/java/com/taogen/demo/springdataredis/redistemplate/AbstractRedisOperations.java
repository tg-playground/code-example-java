package com.taogen.demo.springdataredis.redistemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * @author Taogen
 */
public abstract class AbstractRedisOperations {
    @Autowired
    protected RedisTemplate<String, Object> redisTemplate;

    protected void expireKey(String key, Long timeout) {
        if (timeout != null && timeout > 0) {
            // or valueOperations.getOperations().expire(key, timeout, TimeUnit.SECONDS);
            redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
        }
    }

    protected Boolean deleteKey(String key) {
        return redisTemplate.delete(key);
    }
}
