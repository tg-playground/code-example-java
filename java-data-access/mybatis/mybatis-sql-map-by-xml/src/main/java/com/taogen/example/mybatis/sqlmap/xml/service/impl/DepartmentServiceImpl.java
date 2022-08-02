package com.taogen.example.mybatis.sqlmap.xml.service.impl;

import com.taogen.example.mybatis.sqlmap.xml.entity.Department;
import com.taogen.example.mybatis.sqlmap.xml.mapper.DepartmentMapper;
import com.taogen.example.mybatis.sqlmap.xml.service.AbstractCrudService;
import com.taogen.example.mybatis.sqlmap.xml.service.DepartmentService;

/**
 * @author Taogen
 */
public class DepartmentServiceImpl
        extends AbstractCrudService<DepartmentMapper, Department>
        implements DepartmentService {
}
