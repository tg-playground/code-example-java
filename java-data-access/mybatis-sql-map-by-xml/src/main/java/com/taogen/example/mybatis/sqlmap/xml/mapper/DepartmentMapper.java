package com.taogen.example.mybatis.sqlmap.xml.mapper;

import com.taogen.example.mybatis.sqlmap.xml.entity.Department;

/**
 * @author Taogen
 */
public interface DepartmentMapper extends CrudMapper<Department> {
    Department getByIdWithNestedSelect(Department department);
}