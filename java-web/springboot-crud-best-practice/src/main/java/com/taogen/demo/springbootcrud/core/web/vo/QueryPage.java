package com.taogen.demo.springbootcrud.core.web.vo;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author Taogen
 */
public class QueryPage<T> {
    private final String dbName = "mysql";

    @NotNull
    @Min(1)
    private Integer pageNo;

    @NotNull
    @Min(1)
    private Integer pageSize;

    private String orderBy;

    private T entity;

    public QueryPage() {
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public T getEntity() {
        return entity;
    }

    public void setEntity(T entity) {
        this.entity = entity;
    }

    @Override
    public String toString() {
        return "QueryPage{" +
                "pageNo=" + pageNo +
                ", pageSize=" + pageSize +
                ", orderBy='" + orderBy + '\'' +
                ", entity=" + entity +
                '}';
    }
}
