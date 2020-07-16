package com.taogen.springmvcdatahandle.entity;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class User
{
    @NotNull
    @Min(1)
    private Integer id;

    @NotNull
    @Size(min=2, max=30)
    private String name;

    @NotNull
    @Range(min=1, max=150)
    private Integer age;

    @NotNull
    private String address;

    public User() {}
    public User(Integer id, String name, Integer age, String address)
    {
        this.id = id;
        this.name = name;
        this.age = age;
        this.address = address;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                '}';
    }
}
