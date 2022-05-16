package com.taogen.demo.springdataredis.redistemplate;

import org.springframework.data.redis.core.HashOperations;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @author Taogen
 */
public class RedisHashOperations extends AbstractRedisOperations {
    private HashOperations<String, String, Object> hashOperations;

    /**
     * Use constructor or postConstruct to initialize operations bean
     */
    @PostConstruct
    private void init() {
        hashOperations = redisTemplate.opsForHash();
    }

    public HashOperations<String, String, Object> getHashOperations() {
        return hashOperations;
    }

    public void put(String key, String field, Object value, Long timeout) {
        hashOperations.put(key, field, value);
        expireKey(key, timeout);
    }

    @Override
    public Boolean deleteKey(String key) {
        return super.deleteKey(key);
    }

    public void deleteHashKeys(String key, String... hashKeys) {
        hashOperations.delete(key, hashKeys);
    }

    public Object get(String key, String field) {
        return hashOperations.get(key, field);
    }

    public List<Object> getHashValues(String key, List<String> fields) {
        return hashOperations.multiGet(key, fields);
    }
}
