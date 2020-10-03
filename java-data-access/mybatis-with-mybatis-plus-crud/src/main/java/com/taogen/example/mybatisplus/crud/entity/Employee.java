package com.taogen.example.mybatisplus.crud.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * employee table
 * </p>
 *
 * @author Taogen
 * @since 2020-10-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_employee")
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * employee ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * employee name
     */
    private String name;

    private String nickname;

    private Integer age;

    /**
     * department ID of employee
     */
    private Integer deptId;

    /**
     * delete flag
     */
    @TableLogic
    private Boolean deleteFlag;

    private LocalDateTime createTime;

    private LocalDateTime modifyTime;


}
