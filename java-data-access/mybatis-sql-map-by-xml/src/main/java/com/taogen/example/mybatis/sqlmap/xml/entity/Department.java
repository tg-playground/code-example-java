package com.taogen.example.mybatis.sqlmap.xml.entity;

import java.util.List;

/**
 * @author Taogen
 */
public class Department extends BaseEntity {

    private String name;

    private List<Employee> employees;

    public Department(int id) {
        this.id = id;
    }

    public Department() {
    }

    public Department(String name) {
        this.name = name;
    }

    public Department(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        return "Department{" +
                "name='" + name + '\'' +
                ", employees=" + employees +
                ", id=" + id +
                ", deleteFlag=" + deleteFlag +
                ", createTime=" + createTime +
                ", modifyTime=" + modifyTime +
                '}';
    }
}