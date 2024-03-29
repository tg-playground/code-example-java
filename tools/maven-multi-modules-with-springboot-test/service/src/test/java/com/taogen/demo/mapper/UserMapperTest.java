package com.taogen.demo.mapper;

import com.taogen.demo.domain.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

//    @SpringBootApplication
//    @MapperScan("com.taogen.demo.**.mapper")
//    static class TestConfiguration {
//    }

    @Test
    void list() {
        List<User> users = userMapper.selectList(null);
        System.out.println(users);
    }
}
