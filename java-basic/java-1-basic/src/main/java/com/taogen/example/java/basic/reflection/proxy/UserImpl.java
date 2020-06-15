package com.taogen.example.java.basic.reflection.proxy;

/**
 * @author Taogen
 */
public class UserImpl implements User{
    private String name;

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
