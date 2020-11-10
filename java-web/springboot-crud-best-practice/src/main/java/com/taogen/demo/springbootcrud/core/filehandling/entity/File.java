package com.taogen.demo.springbootcrud.core.filehandling.entity;

import com.taogen.demo.springbootcrud.core.persistence.entity.BaseEntity;

/**
 * @author Taogen
 */
public class File extends BaseEntity {

    private String name;

    private String uri;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri == null ? null : uri.trim();
    }

}