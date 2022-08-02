package com.taogen.example.mybatis.sqlmap.annotations.service.impl;

import com.taogen.example.mybatis.sqlmap.annotations.entity.Department;
import com.taogen.example.mybatis.sqlmap.annotations.entity.Page;
import com.taogen.example.mybatis.sqlmap.annotations.service.DepartmentService;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

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
    public void saveOrUpdate() {
        int id = 104;
        Department department = new Department(id, "saveOrUpdate");
        departmentService.deleteById(department);
        if (departmentService.getById(department) == null) {
            assertEquals(1, departmentService.saveOrUpdate(department));
        }
        String name = "saveOrUpdate" + System.currentTimeMillis();
        department.setName(name);
        assertEquals(1, departmentService.saveOrUpdate(department));
        department = departmentService.getById(department);
        assertNotNull(department);
        assertEquals(name, department.getName());
    }

    @Test
    public void deleteById() {
        int deleteId = 105;
        Department department = new Department(deleteId);
        ensureEntityExist(department);
        assertEquals(1, departmentService.deleteById(department));
    }

    @Test
    public void deleteAllByIds() {
        List<Integer> deleteIds = Arrays.asList(106, 107);
        List<Department> departments = ensureEntityListExist(deleteIds);
        assertEquals(deleteIds.size(), departmentService.deleteAllByIds(departments));
    }

    @Test
    public void deleteLogically() {
        int deleteId = 202;
        Department department = new Department(deleteId);
        ensureEntityExist(department);
        String name = "deleteLogically" + System.currentTimeMillis();
        department.setName(name);
        departmentService.update(department);
        assertEquals(1, departmentService.deleteLogically(department));
        department = departmentService.getById(department);
        assertEquals(name, department.getName());
        assertEquals(true, department.getDeleteFlag());
    }

    @Test
    public void deleteAllLogically() {
        List<Integer> deleteIds = Arrays.asList(203, 204);
        List<Department> departments = ensureEntityListExist(deleteIds);
        String name = "deleteAllLogically" + System.currentTimeMillis();
        for (Department department : departments) {
            department = departmentService.getById(department);
            department.setName(name);
            departmentService.update(department);
        }
        departmentService.deleteAllLogically(departments);
        for (Department department : departments) {
            department = departmentService.getById(department);
            assertEquals(name, department.getName());
            assertEquals(true, department.getDeleteFlag());
        }
    }

    @Test
    public void deleteAllByMap() {
        List<Integer> deleteIds = Arrays.asList(108, 109);
        List<Department> departments = ensureEntityListExist(deleteIds);
        String name = "delete_all_by_map";
        for (Department department : departments) {
            department = departmentService.getById(department);
            department.setName(name);
            departmentService.update(department);
        }
        Map<String, Object> conditions = new HashMap<>();
        conditions.put("name", name);
        assertEquals(deleteIds.size(), departmentService.deleteAllByMap(conditions));
    }

    @Test
    public void update() {
        int updateId = 110;
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
        List<Integer> updateIds = Arrays.asList(111, 112);
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
        int getId = 113;
        Department department = new Department(getId);
        ensureEntityExist(department);
        assertNotNull(departmentService.getById(department));
    }

    @Test
    public void count() {
        int id = 114;
        ensureEntityExist(new Department(id));
        assertTrue(departmentService.count() > 0);
    }

    @Test
    public void findPage() {
        departmentService.save(new Department("test_find_page"));
        departmentService.save(new Department("test_find_page2"));
        int pageNo = 1;
        long pageSize = 10;
        Page page = new Page(pageNo, (int) pageSize);
        List<Department> departments = departmentService.findPage(page, new Department("test"));
        assertNotNull(departments);
    }

    @Test
    public void findAllByFields() {
        int id = 115;
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
        int id = 116;
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