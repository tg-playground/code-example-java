package com.taogen.demo.springdataredis.redistemplate;

import org.springframework.data.redis.core.ListOperations;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @author Taogen
 */
@Component
public class RedisListOperations extends AbstractRedisOperations{
    private ListOperations<String, Object> listOperations;

    /**
     * Use constructor or postConstruct to initialize operations bean
     */
    @PostConstruct
    public void init() {
        listOperations = redisTemplate.opsForList();
    }

    public ListOperations<String, Object> getListOperations() {
        return listOperations;
    }

    public void push(String key, Object value) {
        listOperations.rightPush(key, value);
    }

    @Override
    protected Boolean deleteKey(String key) {
        return super.deleteKey(key);
    }

    public void delete(String key, Object value) {
        listOperations.remove(key, 0, value);
    }

    public void deleteByRange(String key, long start, long end) {
        listOperations.trim(key, start, end);
    }

    public Object getByIndex(String key, Long index) {
        return listOperations.index(key, index);
    }

    public List<Object> getByRange(String key, Long start, Long end) {
        return listOperations.range(key, start, end);
    }

    public boolean objectExists(String key, Object value) {
        return listOperations.indexOf(key, value) != -1;
    }

    public Long size(String key) {
        return listOperations.size(key);
    }
}
