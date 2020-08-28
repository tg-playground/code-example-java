package com.taogen.example.mybatis.sqlmap.xml.mapper;

import com.taogen.example.mybatis.sqlmap.xml.entity.Department;
import com.taogen.example.mybatis.sqlmap.xml.entity.Page;
import com.taogen.example.mybatis.sqlmap.xml.service.SqlSessionFactoryService;
import com.taogen.example.mybatis.sqlmap.xml.service.impl.SqlSessionFactoryServiceImpl;
import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class DepartmentMapperTest {

    private DepartmentMapper mapper;
    private SqlSessionFactoryService sqlSessionFactoryService = new SqlSessionFactoryServiceImpl();


    @Before
    public void getMapper() {
        SqlSession sqlSession = sqlSessionFactoryService.getSqlSessionFactory().openSession(true);
        mapper = sqlSession.getMapper(DepartmentMapper.class);
    }


    @Test
    public void saveSelective() {
        assertEquals(1, mapper.saveSelective(new Department("test_save_selective")));
    }

    @Test
    public void deleteById() {
        int deleteId = 1;
        ensureEntityExist(deleteId);
        assertEquals(1, mapper.deleteById(deleteId));
    }

    @Test
    public void deleteByIdLogically() {
        int id = 2;
        ensureEntityExist(id);
        Department department = mapper.getById(id);
        department.setDeleteFlag(true);
        mapper.updateSelective(department);
        Department updatedDepartment = mapper.getById(id);
        assertNotNull(updatedDepartment);
        assertTrue(updatedDepartment.getDeleteFlag());
    }

    @Test
    public void deleteAll() {
        List<Integer> deleteIds = Arrays.asList(11, 12);
        List<Department> departments = new ArrayList<>();
        for (Integer id : deleteIds) {
            ensureEntityExist(id);
        }
        for (Integer id : deleteIds) {
            departments.add(new Department(id));
        }
        assertEquals(deleteIds.size(), mapper.deleteAll(departments));
    }

    @Test
    public void deleteAllByField() {
        List<Integer> deleteIds = Arrays.asList(1, 2);
        List<Department> departments = new ArrayList<>();
        for (Integer id : deleteIds) {
            ensureEntityExist(id);
        }
        for (Integer id : deleteIds) {
            departments.add(mapper.getById(id));
        }
        String name = "delete_all_by_field" + System.currentTimeMillis();
        for (Department department : departments) {
            department.setName(name);
        }
        mapper.updateAll(departments);
        assertTrue(mapper.deleteAllByField(new Department(name)) >= deleteIds.size());
    }

    @Test
    public void deleteByMap() {
        List<Integer> deleteIds = Arrays.asList(1, 2);
        List<Department> departments = new ArrayList<>();
        for (Integer id : deleteIds) {
            ensureEntityExist(id);
        }
        for (Integer id : deleteIds) {
            departments.add(mapper.getById(id));
        }
        String name = "delete_all_by_map" + System.currentTimeMillis();
        for (Department department : departments) {
            department.setName(name);
        }
        mapper.updateAll(departments);
        Map<String, Object> params = new HashMap<>();
        params.put("name", name);
        assertTrue(mapper.deleteAllByMap(params) >= deleteIds.size());
    }

    @Test
    public void updateSelective() {
        int updateId = 10;
        ensureEntityExist(updateId);
        Department department = mapper.getById(updateId);
        long timestamp = System.currentTimeMillis();
        department.setName(timestamp + "");
        mapper.updateSelective(department);
        department = mapper.getById(updateId);
        assertEquals(timestamp, department.getName());
    }

    @Test
    public void updateAll() {

        List<Integer> updateIds = Arrays.asList(11, 12);
        List<Department> departments = new ArrayList<>();
        for (Integer id : updateIds) {
            ensureEntityExist(id);
        }
        for (Integer id : updateIds) {
            departments.add(mapper.getById(id));
        }

        long timestamp = System.currentTimeMillis();
        for (Department department : departments) {
            department.setName(timestamp + "");
        }
        mapper.updateAll(departments);
        for (Integer id : updateIds) {
            Department department = mapper.getById(id);
            assertEquals(timestamp, department.getName());
        }
    }

    @Test
    public void getById() {
        int id = 1;
        ensureEntityExist(id);
        assertNotNull(mapper.getById(id));
    }

    @Test
    public void count() {
        int id = 1;
        ensureEntityExist(id);
        assertTrue(mapper.count() >= 1);
    }

    @Test
    public void countByMap() {
        String name = "count_by_map" + System.currentTimeMillis();
        mapper.saveSelective(new Department(name));
        Map<String, Object> params = new HashMap<>();
        params.put("name", name);
        assertTrue(mapper.countByMap(params) >= 1);
    }

    @Test
    public void findAllByFields() {
        String name = "find_all_by_fields" + System.currentTimeMillis();
        mapper.saveSelective(new Department(name));
        List<Department> departments = mapper.findAllByFields(new Department(name));
        assertNotNull(departments);
        assertTrue(departments.size() >= 1);
    }

    @Test
    public void findPage() {
        long count = mapper.count();
        if (count == 0) {
            mapper.saveSelective(new Department("test_find_page"));
        }
        long pageSize = 10;
        pageSize = count > pageSize ? pageSize : count;
        Page page = new Page();
        page.setPageNo(1);
        page.setPageSize((int) pageSize);
        page.setCount(count);
        List<Department> departments = mapper.findPage(page, new Department());
        assertNotNull(departments);
        assertEquals(pageSize, departments.size());
    }

    @Test
    public void findAllByMap() {
        int id = 41;
        ensureEntityExist(id);
        Department department = mapper.getById(id);
        String name = "find_all_by_map" + System.currentTimeMillis();
        department.setName(name);
        mapper.updateSelective(department);
        Map<String, Object> params = new HashMap<>();
        params.put("name", name);
        assertNotNull(mapper.findAllByMap(params));

    }

    private void ensureEntityExist(Integer id) {
        if (mapper.getById(id) == null) {
            mapper.saveSelective(new Department(id, "ensureEntityExist" + System.currentTimeMillis()));
        }
    }
}