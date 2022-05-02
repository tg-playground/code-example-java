package com.taogen.demo.springjdbctemplate.dao.impl;

import com.taogen.demo.common.vo.Page;
import com.taogen.demo.springjdbctemplate.dao.UserDao;
import com.taogen.demo.springjdbctemplate.entity.User;
import com.taogen.demo.springjdbctemplate.rowmapper.UserRowMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Taogen
 */
@Slf4j
@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * dynamic query
     *
     * @param page
     * @param user
     * @return
     */
    @Override
    public List<User> listUsers(Page page, User user) {
        String query = "select * from user where 1=1 ";
        List<Object> params = new ArrayList<>();
        if (user != null) {
            if (user.getUserName() != null && !user.getUserName().isEmpty()) {
                query += "AND user_name like ?";
                params.add(new StringBuilder().append("%").append(user.getUserName()).append("%").toString());
            }
            if (user.getUserPassword() != null && !user.getUserPassword().isEmpty()) {
                query += "AND user_password = ?";
                params.add(user.getUserPassword());
            }
            if (user.getUserEmail() != null && !user.getUserEmail().isEmpty()) {
                query += " user_email like ?";
                params.add(new StringBuilder().append("%").append(user.getUserEmail()).append("%").toString());
            }
        }
        if (page != null) {
            query += new StringBuilder()
                    .append(" limit ")
                    .append((page.getPageNo() - 1) * page.getPageSize())
                    .append(",")
                    .append(page.getPageSize())
                    .toString();
        }
        log.debug("query sql: {}", query);
        List<User> users = jdbcTemplate.query(query, new UserRowMapper(), params.toArray());
        return users;
    }

    @Override
    public User getUser(Integer id) {
        String query = "select * from user where user_id = ?";
        User entity = null;
        try {
            entity = jdbcTemplate.queryForObject(query, new UserRowMapper(), id);
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
        }
        return entity;
    }

    @Override
    public int saveUser(User user) {
        String sql = "INSERT INTO user (user_name, user_password, user_email) VALUES (?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
//        int result = jdbcTemplate.update(sql, user.getUserName(), user.getUserPassword(), user.getUserEmail());
        int result = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getUserName());
            ps.setString(2, user.getUserPassword());
            ps.setString(3, user.getUserEmail());
            return ps;
        }, keyHolder);

        log.debug("keyHolder.getKey() = {}", keyHolder.getKey());
        user.setUserId(keyHolder.getKey().intValue());
        return result;
    }

    @Override
    public int updateUser(User user) {
        String sql = "UPDATE user SET user_name = ?, user_password = ?, user_email = ? WHERE user_id = ?";
        int result = jdbcTemplate.update(sql, user.getUserName(), user.getUserPassword(), user.getUserEmail(), user.getUserId());
        return result;
    }

    @Override
    public int deleteUser(Integer id) {
        return jdbcTemplate.update("delete from user where user_id = ?", id);
    }
}
