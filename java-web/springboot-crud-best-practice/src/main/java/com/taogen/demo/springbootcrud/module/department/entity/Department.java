package com.taogen.demo.springbootcrud.module.department.entity;

import com.taogen.demo.springbootcrud.common.entity.BaseEntity;
import com.taogen.demo.springbootcrud.module.employee.entity.Employee;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author Taogen
 */
@Entity
public class Department extends BaseEntity {

    @NotNull(message = "Name cannot be null")
    private String name;

    @OneToMany
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