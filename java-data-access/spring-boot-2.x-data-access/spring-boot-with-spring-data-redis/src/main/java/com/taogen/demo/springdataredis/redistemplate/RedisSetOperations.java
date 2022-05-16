package com.taogen.demo.springdataredis.redistemplate;

import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author Taogen
 */
@Component
public class RedisSetOperations extends AbstractRedisOperations{
    private SetOperations<String, Object> setOperations;

    /**
     * Use constructor or postConstruct to initialize operations bean
     */
    @PostConstruct
    private void init() {
        setOperations = redisTemplate.opsForSet();
    }

    public SetOperations<String, Object> getSetOperations() {
        return setOperations;
    }

    public void add(String key, Object... values) {
        setOperations.add(key, values);
    }

    @Override
    protected Boolean deleteKey(String key) {
        return super.deleteKey(key);
    }

    public void remove(String key, Object... values) {
        setOperations.remove(key, values);
    }

    public boolean isMember(String key, Object value) {
        return setOperations.isMember(key, value);
    }

    public Long size(String key) {
        return setOperations.size(key);
    }

}
