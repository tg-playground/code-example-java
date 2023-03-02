package com.taogen.example.mybatis.sqlmap.xml.service.impl;

import com.taogen.example.mybatis.sqlmap.xml.entity.Employee;
import com.taogen.example.mybatis.sqlmap.xml.entity.Page;
import com.taogen.example.mybatis.sqlmap.xml.service.EmployeeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeServiceImplTest {

    private static final Logger logger = LogManager.getLogger();
    private EmployeeService employeeService = new EmployeeServiceImpl();

    @Test
    public void save() {
        assertEquals(1, employeeService.save(new Employee("development")));
    }

    @Test
    public void saveAll() {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("test"));
        employees.add(new Employee("test2"));
        assertEquals(2, employeeService.saveAll(employees, false));
    }

    @Test
    public void saveAllTestException() {
        String name = "test_exception" + System.currentTimeMillis();
        Map<String, Object> conditions = new HashMap<>();
        conditions.put("name", name);
        employeeService.deleteAllByMap(conditions);
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(name));
        employees.add(new Employee(name));
        assertEquals(0, employeeService.saveAll(employees, true));
        assertEquals(0, employeeService.findAllByFields(new Employee(name)).size());

    }

    @Test
    public void saveOrUpdate() {
        int id = 104;
        Employee employee = new Employee(id, "saveOrUpdate");
        employeeService.deleteById(employee);
        if (employeeService.getById(employee) == null) {
            assertEquals(1, employeeService.saveOrUpdate(employee));
        }
        String name = "saveOrUpdate" + System.currentTimeMillis();
        employee.setName(name);
        assertEquals(1, employeeService.saveOrUpdate(employee));
        employee = employeeService.getById(employee);
        assertNotNull(employee);
        assertEquals(name, employee.getName());
    }

    @Test
    public void deleteById() {
        int deleteId = 105;
        Employee employee = new Employee(deleteId);
        ensureEntityExist(employee);
        assertEquals(1, employeeService.deleteById(employee));
    }

    @Test
    public void deleteAllByIds() {
        List<Integer> deleteIds = Arrays.asList(116, 117);
        List<Employee> employees = ensureEntityListExist(deleteIds);
        assertEquals(deleteIds.size(), employeeService.deleteAllByIds(employees));
    }

    @Test
    public void deleteLogically() {
        int deleteId = 202;
        Employee employee = new Employee(deleteId);
        ensureEntityExist(employee);
        String name = "deleteLogically" + System.currentTimeMillis();
        employee.setName(name);
        employeeService.update(employee);
        assertEquals(1, employeeService.deleteLogically(employee));
        employee = employeeService.getById(employee);
        logger.debug("employee is {}", employee);
        assertEquals(name, employee.getName());
        assertEquals(true, employee.getDeleteFlag());
    }

    @Test
    public void deleteAllLogically() {
        List<Integer> deleteIds = Arrays.asList(12, 13);
        List<Employee> employees = ensureEntityListExist(deleteIds);
        String name = "deleteAllLogically" + System.currentTimeMillis();
        for (Employee employee : employees) {
            employee = employeeService.getById(employee);
            employee.setName(name);
            employeeService.update(employee);
        }
        employeeService.deleteAllLogically(employees);
        for (Employee employee : employees) {
            employee = employeeService.getById(employee);
            assertEquals(name, employee.getName());
            assertEquals(true, employee.getDeleteFlag());
        }
    }

    @Test
    public void deleteAllByMap() {
        List<Integer> deleteIds = Arrays.asList(106, 107);
        List<Employee> employees = ensureEntityListExist(deleteIds);
        String name = "delete_all_by_map" + System.currentTimeMillis();
        for (Employee employee : employees) {
            employee = employeeService.getById(employee);
            employee.setName(name);
            employeeService.update(employee);
        }
        Map<String, Object> conditions = new HashMap<>();
        conditions.put("name", name);
        assertEquals(deleteIds.size(), employeeService.deleteAllByMap(conditions));
    }

    @Test
    public void update() {
        int updateId = 108;
        Employee employee = new Employee(updateId);
        ensureEntityExist(employee);
        employee = employeeService.getById(employee);
        String name = System.currentTimeMillis() + "";
        employee.setName(name);
        employeeService.update(employee);
        employee = employeeService.getById(employee);
        assertEquals(name, employee.getName());
    }

    @Test
    public void updateAllByIds() {
        List<Integer> updateIds = Arrays.asList(109, 110);
        List<Employee> employees = ensureEntityListExist(updateIds);

        String name = System.currentTimeMillis() + "";
        for (Employee employee : employees) {
            employee.setName(name);
        }
        employeeService.updateAllByIds(employees);
        for (Integer id : updateIds) {
            Employee employee = employeeService.getById(new Employee(id));
            assertEquals(name, employee.getName());
        }
    }

    @Test
    public void getById() {
        int getId = 111;
        Employee employee = new Employee(getId);
        ensureEntityExist(employee);
        assertNotNull(employeeService.getById(employee));
    }

    @Test
    public void count() {
        int id = 112;
        ensureEntityExist(new Employee(id));
        assertTrue(employeeService.count() > 0);
    }

    @Test
    public void findPage() {
        employeeService.save(new Employee("test_find_page"));
        employeeService.save(new Employee("test_find_page2"));
        int pageNo = 1;
        long pageSize = 10;
        Page page = new Page(pageNo, (int) pageSize);
        List<Employee> employees = employeeService.findPage(page, new Employee("test"));
        logger.debug("employees are {}", employees);
        assertNotNull(employees);
    }

    @Test
    public void findAllByFields() {
        int id = 113;
        Employee employee = new Employee(id);
        ensureEntityExist(employee);
        employee = employeeService.getById(employee);
        String name = "find_all_by_fields" + System.currentTimeMillis();
        employee.setName(name);
        employeeService.update(employee);
        assertNotNull(employeeService.findAllByFields(new Employee(name)));
    }

    @Test
    public void findAllByMap() {
        int id = 114;
        Employee employee = new Employee(id);
        ensureEntityExist(employee);
        employee = employeeService.getById(employee);
        String name = "find_all_by_map" + System.currentTimeMillis();
        employee.setName(name);
        employeeService.update(employee);
        Map<String, Object> params = new HashMap<>();
        params.put("name", name);
        assertNotNull(employeeService.findAllByMap(params));
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
        if (employeeService.getById(employee) == null) {
            employeeService.save(new Employee(employee.getId(), "ensureEntityExist" + System.currentTimeMillis()));
        }
    }
}