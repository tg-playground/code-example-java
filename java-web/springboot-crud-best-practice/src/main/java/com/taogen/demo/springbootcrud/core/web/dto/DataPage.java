package com.taogen.demo.springbootcrud.core.web.dto;

/**
 * @author Taogen
 */
public class DataPage<T> {
    private Long total;
    private T data;

    public DataPage() {
    }

    public DataPage(Long total, T data) {
        this.total = total;
        this.data = data;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
