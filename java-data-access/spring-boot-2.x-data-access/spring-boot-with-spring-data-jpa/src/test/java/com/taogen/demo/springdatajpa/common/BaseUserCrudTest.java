package com.taogen.demo.springdatajpa.common;

import com.taogen.demo.springdatajpa.App;
import com.taogen.demo.springdatajpa.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Taogen
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {App.class})
@Slf4j
// Auto rollback for all tests by add @Transactional
// By default, Spring will start a new transaction surrounding your test method and @Before/@After callbacks, rolling back at the end.
@Transactional
public class BaseUserCrudTest extends AbstractCrudJpaTest<User> {
    @Override
    protected User buildEntityWithoutId() {
        return new User("test-name" + System.currentTimeMillis(), "test-password", "test-email");
    }
}
