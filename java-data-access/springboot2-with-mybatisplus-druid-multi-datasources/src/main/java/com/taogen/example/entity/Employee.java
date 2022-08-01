package com.taogen.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author Taogen
 */
@TableName("t_employee")
@Data
public class Employee {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String name;
    private String nickname;
    private Integer age;
    private Boolean deleteFlag;
    private Date createTime;
    private Date modifyTime;
}
