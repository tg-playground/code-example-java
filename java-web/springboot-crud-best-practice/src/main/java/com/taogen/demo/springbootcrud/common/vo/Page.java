package com.taogen.demo.springbootcrud.common.vo;

/**
 * @author Taogen
 */
public class Page<T> {
    private final String dbName = "mysql";

    private Integer pageNo;
    private Integer pageSize;
    private Long count;
    private String orderBy;
    private Integer start;
    private T entity;

    public Page(int pageNo, int pageSize) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.start = (pageNo - 1) * pageSize;
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

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public T getEntity() {
        return entity;
    }

    public void setEntity(T entity) {
        this.entity = entity;
    }

    @Override
    public String toString() {
        return "Page{" +
                "pageNo=" + pageNo +
                ", pageSize=" + pageSize +
                ", count=" + count +
                ", orderBy='" + orderBy + '\'' +
                ", start=" + start +
                ", entity=" + entity +
                '}';
    }
}
