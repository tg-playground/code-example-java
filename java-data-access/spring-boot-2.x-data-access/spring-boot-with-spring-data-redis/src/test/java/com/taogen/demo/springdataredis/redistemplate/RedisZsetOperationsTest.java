package com.taogen.demo.springdataredis.redistemplate;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Slf4j
class RedisZsetOperationsTest {

    @Autowired
    private RedisZsetOperations redisZsetOperations;

    private String key = "zset";

    public Integer getRandomNumber() {
        return ThreadLocalRandom.current().nextInt(0, 100);
    }

    @Test
    void add() {
        for (int i = 0; i < 10; i++) {
            Integer value = getRandomNumber();
            log.info("value: {}", value);
            redisZsetOperations.add(key, value, getRandomNumber());
        }
    }

    @Test
    void remove() {
    }

    @Test
    void incrementScore() {
    }

    @Test
    void isMember() {
        String randomValue = "random" + System.currentTimeMillis();
        boolean isMemberBefore = redisZsetOperations.isMember(key, randomValue);
        assertFalse(isMemberBefore);

        redisZsetOperations.add(key, randomValue, getRandomNumber());

        Integer value = 30;
        boolean isMember = redisZsetOperations.isMember(key, value);
        assertTrue(isMember);
    }

    @Test
    void range() {
    }

    @Test
    void size() {
    }
}
