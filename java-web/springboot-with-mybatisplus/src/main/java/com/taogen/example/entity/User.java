package com.taogen.example.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author Taogen
 */
@Data
@TableName("user")
public class User {
    @TableId(value = "user_id")
    private Integer userId;
    private String userName;
    private String userPassword;
    private String userEmail;
}
