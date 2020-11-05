package com.taogen.demo.springbootcrud.module.employee.entity;

import com.taogen.demo.springbootcrud.common.entity.BaseEntity;
import com.taogen.demo.springbootcrud.module.department.entity.Department;

/**
 * @author Taogen
 */
public class Employee extends BaseEntity {

    private String name;

    private String nickname;

    private Integer age;

    private Department department;

    public Employee(int id) {
        this.id = id;
    }

    public Employee() {
    }

    public Employee(String name) {
        this.name = name;
    }

    public Employee(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                ", age=" + age +
                ", department=" + department +
                ", id=" + id +
                ", deleteFlag=" + deleteFlag +
                ", createTime=" + createTime +
                ", modifyTime=" + modifyTime +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}