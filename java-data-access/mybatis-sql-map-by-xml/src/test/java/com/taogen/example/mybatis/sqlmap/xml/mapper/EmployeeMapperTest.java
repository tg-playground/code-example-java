package com.taogen.example.mybatis.sqlmap.xml.mapper;

import com.taogen.example.mybatis.sqlmap.xml.entity.Department;
import com.taogen.example.mybatis.sqlmap.xml.entity.Employee;
import com.taogen.example.mybatis.sqlmap.xml.entity.Page;
import com.taogen.example.mybatis.sqlmap.xml.service.DepartmentService;
import com.taogen.example.mybatis.sqlmap.xml.service.SqlSessionFactoryService;
import com.taogen.example.mybatis.sqlmap.xml.service.impl.DepartmentServiceImpl;
import com.taogen.example.mybatis.sqlmap.xml.service.impl.SqlSessionFactoryServiceImpl;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class EmployeeMapperTest {

    private EmployeeMapper mapper;
    private SqlSessionFactoryService sqlSessionFactoryService = new SqlSessionFactoryServiceImpl();
    private DepartmentService departmentService = new DepartmentServiceImpl();
    private SqlSession sqlSession;

    @Before
    public void before() {
        sqlSession = sqlSessionFactoryService.getSqlSessionFactory().openSession(true);
        mapper = sqlSession.getMapper(EmployeeMapper.class);
    }

    @After
    public void after() {
        sqlSession.close();
    }

    @Test
    public void saveSelective() {
        assertEquals(1, mapper.saveSelective(new Employee("test_save_selective")));
    }

    @Test
    public void deleteById() {
        int deleteId = 11;
        ensureEntityExist(new Employee(deleteId));
        assertEquals(1, mapper.deleteById(new Employee(deleteId)));
    }

    @Test
    public void deleteAll() {
        List<Integer> deleteIds = Arrays.asList(21, 22);
        List<Employee> employees = ensureEntityListExist(deleteIds);
        assertEquals(deleteIds.size(), mapper.deleteAll(employees));
    }

    @Test
    public void deleteAllByField() {
        List<Integer> deleteIds = Arrays.asList(31, 32);
        List<Employee> employees = ensureEntityListExist(deleteIds);
        String name = "delete_all_by_field" + System.currentTimeMillis();
        for (Employee employee : employees) {
            employee.setName(name);
            mapper.updateSelective(employee);
        }
        assertTrue(mapper.deleteAllByField(new Employee(name)) >= deleteIds.size());
    }

    @Test
    public void deleteAllByMap() {
        List<Integer> deleteIds = Arrays.asList(41, 42);
        List<Employee> employees = ensureEntityListExist(deleteIds);
        String name = "delete_all_by_map" + System.currentTimeMillis();
        for (Employee employee : employees) {
            employee.setName(name);
            mapper.updateSelective(employee);
        }
        Map<String, Object> params = new HashMap<>();
        params.put("name", name);
        assertTrue(mapper.deleteAllByMap(params) >= deleteIds.size());
    }

    @Test
    public void updateSelective() {
        int updateId = 51;
        Employee employee = new Employee(updateId);
        ensureEntityExist(employee);
        employee = mapper.getById(employee);
        String name = System.currentTimeMillis() + "";
        employee.setName(name);
        mapper.updateSelective(employee);
        employee = mapper.getById(employee);
        assertEquals(name, employee.getName());
    }

    @Test
    public void updateAllFieldsByMap() {
        List<Integer> updateIds = Arrays.asList(61, 62);
        List<Employee> employees = ensureEntityListExist(updateIds);
        String name = System.currentTimeMillis() + "";
        for (Employee employee : employees) {
            employee.setName(name);
            mapper.updateSelective(employee);
        }
        String newName = "new_name" + System.currentTimeMillis();
        Map<String, Object> conditions = new HashMap<>();
        conditions.put("name", name);
        mapper.updateAllFieldsByMap(new Employee(newName), conditions);
        for (Integer id : updateIds) {
            Employee employee = mapper.getById(new Employee(id));
            assertEquals(newName, employee.getName());
        }
    }

    @Test
    public void getById() {
        int id = 71;
        Employee employee = new Employee(id);
        ensureEntityExist(employee);

        Department department = new Department(1, "test");
        departmentService.saveOrUpdate(department);
        employee.setDepartment(department);
        mapper.updateSelective(employee);

        assertNotNull(mapper.getById(employee));
        System.out.println(mapper.getById(employee));
    }

    @Test
    public void count() {
        int id = 81;
        ensureEntityExist(new Employee(id));
        assertTrue(mapper.count() >= 1);
    }

    @Test
    public void countByField() {
        String name = "count_by_field" + System.currentTimeMillis();
        mapper.saveSelective(new Employee(name));
        assertTrue(mapper.countByField(new Employee(name)) >= 1);
    }

    @Test
    public void countByMap() {
        String name = "count_by_map" + System.currentTimeMillis();
        mapper.saveSelective(new Employee(name));
        Map<String, Object> params = new HashMap<>();
        params.put("name", name);
        assertTrue(mapper.countByMap(params) >= 1);
    }

    @Test
    public void findAllByFields() {
        String name = "find_all_by_fields" + System.currentTimeMillis();
        mapper.saveSelective(new Employee(name));
        List<Employee> employees = mapper.findAllByFields(new Employee(name));
        assertNotNull(employees);
        assertTrue(employees.size() >= 1);
    }

    @Test
    public void findPage() {
        long count = mapper.count();
        if (count == 0) {
            mapper.saveSelective(new Employee("test_find_page"));
        }
        int pageNo = 1;
        long pageSize = 10;
        pageSize = count > pageSize ? pageSize : count;
        Page page = new Page(pageNo, (int) pageSize);
        page.setOrderBy("name");
        List<Employee> employees = mapper.findPage(page, new Employee());
        assertNotNull(employees);
        assertEquals(pageSize, employees.size());
//        for (Employee d : employees) {
//            System.out.println(d.getId() + "," + d.getName());
//        }
    }

    @Test
    public void findAllByMap() {
        int id = 91;
        Employee employee = new Employee(id);
        ensureEntityExist(employee);
        employee = mapper.getById(employee);
        String name = "find_all_by_map" + System.currentTimeMillis();
        employee.setName(name);
        mapper.updateSelective(employee);
        Map<String, Object> params = new HashMap<>();
        params.put("name", name);
        assertNotNull(mapper.findAllByMap(params));
    }

//    @Test
//    public void execSelectSql() {
//        String name = "exec_dql_sql" + System.currentTimeMillis();
//        mapper.saveSelective(new Employee(name));
//        String sql = "select * from t_employee where name=\"" + name + "\"";
//        List<Employee> employees = mapper.execSelectSql(sql);
//        assertNotNull(employees);
//        assertTrue(employees.size() >= 1);
//
//    }

//    @Test
//    public void execDeleteSql() {
//        int deleteId = 1;
//        ensureEntityExist(new Employee(deleteId));
//        String sql = "delete from t_employee where id=" + deleteId;
//        assertEquals(1, mapper.execDeleteSql(sql));
//    }

    private List<Employee> ensureEntityListExist(List<Integer> ids) {
        List<Employee> employees = new ArrayList<>();
        for (Integer id : ids) {
            Employee employee = new Employee(id);
            ensureEntityExist(employee);
            employees.add(new Employee(id));
        }
        return employees;
    }

    private void ensureEntityExist(Employee employee) {
        if (mapper.getById(employee) == null) {
            mapper.saveSelective(new Employee(employee.getId(), "ensureEntityExist" + System.currentTimeMillis()));
        }
    }
}