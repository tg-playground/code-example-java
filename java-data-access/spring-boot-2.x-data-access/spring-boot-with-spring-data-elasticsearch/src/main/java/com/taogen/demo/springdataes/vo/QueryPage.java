package com.taogen.demo.springdataes.vo;

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
