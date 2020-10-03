package com.taogen.example.mybatisplus.helloworld.entity;

import lombok.Data;

/**
 * @author Taogen
 */
@Data
public class User {
    private Long id;
    private String name;
    private Integer age;
    private String email;
}
