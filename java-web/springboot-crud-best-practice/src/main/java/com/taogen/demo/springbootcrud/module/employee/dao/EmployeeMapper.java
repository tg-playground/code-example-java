package com.taogen.demo.springbootcrud.module.employee.dao;


import com.taogen.demo.springbootcrud.core.persistence.mapper.CrudMapper;
import com.taogen.demo.springbootcrud.module.employee.entity.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * @author Taogen
 */
@Component
@Mapper
public interface EmployeeMapper extends CrudMapper<Employee> {
    Employee getByIdWithNestedResults(Employee employee);

    Employee getByIdWithNestedSelect(Employee employee);
}