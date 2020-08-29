package com.taogen.example.mybatis.sqlmap.xml.mapper;

import com.taogen.example.mybatis.sqlmap.xml.entity.Department;
import com.taogen.example.mybatis.sqlmap.xml.entity.Page;
import com.taogen.example.mybatis.sqlmap.xml.service.SqlSessionFactoryService;
import com.taogen.example.mybatis.sqlmap.xml.service.impl.SqlSessionFactoryServiceImpl;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class DepartmentMapperTest {

    private DepartmentMapper mapper;
    private SqlSessionFactoryService sqlSessionFactoryService = new SqlSessionFactoryServiceImpl();
    private SqlSession sqlSession;

    @Before
    public void before() {
        sqlSession = sqlSessionFactoryService.getSqlSessionFactory().openSession(true);
        mapper = sqlSession.getMapper(DepartmentMapper.class);
    }

    @After
    public void after() {
        sqlSession.close();
    }

    @Test
    public void saveSelective() {
        assertEquals(1, mapper.saveSelective(new Department("test_save_selective")));
    }

    @Test
    public void deleteById() {
        int deleteId = 11;
        ensureEntityExist(new Department(deleteId));
        assertEquals(1, mapper.deleteById(new Department(deleteId)));
    }

    @Test
    public void deleteAll() {
        List<Integer> deleteIds = Arrays.asList(21, 22);
        List<Department> departments = ensureEntityListExist(deleteIds);
        assertEquals(deleteIds.size(), mapper.deleteAll(departments));
    }

    @Test
    public void deleteAllByField() {
        List<Integer> deleteIds = Arrays.asList(31, 32);
        List<Department> departments = ensureEntityListExist(deleteIds);
        String name = "delete_all_by_field" + System.currentTimeMillis();
        for (Department department : departments) {
            department.setName(name);
            mapper.updateSelective(department);
        }
        assertTrue(mapper.deleteAllByField(new Department(name)) >= deleteIds.size());
    }

    @Test
    public void deleteAllByMap() {
        List<Integer> deleteIds = Arrays.asList(41, 42);
        List<Department> departments = ensureEntityListExist(deleteIds);
        String name = "delete_all_by_map" + System.currentTimeMillis();
        for (Department department : departments) {
            department.setName(name);
            mapper.updateSelective(department);
        }
        Map<String, Object> params = new HashMap<>();
        params.put("name", name);
        assertTrue(mapper.deleteAllByMap(params) >= deleteIds.size());
    }

    @Test
    public void updateSelective() {
        int updateId = 51;
        Department department = new Department(updateId);
        ensureEntityExist(department);
        department = mapper.getById(department);
        String name = System.currentTimeMillis() + "";
        department.setName(name);
        mapper.updateSelective(department);
        department = mapper.getById(department);
        assertEquals(name, department.getName());
    }

    @Test
    public void updateAllFieldsByMap() {
        List<Integer> updateIds = Arrays.asList(61, 62);
        List<Department> departments = ensureEntityListExist(updateIds);
        String name = System.currentTimeMillis() + "";
        for (Department department : departments) {
            department.setName(name);
            mapper.updateSelective(department);
        }
        String newName = "new_name" + System.currentTimeMillis();
        Map<String, Object> conditions = new HashMap<>();
        conditions.put("name", name);
        mapper.updateAllFieldsByMap(new Department(newName), conditions);
        for (Integer id : updateIds) {
            Department department = mapper.getById(new Department(id));
            assertEquals(newName, department.getName());
        }
    }

    @Test
    public void getById() {
        int id = 71;
        Department department = new Department(id);
        ensureEntityExist(department);
        assertNotNull(mapper.getById(department));
    }

    @Test
    public void count() {
        int id = 81;
        ensureEntityExist(new Department(id));
        assertTrue(mapper.count() >= 1);
    }

    @Test
    public void countByField() {
        String name = "count_by_field" + System.currentTimeMillis();
        mapper.saveSelective(new Department(name));
        assertTrue(mapper.countByField(new Department(name)) >= 1);
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
        int pageNo = 1;
        long pageSize = 10;
        pageSize = count > pageSize ? pageSize : count;
        Page page = new Page(pageNo, (int) pageSize);
        page.setOrderBy("name");
        List<Department> departments = mapper.findPage(page, new Department());
        assertNotNull(departments);
        assertEquals(pageSize, departments.size());
//        for (Department d : departments) {
//            System.out.println(d.getId() + "," + d.getName());
//        }
    }

    @Test
    public void findAllByMap() {
        int id = 91;
        Department department = new Department(id);
        ensureEntityExist(department);
        department = mapper.getById(department);
        String name = "find_all_by_map" + System.currentTimeMillis();
        department.setName(name);
        mapper.updateSelective(department);
        Map<String, Object> params = new HashMap<>();
        params.put("name", name);
        assertNotNull(mapper.findAllByMap(params));
    }

//    @Test
//    public void execSelectSql() {
//        String name = "exec_dql_sql" + System.currentTimeMillis();
//        mapper.saveSelective(new Department(name));
//        String sql = "select * from t_department where name=\"" + name + "\"";
//        List<Department> departments = mapper.execSelectSql(sql);
//        assertNotNull(departments);
//        assertTrue(departments.size() >= 1);
//
//    }

//    @Test
//    public void execDeleteSql() {
//        int deleteId = 1;
//        ensureEntityExist(new Department(deleteId));
//        String sql = "delete from t_department where id=" + deleteId;
//        assertEquals(1, mapper.execDeleteSql(sql));
//    }

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
        if (mapper.getById(department) == null) {
            mapper.saveSelective(new Department(department.getId(), "ensureEntityExist" + System.currentTimeMillis()));
        }
    }
}