package com.taogen.example.service.impl;

import com.taogen.example.entity.Employee;
import com.taogen.example.mapper.slave.EmployeeMapper;
import com.taogen.example.service.EmployeeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Taogen
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Resource
    private EmployeeMapper employeeMapper;

    @Override
    public List<Employee> queryList() {
        return employeeMapper.queryList();
    }
}
