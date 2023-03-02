package com.taogen.example.mybatis.sqlmap.annotations.entity;

/**
 * @author Taogen
 */
public class Page {
    private final String dbName = "mysql";
    private Integer pageNo;
    private Integer pageSize;
    private Long count;
    private String orderBy;
    private Integer start;

    public Page(Integer pageNo, Integer pageSize) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.start = (pageNo - 1) * pageSize;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public Integer getStart() {
        return start;
    }
}
