package com.taogen.example.bestpractice.statements;

import com.taogen.example.bestpractice.common.entity.Department;
import com.taogen.example.bestpractice.common.entity.Employee;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

public class NullCheckingTest {

    @Test
    public void getValueOrDefault() {
        String s1 = "hello";
        String defaultStr = "default";
        assertEquals(s1, NullChecking.getValueOrDefault(s1, defaultStr));

        assertEquals(defaultStr, NullChecking.getValueOrDefault(null, defaultStr));
    }

    @Test
    public void getSubObjectFieldOrDefault() {
        String deptName = "dev";
        String defaultDeptName = "default";
        Department department = new Department(1, deptName);
        Employee employee = new Employee(101, "Tom", 18);
        employee.setDepartment(department);
        assertEquals(deptName, NullChecking.getSubObjectFieldOrDefault(employee, defaultDeptName));

        employee.setDepartment(null);
        assertEquals(defaultDeptName, NullChecking.getSubObjectFieldOrDefault(employee, defaultDeptName));
    }

    @Test
    public void getListOrEmpty() {
        List<Employee> employeeList = NullChecking.getListOrEmpty();
        assertNotNull(employeeList);
        assertEquals(0, employeeList.size());
    }

    @Test
    public void getOptional() {
        Optional optional = NullChecking.getOptional();
        if (optional.isPresent()) {
            assertNotNull(optional.get());
            System.out.println(optional.get());
        } else {
            System.out.println("entity is null!");
        }
    }
}