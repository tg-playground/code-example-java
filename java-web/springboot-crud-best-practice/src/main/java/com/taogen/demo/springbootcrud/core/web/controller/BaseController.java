package com.taogen.demo.springbootcrud.core.web.controller;

import com.taogen.demo.springbootcrud.core.persistence.entity.BaseEntity;
import com.taogen.demo.springbootcrud.core.web.vo.QueryPage;
import com.taogen.demo.springbootcrud.core.web.model.ResponseModel;

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

    ResponseModel findList(HttpServletRequest request, QueryPage<T> queryPage);

    ResponseModel deleteAll(HttpServletRequest request, List<Serializable> ids);
}
