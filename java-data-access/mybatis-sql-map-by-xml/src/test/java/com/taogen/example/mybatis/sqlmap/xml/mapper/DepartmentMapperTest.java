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

public class DepartmentMapperTest {

    private DepartmentMapper mapper;
    private EmployeeMapper employeeMapper;
    private SqlSessionFactoryService sqlSessionFactoryService = new SqlSessionFactoryServiceImpl();
    private SqlSession sqlSession;

    @Before
    public void before() {
        sqlSession = sqlSessionFactoryService.getSqlSessionFactory().openSession(true);
        mapper = sqlSession.getMapper(DepartmentMapper.class);
        employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
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
        int deleteId = 202;
        ensureEntityExist(new Department(deleteId));
        assertEquals(1, mapper.deleteById(new Department(deleteId)));
    }

    @Test
    public void deleteAll() {
        List<Integer> deleteIds = Arrays.asList(203, 204);
        List<Department> departments = ensureEntityListExist(deleteIds);
        assertEquals(deleteIds.size(), mapper.deleteAll(departments));
    }

    @Test
    public void deleteLogically() {
        int deleteId = 202;
        Department department = new Department(deleteId);
        ensureEntityExist(department);
        String name = "deleteLogically" + System.currentTimeMillis();
        department.setName(name);
        mapper.updateSelective(department);
        assertEquals(1, mapper.deleteLogically(department));
        department = mapper.getById(department);
        assertEquals(name, department.getName());
        assertEquals(true, department.getDeleteFlag());
    }

    @Test
    public void deleteAllLogically() {
        List<Integer> deleteIds = Arrays.asList(203, 204);
        List<Department> departments = ensureEntityListExist(deleteIds);
        String name = "deleteAllLogically" + System.currentTimeMillis();
        for (Department department : departments) {
            department = mapper.getById(department);
            department.setName(name);
            mapper.updateSelective(department);
        }
        mapper.deleteAllLogically(departments);
        for (Department department : departments) {
            department = mapper.getById(department);
            assertEquals(name, department.getName());
            assertEquals(true, department.getDeleteFlag());
        }
    }

    @Test
    public void deleteAllByField() {
        List<Integer> deleteIds = Arrays.asList(205, 206);
        mapper.deleteAll(deleteIds.stream().map(deleteId -> new Department(deleteId)).collect(Collectors.toList()));
        List<Department> departments = ensureEntityListExist(deleteIds);
        String name = "delete_all_by_field" + System.currentTimeMillis();
        for (Department department : departments) {
            department.setName(name);
            mapper.updateSelective(department);
        }
        assertTrue(mapper.deleteAllByFields(new Department(name)) >= deleteIds.size());
    }

    @Test
    public void deleteAllByMap() {
        List<Integer> deleteIds = Arrays.asList(207, 208);
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
        int updateId = 209;
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
        List<Integer> updateIds = Arrays.asList(210, 211);
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
        int id = 212;
        Department department = new Department(id);
        ensureEntityExist(department);

        String employeeName = "test_department_one_to_many" + System.currentTimeMillis();
        System.out.println("employeeName: " + employeeName);
        Employee employee = new Employee(1, employeeName);
        System.out.println("deptId: " + department.getId());
        employee.setDepartment(department);
        if (employeeMapper.getById(employee) == null) {
            employeeMapper.saveSelective(employee);
        } else {
            employeeMapper.updateSelective(employee);
        }

        department = mapper.getById(department);
        System.out.println("department: " + department);
        assertNotNull(department);
        assertNotNull(department.getEmployees());
        assertTrue(department.getEmployees()
                .stream()
                .map(emp -> emp.getName())
                .anyMatch(name -> name.equals(employeeName)));
    }

    @Test
    public void callById() {
        int id = 212;
        ensureEntityExist(new Department(id));
        Department department = mapper.callById(new Department(id));
        assertNotNull(department);
        System.out.println(department);
    }

    @Test
    public void count() {
        int id = 213;
        ensureEntityExist(new Department(id));
        assertTrue(mapper.count() >= 1);
    }

    @Test
    public void countByField() {
        String name = "count_by_field" + System.currentTimeMillis();
        mapper.saveSelective(new Department(name));
        assertTrue(mapper.countByFields(new Department(name)) >= 1);
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
    }

    @Test
    public void findAllByMap() {
        int id = 214;
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

    @Test
    public void execInsertSql() {
        String sql = "insert into t_department (name) values ('Tom'), ('John')";
        assertEquals(2, mapper.execInsertSql(sql));
    }

    @Test
    public void execDeleteSql() {
        int id = 11;
        ensureEntityExist(new Department(id, "exec_delete_sql"));
        String sql = "delete from t_department where id=" + id;
        assertEquals(1, mapper.execDeleteSql(sql));
    }

    @Test
    public void execUpdateSql() {
        int id = 12;
        ensureEntityExist(new Department(id, "exec_update_sql"));
        String updateName = "exec_update_sql" + System.currentTimeMillis();
        String sql = "update t_department set name=\"" + updateName + "\" where id=" + id;
        assertEquals(1, mapper.execUpdateSql(sql));
    }

    @Test
    public void execSelectSql() {
        int id = 13;
        String name = "exec_select_sql" + System.currentTimeMillis();
        Department department = new Department(id, name);
        ensureEntityExist(department);
        mapper.updateSelective(department);
        String sql = "select * from t_department where name=\"" + name + "\"";
        List<Department> departments = mapper.execSelectSql(sql);
        System.out.println(departments);
        assertNotNull(departments);
        assertTrue(departments.size() >= 1);
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
        if (mapper.getById(department) == null) {
            mapper.saveSelective(new Department(department.getId(), "ensureEntityExist" + System.currentTimeMillis()));
        }
    }
}