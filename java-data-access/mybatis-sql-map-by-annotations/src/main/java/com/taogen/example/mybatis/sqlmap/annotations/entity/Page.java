package com.taogen.example.mybatis.sqlmap.annotations.entity;

/**
 * @author Taogen
 */
public class Page {
    private final String dbName = "mysql";
    private int pageNo;
    private int pageSize;
    private long count;
    private String orderBy;
    private int start;

    public Page(int pageNo, int pageSize) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.start = (pageNo - 1) * pageSize;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }
}
