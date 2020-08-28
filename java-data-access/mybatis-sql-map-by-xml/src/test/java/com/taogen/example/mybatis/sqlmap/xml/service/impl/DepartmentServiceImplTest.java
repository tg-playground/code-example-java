package com.taogen.example.mybatis.sqlmap.xml.service.impl;

import com.taogen.example.mybatis.sqlmap.xml.entity.Department;
import com.taogen.example.mybatis.sqlmap.xml.service.DepartmentService;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class DepartmentServiceImplTest {

    private DepartmentService departmentService = new DepartmentServiceImpl();

    @Test
    public void save() {
        assertEquals(1, departmentService.save(new Department("development")));
    }

    @Test
    public void saveAll() {
        List<Department> departments = new ArrayList<>();
        departments.add(new Department("test"));
        departments.add(new Department("test2"));
        assertEquals(2, departmentService.saveAll(departments));
    }

    @Test
    public void deleteById() {
        int deleteUserId = 1;
        ensureEntityExist(deleteUserId);
        assertEquals(1, departmentService.deleteById(new Department(deleteUserId)));
    }

    @Test
    public void deleteAllByIds() {
        List<Integer> deleteIds = Arrays.asList(1, 2);
        List<Department> departments = new ArrayList<>();
        for (Integer id : deleteIds) {
            ensureEntityExist(id);
        }
        for (Integer id : deleteIds) {
            departments.add(new Department(id));
        }
        assertEquals(deleteIds.size(), departmentService.deleteAllByIds(departments));
    }

    @Test
    public void update() {
        int updateUserId = 10;
        ensureEntityExist(updateUserId);
        Department department = departmentService.getById(updateUserId);
        long timestamp = System.currentTimeMillis();
        department.setName(timestamp + "");
        departmentService.update(department);
        department = departmentService.getById(updateUserId);
        assertEquals(timestamp, department.getName());
    }

    @Test
    public void updateAllByIds() {
        List<Integer> updateUserIds = Arrays.asList(11, 12);
        List<Department> departments = new ArrayList<>();
        for (Integer id : updateUserIds) {
            ensureEntityExist(id);
        }
        for (Integer id : updateUserIds) {
            departments.add(departmentService.getById(id));
        }

        long timestamp = System.currentTimeMillis();
        for (Department department : departments) {
            department.setName(timestamp + "");
        }
        departmentService.updateAllByIds(departments);
        for (Integer id : updateUserIds) {
            Department department = departmentService.getById(id);
            assertEquals(timestamp, department.getName());
        }
    }

    @Test
    public void getById() {
        int getUserId = 20;
        ensureEntityExist(getUserId);
        assertNotNull(departmentService.getById(getUserId));
    }

    @Test
    public void testGetById() {
        int getUserId = 20;
        ensureEntityExist(getUserId);
        assertNotNull(departmentService.getById(new Department(getUserId)));
    }

    @Test
    public void findPage() {
        // TODO
    }

    @Test
    public void findAllByFields() {
    }

    @Test
    public void findAllByMap() {
    }

    private void ensureEntityExist(Integer id) {
        if (departmentService.getById(id) == null) {
            departmentService.save(new Department(id));
        }
    }

}