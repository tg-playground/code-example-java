package com.taogen.demo.springbootcrud.core.controller;

import com.taogen.demo.springbootcrud.core.entity.BaseEntity;
import com.taogen.demo.springbootcrud.core.vo.Page;
import com.taogen.demo.springbootcrud.core.vo.ResponseModel;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.List;

/**
 * @author Taogen
 */
public interface BaseController<T extends BaseEntity> {
    ResponseModel save(HttpServletRequest request, T entity);

    ResponseModel delete(HttpServletRequest request, Serializable id);

    ResponseModel update(HttpServletRequest request, T entity);

    ResponseModel get(HttpServletRequest request, Serializable id);

    ResponseModel findList(HttpServletRequest request, Page<T> page);

    ResponseModel deleteAll(HttpServletRequest request, List<Serializable> ids);
}
