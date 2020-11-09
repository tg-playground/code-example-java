package com.taogen.demo.springbootcrud.module.department.service.impl;


import com.taogen.demo.springbootcrud.core.service.AbstractCrudService;
import com.taogen.demo.springbootcrud.module.department.dao.DepartmentMapper;
import com.taogen.demo.springbootcrud.module.department.entity.Department;
import com.taogen.demo.springbootcrud.module.department.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Taogen
 */
@Service
public class DepartmentServiceImpl
        extends AbstractCrudService<DepartmentMapper, Department>
        implements DepartmentService {
    @Autowired
    @Override
    public void setMapper(DepartmentMapper mapper) {
        this.mapper = mapper;
    }
}
