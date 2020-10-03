package com.taogen.example.mybatisplus.crud.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taogen.example.mybatisplus.crud.entity.Department;
import com.taogen.example.mybatisplus.crud.mapper.DepartmentMapper;
import com.taogen.example.mybatisplus.crud.service.IDepartmentService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * department table 服务实现类
 * </p>
 *
 * @author Taogen
 * @since 2020-10-03
 */
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements IDepartmentService {

}
