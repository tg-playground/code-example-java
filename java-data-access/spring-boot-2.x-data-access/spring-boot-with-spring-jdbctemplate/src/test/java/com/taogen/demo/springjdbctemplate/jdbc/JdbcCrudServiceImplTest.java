package com.taogen.demo.springjdbctemplate.jdbc;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Types;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
@Slf4j
class JdbcCrudServiceImplTest {

    @Autowired
    private JdbcCrudService jdbcCrudService;

    @Test
    void insert() {
        long id = insertRandomRow();
        assertTrue(id > 0);
    }

    private long insertRandomRow() {
//        String sql = "insert into user (user_name, user_password, user_email) values ('a', 'abc', 'abc@mail.com')";
        String sql = "insert into user (user_name, user_password, user_email) values (?, ?, ?)";
        long id = jdbcCrudService.insert(sql, new String[]{"aa", "abc", "abc@mail.com"}, new int[]{Types.VARCHAR, Types.VARCHAR, Types.VARCHAR});
        log.info("inserted id is {}", id);
        return id;
    }

    @Test
    void update() {
        long id = insertRandomRow();
        String sql = "update user set user_name = 'updated name' where user_id = " + id;
        int updateRows = jdbcCrudService.update(sql, null, null);
        assertEquals(1, updateRows);
    }

    @Test
    void delete() {
        long id = insertRandomRow();
        String sql = "delete from user where user_id = " + id;
        int deleteRows = jdbcCrudService.delete(sql, null, null);
        assertEquals(1, deleteRows);
    }

    @Test
    void selectOne() {
        long id = insertRandomRow();
        String sql = "select * from user where user_id = " + id;
        Map<String, Object> resultMap = jdbcCrudService.selectOne(sql, null, null);
        log.info("selectOne result is: {}", resultMap);
        assertNotNull(resultMap);
    }

    @Test
    void selectList() {
        long id = insertRandomRow();
        String sql = "select * from user order by user_id desc limit 10";
        List<Map<String, Object>> resultMap = jdbcCrudService.selectList(sql, null, null);
        log.info("selectList result is: {}", resultMap);
        assertNotNull(resultMap);
        assertTrue(resultMap.size() > 0);
    }

    @Test
    void selectCount() {
        long id = insertRandomRow();
        String sql = "select count(*) from user";
        long count = jdbcCrudService.selectCount(sql, null, null);
        log.info("selectCount result is: {}", count);
        assertTrue(count > 0);
    }
}