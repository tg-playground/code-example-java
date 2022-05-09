package com.taogen.demo.springdataredis.repository;

import com.taogen.demo.springdataredis.common.AbstractCrudJpaTest;
import com.taogen.demo.springdataredis.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Slf4j
class UserRepositoryTest extends AbstractCrudJpaTest<User> {

    @Autowired
    private UserRepository userRepository;

    @Test
    void listAllUsers() {
        addRandomEntityJpa(userRepository::save, User::getUserId);
        testListEntitiesJpa(userRepository::findAll, PageRequest.of(0, 10));
    }

    @Test
    void getUser() {
        User user = addRandomEntityJpa(userRepository::save, User::getUserId);
        testGetEntityByIdJpa(userRepository::findById, user.getUserId());
    }

    @Test
    void saveUser() {
        addRandomEntityJpa(userRepository::save, User::getUserId);
    }

    @Test
    void updateUser() {
        User user = addRandomEntityJpa(userRepository::save, User::getUserId);
        testUpdateEntityByIdJpa(userRepository::findById, user.getUserId(),
                User::setUserName, userRepository::save, User::getUserName);
    }

    @Test
    void deleteUser() {
        User user = addRandomEntityJpa(userRepository::save, User::getUserId);
        testDeleteEntityByIdJpa(userRepository::deleteById, user.getUserId(), userRepository::findById);
    }

    @Override
    protected User buildEntityWithoutId() {
        User user = new User("test-name" + System.currentTimeMillis(), "test-password", "test-email");
//        user.setExpiry(30L);
        return user;
    }
}
