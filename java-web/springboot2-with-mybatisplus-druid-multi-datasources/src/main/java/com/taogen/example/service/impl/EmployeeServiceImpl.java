package com.taogen.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taogen.example.entity.Employee;
import com.taogen.example.mapper.slave.EmployeeMapper;
import com.taogen.example.service.EmployeeService;
import org.springframework.stereotype.Service;

/**
 * @author Taogen
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee>
        implements EmployeeService {
}
