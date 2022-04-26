package com.taogen.demo.springdatajdbc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

/**
 * @author Taogen
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("user")
public class User {
    @Id
    private Integer userId;
    private String userName;
    private String userPassword;
    private String userEmail;

    public User(String userName, String userPassword, String userEmail) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.userEmail = userEmail;
    }
}
