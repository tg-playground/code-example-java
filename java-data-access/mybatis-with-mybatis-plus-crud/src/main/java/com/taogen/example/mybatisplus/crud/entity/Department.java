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
 * department table
 * </p>
 *
 * @author Taogen
 * @since 2020-10-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_department")
public class Department implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * department ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * department name
     */
    private String name;

    /**
     * delete flag
     */
    @TableLogic
    private Boolean deleteFlag;

    private LocalDateTime createTime;

    private LocalDateTime modifyTime;

    public Department() {
    }

    public Department(String name) {
        this.name = name;
    }
}
