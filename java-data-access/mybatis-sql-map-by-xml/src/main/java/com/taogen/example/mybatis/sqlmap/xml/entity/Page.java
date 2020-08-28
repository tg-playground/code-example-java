package com.taogen.example.mybatis.sqlmap.xml.entity;

/**
 * @author Taogen
 */
public class Page {
    private int pageNo;
    private int pageSize;
    private long count;

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}
