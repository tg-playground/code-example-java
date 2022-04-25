package com.taogen.demo.common;

import com.taogen.demo.common.test.AbstractCrudTest;
import com.taogen.demo.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author Taogen
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
@Slf4j
// Auto rollback for all tests by add @Transactional
// By default, Spring will start a new transaction surrounding your test method and @Before/@After callbacks, rolling back at the end.
@Transactional
public class BaseUserCrudTest extends AbstractCrudTest<User> {
    @Override
    protected User buildEntityWithoutId() {
        return new User("test-name" + System.currentTimeMillis(), "test-password", "test-email");
    }
}
