package com.taogen.example.servlet.filter.entity;

import lombok.Data;

@Data
public class User {
    private String username;
    private String gender;
    private String birthDate;
    public User(){}
    public User(String username){
        this.username = username;
    }
}
