package com.taogen.demo.springjdbctemplate.rowmapper;

import com.taogen.demo.springjdbctemplate.entity.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Taogen
 */
public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setUserId(rs.getInt("user_id"));
        user.setUserName(rs.getString("user_name"));
        user.setUserPassword(rs.getString("user_password"));
        user.setUserEmail(rs.getString("user_email"));
        return user;
    }
}
