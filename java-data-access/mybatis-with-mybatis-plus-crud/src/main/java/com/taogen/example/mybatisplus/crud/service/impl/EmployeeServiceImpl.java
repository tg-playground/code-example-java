package com.taogen.example.mybatisplus.crud.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taogen.example.mybatisplus.crud.entity.Employee;
import com.taogen.example.mybatisplus.crud.mapper.EmployeeMapper;
import com.taogen.example.mybatisplus.crud.service.IEmployeeService;
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
