package com.taogen.example.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author Taogen
 */
@Data
public class Employee {
    private Integer id;
    private String name;
    private String nickname;
    private Integer age;
    private Boolean deleteFlag;
    private Date createTime;
    private Date modifyTime;
}
