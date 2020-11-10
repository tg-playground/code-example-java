package com.taogen.demo.springbootcrud.module.user.entity;

import com.taogen.demo.springbootcrud.core.persistence.entity.BaseEntity;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;

@Entity
public class User extends BaseEntity {

    @NotEmpty(message = "{name.notempty}")
    private String name;

    private String password;

    private String nickname;

    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
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
}