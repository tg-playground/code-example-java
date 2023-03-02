package com.taogen.example.mybatis.sqlmap.annotations.service;


import com.taogen.example.mybatis.sqlmap.annotations.entity.Employee;
import com.taogen.example.mybatis.sqlmap.annotations.mapper.EmployeeMapper;

/**
 * @author Taogen
 */
public interface EmployeeService extends CrudService<EmployeeMapper, Employee> {
}
