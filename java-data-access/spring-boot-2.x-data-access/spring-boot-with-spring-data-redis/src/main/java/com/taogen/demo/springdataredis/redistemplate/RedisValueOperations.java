package com.taogen.demo.springdataredis.redistemplate;

import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

/**
 * @author Taogen
 */
@Component
public class RedisValueOperations extends AbstractRedisOperations {

    private ValueOperations<String, Object> valueOperations;

    /**
     * Use constructor or postConstruct to initialize operations bean
     */
    @PostConstruct
    private void initializeHashOperations() {
        valueOperations = redisTemplate.opsForValue();
    }

    public void set(String key, Object value, Long timeout) {
        valueOperations.set(key, value);
        expireKey(key, timeout);
    }

    public boolean delete(String key) {
        return super.deleteKey(key);
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
