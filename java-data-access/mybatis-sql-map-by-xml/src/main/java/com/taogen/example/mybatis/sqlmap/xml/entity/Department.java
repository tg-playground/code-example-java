package com.taogen.example.mybatis.sqlmap.xml.entity;

/**
 * @author Taogen
 */
public class Department extends BaseEntity {

    private String name;

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

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name=" + name +
                ", deleteFlag=" + deleteFlag +
                ", createTime=" + createTime +
                ", modifyTime=" + modifyTime +
                '}';
    }
}