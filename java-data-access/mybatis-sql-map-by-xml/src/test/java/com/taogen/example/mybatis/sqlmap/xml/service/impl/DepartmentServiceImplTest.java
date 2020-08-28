package com.taogen.example.mybatis.sqlmap.xml.service.impl;

import com.taogen.example.mybatis.sqlmap.xml.entity.Department;
import com.taogen.example.mybatis.sqlmap.xml.entity.Page;
import com.taogen.example.mybatis.sqlmap.xml.service.DepartmentService;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

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
        assertEquals(2, departmentService.saveAll(departments, false));
        testException();
    }

    private void testException() {
        List<Department> departments = new ArrayList<>();
        String name = "test_exception" + System.currentTimeMillis();
        departments.add(new Department(name));
        departments.add(new Department(name));
        departmentService.saveAll(departments, true);
        assertNull(departmentService.findAllByFields(new Department(name)));
    }

    @Test
    public void deleteById() {
        int deleteId = 1;
        ensureEntityExist(deleteId);
        assertEquals(1, departmentService.deleteById(new Department(deleteId)));
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
        int updateId = 10;
        ensureEntityExist(updateId);
        Department department = departmentService.getById(updateId);
        long timestamp = System.currentTimeMillis();
        department.setName(timestamp + "");
        departmentService.update(department);
        department = departmentService.getById(updateId);
        assertEquals(timestamp, department.getName());
    }

    @Test
    public void updateAllByIds() {
        List<Integer> updateIds = Arrays.asList(11, 12);
        List<Department> departments = new ArrayList<>();
        for (Integer id : updateIds) {
            ensureEntityExist(id);
        }
        for (Integer id : updateIds) {
            departments.add(departmentService.getById(id));
        }

        long timestamp = System.currentTimeMillis();
        for (Department department : departments) {
            department.setName(timestamp + "");
        }
        departmentService.updateAllByIds(departments);
        for (Integer id : updateIds) {
            Department department = departmentService.getById(id);
            assertEquals(timestamp, department.getName());
        }
    }

    @Test
    public void getById() {
        int id = 20;
        ensureEntityExist(id);
        assertNotNull(departmentService.getById(id));
    }

    @Test
    public void testGetById() {
        int getUserId = 20;
        ensureEntityExist(getUserId);
        assertNotNull(departmentService.getById(new Department(getUserId)));
    }

    @Test
    public void findPage() {
        long count = departmentService.count();
        if (count == 0) {
            departmentService.save(new Department("test_find_page"));
        }
        long pageSize = 10;
        pageSize = count > pageSize ? pageSize : count;
        Page page = new Page();
        page.setPageNo(1);
        page.setPageSize((int) pageSize);
        page.setCount(count);
        List<Department> departments = departmentService.findPage(page, new Department());
        assertNotNull(departments);
        assertEquals(pageSize, departments.size());
    }

    @Test
    public void findAllByFields() {
        int id = 31;
        ensureEntityExist(id);
        Department department = departmentService.getById(id);
        String name = "find_all_by_fields" + System.currentTimeMillis();
        department.setName(name);
        departmentService.update(department);
        assertNotNull(departmentService.findAllByFields(new Department(name)));
    }

    @Test
    public void findAllByMap() {
        int id = 41;
        ensureEntityExist(id);
        Department department = departmentService.getById(id);
        String name = "find_all_by_map" + System.currentTimeMillis();
        department.setName(name);
        departmentService.update(department);
        Map<String, Object> params = new HashMap<>();
        params.put("name", name);
        assertNotNull(departmentService.findAllByMap(params));
    }

    @Test
    public void count() {
        int id = 1;
        ensureEntityExist(id);
        assertTrue(departmentService.count() > 0);
    }

    private void ensureEntityExist(Integer id) {
        if (departmentService.getById(id) == null) {
            departmentService.save(new Department(id));
        }
    }
}