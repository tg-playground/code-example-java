package com.taogen.example.mybatis.xml.entity;

public class User {
    private Integer id;

    private String name;

    private String firstName;

    private String secondName;

    private Integer age;

    public User() {
    }

    public User(String name, String firstName, String secondName, Integer age) {
        this.name = name;
        this.firstName = firstName;
        this.secondName = secondName;
        this.age = age;
    }

    public User(Integer id, String name, String firstName, String secondName, Integer age) {
        this.id = id;
        this.name = name;
        this.firstName = firstName;
        this.secondName = secondName;
        this.age = age;
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
        this.name = name == null ? null : name.trim();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName == null ? null : firstName.trim();
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName == null ? null : secondName.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", age=" + age +
                '}';
    }
}