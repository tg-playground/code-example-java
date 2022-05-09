package com.taogen.demo.springdataredis;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Slf4j
class RedisOperationsTest {

    @Autowired
    private RedisOperations redisOperations;

    @Test
    void getString() {
        String key = "test:" + System.currentTimeMillis();
        String value = "test-" + System.currentTimeMillis();
        redisOperations.setString(key, value, 10000L);
        String fetchValue = redisOperations.getString(key);
        assertNotNull(fetchValue);
        assertEquals(value, fetchValue);
    }

    @Test
    void setString() {
        String key = "test:" + System.currentTimeMillis();
        log.debug("key: " + key);
        String value = "test-" + System.currentTimeMillis();
        redisOperations.setString(key, value, null);
        String fetchValue = redisOperations.getString(key);
        assertNotNull(fetchValue);
        assertEquals(value, fetchValue);
    }

    @Test
    void setString_expire() throws InterruptedException {
        Long timeout = 5L;
        String key = "test:" + System.currentTimeMillis();
        log.debug("key: " + key);
        String value = "test-" + System.currentTimeMillis();
        redisOperations.setString(key, value, timeout);
        String fetchValue = redisOperations.getString(key);
        assertNotNull(fetchValue);
        assertEquals(value, fetchValue);
        Thread.sleep(timeout * 1000);
        fetchValue = redisOperations.getString(key);
        assertNull(fetchValue);
    }

    @Test
    void delete() {
        String key = "test:" + System.currentTimeMillis();
        log.debug("key: " + key);
        String value = "test-" + System.currentTimeMillis();
        redisOperations.setString(key, value, null);
        String fetchValueBeforeDelete = redisOperations.getString(key);
        assertNotNull(fetchValueBeforeDelete);
        redisOperations.delete(key);
        String fetchValueAfterDelete = redisOperations.getString(key);
        assertNull(fetchValueAfterDelete);
    }

    @Test
    void update() {
        String key = "test:" + System.currentTimeMillis();
        log.debug("key: " + key);
        String value = "test-" + System.currentTimeMillis();
        redisOperations.setString(key, value, null);
        String fetchValueBeforeUpdate = redisOperations.getString(key);
        assertNotNull(fetchValueBeforeUpdate);
        String updateValue = "update-" + System.currentTimeMillis();
        redisOperations.update(key, updateValue, null);
        String fetchValueAfterUpdate = redisOperations.getString(key);
        assertNotNull(fetchValueAfterUpdate);
        assertEquals(updateValue, fetchValueAfterUpdate);
    }
}
