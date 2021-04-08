package com.taogen.example.es.springboot.highlevelrestclient.entity;

import lombok.Data;

/**
 * @author Taogen
 */
@Data
public class QueryPage<T> {

    private Integer pageNo;

    private Integer pageSize;

    /**
     * Format: fieldName ASC/DESC
     * fieldName: original ES fieldName
     */
    private String orderBy;

    private T entity;
}
