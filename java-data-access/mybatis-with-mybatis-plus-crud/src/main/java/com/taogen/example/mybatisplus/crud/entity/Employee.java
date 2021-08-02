package com.taogen.example.mybatisplus.crud.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.taogen.example.mybatisplus.crud.core.annotation.Related;
import com.taogen.example.mybatisplus.crud.core.vo.IdName;
import com.taogen.example.mybatisplus.crud.service.IDepartmentService;
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

    @TableField(exist = false)
    @Related(relatedType = Related.RelatedType.SINGLE, relatedFieldName = "deptId",
            serviceClass = IDepartmentService.class, returnType = IdName.class)
    private IdName department;

    /**
     * delete flag
     */
    @TableLogic
    private Boolean deleteFlag;

    private LocalDateTime createTime;

    private LocalDateTime modifyTime;


}
