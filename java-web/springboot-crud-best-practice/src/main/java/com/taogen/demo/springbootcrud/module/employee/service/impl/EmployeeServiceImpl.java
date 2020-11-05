package com.taogen.demo.springbootcrud.module.employee.service.impl;


import com.taogen.demo.springbootcrud.common.service.AbstractCrudService;
import com.taogen.demo.springbootcrud.module.employee.dao.EmployeeMapper;
import com.taogen.demo.springbootcrud.module.employee.entity.Employee;
import com.taogen.demo.springbootcrud.module.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Taogen
 */
@Service
public class EmployeeServiceImpl
        extends AbstractCrudService<EmployeeMapper, Employee>
        implements EmployeeService {
    @Autowired
    @Override
    public void setMapper(EmployeeMapper mapper) {
        this.mapper = mapper;
    }
}
