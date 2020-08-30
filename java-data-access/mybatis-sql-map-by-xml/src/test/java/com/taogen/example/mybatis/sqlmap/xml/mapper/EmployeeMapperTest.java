package com.taogen.example.mybatis.sqlmap.xml.mapper;

import com.taogen.example.mybatis.sqlmap.xml.entity.Department;
import com.taogen.example.mybatis.sqlmap.xml.entity.Employee;
import com.taogen.example.mybatis.sqlmap.xml.entity.Page;
import com.taogen.example.mybatis.sqlmap.xml.service.SqlSessionFactoryService;
import com.taogen.example.mybatis.sqlmap.xml.service.impl.SqlSessionFactoryServiceImpl;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class EmployeeMapperTest {

    private EmployeeMapper mapper;
    private DepartmentMapper departmentMapper;
    private SqlSessionFactoryService sqlSessionFactoryService = new SqlSessionFactoryServiceImpl();
    private SqlSession sqlSession;

    @Before
    public void before() {
        sqlSession = sqlSessionFactoryService.getSqlSessionFactory().openSession(true);
        mapper = sqlSession.getMapper(EmployeeMapper.class);
        departmentMapper = sqlSession.getMapper(DepartmentMapper.class);
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
        int deleteId = 201;
        ensureEntityExist(new Employee(deleteId));
        assertEquals(1, mapper.deleteById(new Employee(deleteId)));
    }

    @Test
    public void deleteAll() {
        List<Integer> deleteIds = Arrays.asList(202, 203);
        List<Employee> employees = ensureEntityListExist(deleteIds);
        assertEquals(deleteIds.size(), mapper.deleteAll(employees));
    }

    @Test
    public void deleteLogically() {
        int deleteId = 202;
        Employee employee = new Employee(deleteId);
        ensureEntityExist(employee);
        String name = "deleteLogically" + System.currentTimeMillis();
        employee.setName(name);
        mapper.updateSelective(employee);
        assertEquals(1, mapper.deleteLogically(employee));
        employee = mapper.getById(employee);
        System.out.println(employee);
        assertEquals(name, employee.getName());
        assertEquals(true, employee.getDeleteFlag());
    }

    @Test
    public void deleteAllLogically() {
        List<Integer> deleteIds = Arrays.asList(12, 13);
        List<Employee> employees = ensureEntityListExist(deleteIds);
        String name = "deleteAllLogically" + System.currentTimeMillis();
        for (Employee employee : employees) {
            employee = mapper.getById(employee);
            employee.setName(name);
            mapper.updateSelective(employee);
        }
        mapper.deleteAllLogically(employees);
        for (Employee employee : employees) {
            employee = mapper.getById(employee);
            assertEquals(name, employee.getName());
            assertEquals(true, employee.getDeleteFlag());
        }
    }

    @Test
    public void deleteAllByField() {
        List<Integer> deleteIds = Arrays.asList(204, 205);
        mapper.deleteAll(deleteIds.stream().map(deleteId -> new Employee(deleteId)).collect(Collectors.toList()));
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
        List<Integer> deleteIds = Arrays.asList(206, 207);
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
        int updateId = 208;
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
        List<Integer> updateIds = Arrays.asList(209, 210);
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
        int id = 211;
        Employee employee = new Employee(id);
        ensureEntityExist(employee);

        String deptName = "test" + System.currentTimeMillis();
        Department department = new Department(1, deptName);
        if (departmentMapper.getById(department) == null) {
            departmentMapper.saveSelective(department);
        } else {
            departmentMapper.updateSelective(department);
        }

        employee.setDepartment(department);
        mapper.updateSelective(employee);

        employee = mapper.getById(employee);
        assertNotNull(employee);
        System.out.println(employee);
        assertNotNull(employee.getDepartment());
        assertEquals(deptName, employee.getDepartment().getName());
    }

    @Test
    public void count() {
        int id = 212;
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
    }

    @Test
    public void findAllByMap() {
        int id = 213;
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

    @Test
    public void execInsertSql() {
        String sql = "insert into t_employee (name) values ('Tom'), ('John')";
        assertEquals(2, mapper.execInsertSql(sql));
    }

    @Test
    public void execDeleteSql() {
        int id = 11;
        ensureEntityExist(new Employee(id, "exec_delete_sql"));
        String sql = "delete from t_employee where id=" + id;
        assertEquals(1, mapper.execDeleteSql(sql));
    }

    @Test
    public void execUpdateSql() {
        int id = 12;
        ensureEntityExist(new Employee(id, "exec_update_sql"));
        String updateName = "exec_update_sql" + System.currentTimeMillis();
        String sql = "update t_employee set name=\"" + updateName + "\" where id=" + id;
        assertEquals(1, mapper.execUpdateSql(sql));
    }

    @Test
    public void execSelectSql() {
        int id = 13;
        String name = "exec_select_sql" + System.currentTimeMillis();
        Employee employee = new Employee(id, name);
        ensureEntityExist(employee);
        mapper.updateSelective(employee);
        String sql = "select * from t_employee where name=\"" + name + "\"";
        List<Employee> employees = mapper.execSelectSql(sql);
        System.out.println(employees);
        assertNotNull(employees);
        assertTrue(employees.size() >= 1);
    }

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