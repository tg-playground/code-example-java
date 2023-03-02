package com.taogen.example.mybatisplus.crud.service.impl;

import com.taogen.example.mybatisplus.crud.entity.Department;
import com.taogen.example.mybatisplus.crud.service.IDepartmentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class DepartmentServiceImplTest {

    @Autowired
    private IDepartmentService departmentService;

    @Test
    public void test() {
        String name = "service test save";
        Department department = new Department(name);
        assertTrue(departmentService.save(department));
        assertEquals(name, departmentService.getById(department.getId()).getName());
    }
}