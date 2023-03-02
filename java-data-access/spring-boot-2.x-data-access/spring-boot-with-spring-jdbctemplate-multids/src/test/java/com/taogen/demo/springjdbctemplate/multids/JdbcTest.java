package com.taogen.demo.springjdbctemplate.multids;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Taogen
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
@Slf4j
public class JdbcTest {

    @Autowired
    @Qualifier("db1DataSource")
    private DataSource dataSource;

    @Autowired
    @Qualifier("db1JdbcTemplate")
    private JdbcTemplate jdbcTemplate1;
    @Autowired
    @Qualifier("db2JdbcTemplate")
    private JdbcTemplate jdbcTemplate2;

    @Test
    @Disabled
    void testDs() {
        // com.zaxxer.hikari.HikariDataSource
        // or org.apache.tomcat.jdbc.pool.DataSource
        System.out.println(dataSource.getClass().getName());
    }

    @Test
    @Disabled
    void testQuery() {
        List<Map<String, Object>> db1Users = jdbcTemplate1.queryForList("select * from user");
        System.out.println("db1Users:" + db1Users);
        List<Map<String, Object>> db2Users = jdbcTemplate2.queryForList("select * from user");
        System.out.println("db2Users: " + db2Users);
    }

    @Test
    @Disabled
    @Transactional
    void testTransaction() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = dateFormat.format(new Date());
        System.out.println(formattedDate);
        String name1 = "test1-" + formattedDate;
        jdbcTemplate1.execute(
                "INSERT INTO `user` (NAME, age) VALUE ('" + name1 + "', 18)");
        String name2 = "test2-" + formattedDate;
        jdbcTemplate2.execute(
                "INSERT INTO `user` (NAME, age) VALUE ('" + name2 + "', 18)");
        int i = 1 / 0;
    }

}
