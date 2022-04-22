package com.taogen.demo.vo;

import lombok.Data;

/**
 * @author Taogen
 */
@Data
public class Page {
    private Integer pageNo;
    private Integer pageSize;
    private String orderBy;

    public Page(Integer pageNo, Integer pageSize) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }
}
