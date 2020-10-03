package com.taogen.example.mybatisplus.employee.service.impl;

import com.taogen.example.mybatisplus.employee.entity.Employee;
import com.taogen.example.mybatisplus.employee.mapper.EmployeeMapper;
import com.taogen.example.mybatisplus.employee.service.IEmployeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * employee table 服务实现类
 * </p>
 *
 * @author Taogen
 * @since 2020-10-03
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements IEmployeeService {

}
