package com.taogen.demo.springbootcrud.module.employee.service.impl;


import com.taogen.demo.springbootcrud.core.web.service.AbstractCrudService;
import com.taogen.demo.springbootcrud.module.employee.dao.EmployeeMapper;
import com.taogen.demo.springbootcrud.module.employee.entity.Employee;
import com.taogen.demo.springbootcrud.module.employee.service.EmployeeService;
import org.springframework.stereotype.Service;

/**
 * @author Taogen
 */
@Service
public class EmployeeServiceImpl
        extends AbstractCrudService<EmployeeMapper, Employee>
        implements EmployeeService {
}
