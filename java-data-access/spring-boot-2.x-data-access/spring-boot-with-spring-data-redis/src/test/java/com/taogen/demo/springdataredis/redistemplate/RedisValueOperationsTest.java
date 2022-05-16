package com.taogen.demo.springdataredis.redistemplate;

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
class RedisValueOperationsTest {

    @Autowired
    private RedisValueOperations redisValueOperations;

    @Test
    void getString() {
        String key = "test:" + System.currentTimeMillis();
        String value = "test-" + System.currentTimeMillis();
        redisValueOperations.set(key, value, 10000L);
        String fetchValue = redisValueOperations.getString(key);
        assertNotNull(fetchValue);
        assertEquals(value, fetchValue);
    }

    @Test
    void setString() {
        String key = "test:" + System.currentTimeMillis();
        log.debug("key: " + key);
        String value = "test-" + System.currentTimeMillis();
        redisValueOperations.set(key, value, null);
        String fetchValue = redisValueOperations.getString(key);
        assertNotNull(fetchValue);
        assertEquals(value, fetchValue);
    }

    @Test
    void setString_expire() throws InterruptedException {
        Long timeout = 5L;
        String key = "test:" + System.currentTimeMillis();
        log.debug("key: " + key);
        String value = "test-" + System.currentTimeMillis();
        redisValueOperations.set(key, value, timeout);
        String fetchValue = redisValueOperations.getString(key);
        assertNotNull(fetchValue);
        assertEquals(value, fetchValue);
        Thread.sleep(timeout * 1000);
        fetchValue = redisValueOperations.getString(key);
        assertNull(fetchValue);
    }

    @Test
    void delete() {
        String key = "test:" + System.currentTimeMillis();
        log.debug("key: " + key);
        String value = "test-" + System.currentTimeMillis();
        redisValueOperations.set(key, value, null);
        String fetchValueBeforeDelete = redisValueOperations.getString(key);
        assertNotNull(fetchValueBeforeDelete);
        redisValueOperations.delete(key);
        String fetchValueAfterDelete = redisValueOperations.getString(key);
        assertNull(fetchValueAfterDelete);
    }

    @Test
    void update() {
        String key = "test:" + System.currentTimeMillis();
        log.debug("key: " + key);
        String value = "test-" + System.currentTimeMillis();
        redisValueOperations.set(key, value, null);
        String fetchValueBeforeUpdate = redisValueOperations.getString(key);
        assertNotNull(fetchValueBeforeUpdate);
        String updateValue = "update-" + System.currentTimeMillis();
        redisValueOperations.update(key, updateValue, null);
        String fetchValueAfterUpdate = redisValueOperations.getString(key);
        assertNotNull(fetchValueAfterUpdate);
        assertEquals(updateValue, fetchValueAfterUpdate);
    }
}
