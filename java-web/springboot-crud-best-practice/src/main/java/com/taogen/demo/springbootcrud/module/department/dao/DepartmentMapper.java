package com.taogen.demo.springbootcrud.module.department.dao;


import com.taogen.demo.springbootcrud.core.persistence.mapper.CrudMapper;
import com.taogen.demo.springbootcrud.module.department.entity.Department;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * @author Taogen
 */
@Component
@Mapper
public interface DepartmentMapper extends CrudMapper<Department> {
    Department getByIdWithNestedSelect(Department department);
}