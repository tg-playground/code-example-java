package com.taogen.demo.springdataredis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

/**
 * @author Taogen
 */
@Component
public class RedisOperations {
    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    private ValueOperations<String, Object> valueOperations;

    private HashOperations<String, String, Object> hashOperations;

    private ListOperations<String, Object> listOperations;

    private SetOperations<String, Object> setOperations;

    /**
     * sorted set
     */
    private ZSetOperations<String, Object> zSetOperations;

    @PostConstruct
    private void initializeHashOperations() {
        valueOperations = redisTemplate.opsForValue();
        hashOperations = redisTemplate.opsForHash();
        listOperations = redisTemplate.opsForList();
        setOperations = redisTemplate.opsForSet();
        zSetOperations = redisTemplate.opsForZSet();
    }

    public void setString(String key, String value, Long timeout) {
        valueOperations.set(key, value);
        if (timeout != null && timeout > 0) {
            // or valueOperations.getOperations().expire(key, timeout, TimeUnit.SECONDS);
            redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
        }
    }

    public boolean delete(String key) {
        return valueOperations.getOperations().delete(key);
    }

    public String update(String key, String value, Long timeout) {
        String oldValue = (String) valueOperations.getAndSet(key, value);
        if (timeout != null && timeout > 0) {
            redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
        }
        return oldValue;
    }

    public String getString(String key) {
        return (String) valueOperations.get(key);
    }
}
