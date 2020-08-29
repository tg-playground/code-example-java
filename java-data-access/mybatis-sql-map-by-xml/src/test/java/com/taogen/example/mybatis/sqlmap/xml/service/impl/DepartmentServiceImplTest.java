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
    }

    @Test
    public void saveAllTestException() {
        String name = "test_exception" + System.currentTimeMillis();
        Map<String, Object> conditions = new HashMap<>();
        conditions.put("name", name);
        departmentService.deleteAllByMap(conditions);
        List<Department> departments = new ArrayList<>();
        departments.add(new Department(name));
        departments.add(new Department(name));
        assertEquals(0, departmentService.saveAll(departments, true));
        assertEquals(0, departmentService.findAllByFields(new Department(name)).size());

    }

    @Test
    public void deleteById() {
        int deleteId = 1;
        Department department = new Department(deleteId);
        ensureEntityExist(department);
        assertEquals(1, departmentService.deleteById(department));
    }

    @Test
    public void deleteAllByIds() {
        List<Integer> deleteIds = Arrays.asList(1, 2);
        List<Department> departments = ensureEntityListExist(deleteIds);
        assertEquals(deleteIds.size(), departmentService.deleteAllByIds(departments));
    }

    @Test
    public void update() {
        int updateId = 10;
        Department department = new Department(updateId);
        ensureEntityExist(department);
        department = departmentService.getById(department);
        String name = System.currentTimeMillis() + "";
        department.setName(name);
        departmentService.update(department);
        department = departmentService.getById(department);
        assertEquals(name, department.getName());
    }

    @Test
    public void updateAllByIds() {
        List<Integer> updateIds = Arrays.asList(11, 12);
        List<Department> departments = ensureEntityListExist(updateIds);

        String name = System.currentTimeMillis() + "";
        for (Department department : departments) {
            department.setName(name);
        }
        departmentService.updateAllByIds(departments);
        for (Integer id : updateIds) {
            Department department = departmentService.getById(new Department(id));
            assertEquals(name, department.getName());
        }
    }

    @Test
    public void getById() {
        int getId = 20;
        Department department = new Department(getId);
        ensureEntityExist(department);
        assertNotNull(departmentService.getById(department));
    }

    @Test
    public void findPage() {
        long count = departmentService.count();
        if (count == 0) {
            departmentService.save(new Department("test_find_page"));
        }
        int pageNo = 1;
        long pageSize = 10;
        pageSize = count > pageSize ? pageSize : count;
        Page page = new Page(pageNo, (int) pageSize);
        page.setCount(count);
        List<Department> departments = departmentService.findPage(page, new Department());
        assertNotNull(departments);
        assertEquals(pageSize, departments.size());
    }

    @Test
    public void findAllByFields() {
        int id = 31;
        Department department = new Department(id);
        ensureEntityExist(department);
        department = departmentService.getById(department);
        String name = "find_all_by_fields" + System.currentTimeMillis();
        department.setName(name);
        departmentService.update(department);
        assertNotNull(departmentService.findAllByFields(new Department(name)));
    }

    @Test
    public void findAllByMap() {
        int id = 41;
        Department department = new Department(id);
        ensureEntityExist(department);
        department = departmentService.getById(department);
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
        ensureEntityExist(new Department(id));
        assertTrue(departmentService.count() > 0);
    }

    private List<Department> ensureEntityListExist(List<Integer> ids) {
        List<Department> departments = new ArrayList<>();
        for (Integer id : ids) {
            Department department = new Department(id);
            ensureEntityExist(department);
            departments.add(new Department(id));
        }
        return departments;
    }

    private void ensureEntityExist(Department department) {
        if (departmentService.getById(department) == null) {
            departmentService.save(new Department(department.getId(), "ensureEntityExist" + System.currentTimeMillis()));
        }
    }
}