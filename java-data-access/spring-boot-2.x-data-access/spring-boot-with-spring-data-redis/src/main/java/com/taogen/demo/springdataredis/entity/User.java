package com.taogen.demo.springdataredis.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

/**
 * @author Taogen
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("user")
public class User {
    @Id
    private Integer userId;
    private String userName;
    private String userPassword;
    private String userEmail;
    @TimeToLive
    private Long expiry;

    public User(String userName, String userPassword, String userEmail) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.userEmail = userEmail;
    }
}
