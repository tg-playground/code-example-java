package com.taogen.example.entity;

import lombok.Data;

/**
 * @author Taogen
 */
@Data
public class User {
    private Integer userId;
    private String userName;
    private String userPassword;
    private String userEmail;
}
