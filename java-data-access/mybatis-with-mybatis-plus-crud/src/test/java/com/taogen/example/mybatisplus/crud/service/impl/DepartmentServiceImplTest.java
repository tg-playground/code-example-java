package com.taogen.example.mybatisplus.crud.service.impl;

import com.taogen.example.mybatisplus.crud.entity.Department;
import com.taogen.example.mybatisplus.crud.service.IDepartmentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
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