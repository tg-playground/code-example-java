package com.taogen.demo.springdataredis.redistemplate;

import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Set;

/**
 * @author Taogen
 */
@Component
public class RedisZsetOperations extends AbstractRedisOperations {

    /**
     * sorted set
     */
    private ZSetOperations<String, Object> zSetOperations;

    /**
     * Use constructor or postConstruct to initialize operations bean
     */
    @PostConstruct
    private void init() {
        zSetOperations = redisTemplate.opsForZSet();
    }

    public ZSetOperations<String, Object> getZSetOperations() {
        return this.zSetOperations;
    }

    public void add(String key, Object value, double score) {
        zSetOperations.add(key, value, score);
    }

    @Override
    protected Boolean deleteKey(String key) {
        return super.deleteKey(key);
    }

    public void remove(String key, Object value) {
        zSetOperations.remove(key, value);
    }

    public void incrementScore(String key, Object value, double score) {
        zSetOperations.incrementScore(key, value, score);
    }

    public boolean isMember(String key, Object value) {
        // or return zSetOperations.rank(key, value) != null;
        return zSetOperations.score(key, value) != null;
    }

    public Set<Object> range(String key, long start, long end) {
        return zSetOperations.range(key, start, end);
    }

    public Set<Object> rangeByScore(String key, double min, double max) {
        return zSetOperations.rangeByScore(key, min, max);
    }

    public Long size(String key) {
        return zSetOperations.size(key);
    }
}
