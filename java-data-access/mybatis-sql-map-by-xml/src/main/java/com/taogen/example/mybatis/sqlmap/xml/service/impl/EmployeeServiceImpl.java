package com.taogen.example.mybatis.sqlmap.xml.service.impl;

import com.taogen.example.mybatis.sqlmap.xml.entity.Employee;
import com.taogen.example.mybatis.sqlmap.xml.mapper.EmployeeMapper;
import com.taogen.example.mybatis.sqlmap.xml.service.AbstractCrudService;
import com.taogen.example.mybatis.sqlmap.xml.service.EmployeeService;

/**
 * @author Taogen
 */
public class EmployeeServiceImpl
        extends AbstractCrudService<EmployeeMapper, Employee>
        implements EmployeeService {
}
