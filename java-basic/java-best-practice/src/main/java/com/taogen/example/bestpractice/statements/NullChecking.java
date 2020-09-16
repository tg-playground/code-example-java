package com.taogen.example.bestpractice.statements;

import com.taogen.example.bestpractice.common.dao.EmployeeDao;
import com.taogen.example.bestpractice.common.entity.Employee;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @author Taogen
 */
public class NullChecking {

    /**
     * Using Optional instead of if null-check statement
     *
     * @param str
     * @param defaultStr
     * @return
     */
    public static String getValueOrDefault(String str, String defaultStr) {
        Optional<String> stringOptional = Optional.ofNullable(str);
        return stringOptional.orElse(defaultStr);
    }


    /**
     * Using Optional instead of multiple nested if null-check statements
     *
     * @param employee
     * @param defaultStr
     * @return
     */
    public static String getSubObjectFieldOrDefault(Employee employee, String defaultStr) {
        Optional<Employee> optional = Optional.ofNullable(employee);
        return optional.map(emp -> emp.getDepartment())
                .map(dept -> dept.getName())
                .orElse(defaultStr);
    }

    /**
     * Return Empty Collection Object instead of Return Null
     *
     * @return
     */
    public static List<Employee> getListOrEmpty() {
        EmployeeDao employeeDao = new EmployeeDao();
        List<Employee> employeeList = employeeDao.getEmployeeList();
        if (employeeList != null) {
            return employeeList;
        }
        return Collections.emptyList();
    }

    /**
     * Return Optional<Entity> instead of return entity object
     *
     * @return
     */
    public static Optional<Employee> getOptional() {
        EmployeeDao employeeDao = new EmployeeDao();
        Integer empId = 1;
        Employee employee = employeeDao.getEmployee(empId);
        return Optional.ofNullable(employee);
    }

}
