package com.taogen.demo.springbootcrud.core.web.controller;

import com.taogen.demo.springbootcrud.core.persistence.entity.BaseEntity;
import com.taogen.demo.springbootcrud.core.web.model.GenericResponseModel;
import com.taogen.demo.springbootcrud.core.web.vo.QueryPage;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.List;

/**
 * @author Taogen
 */
public interface BaseRestController<T extends BaseEntity> {

    GenericResponseModel save(HttpServletRequest request, T entity);

    GenericResponseModel delete(HttpServletRequest request, Serializable id);

    GenericResponseModel update(HttpServletRequest request, Serializable id, T entity);

    GenericResponseModel<T> get(HttpServletRequest request, Serializable id);

    GenericResponseModel<List<T>> findList(HttpServletRequest request, QueryPage<T> queryPage);

    GenericResponseModel deleteAll(HttpServletRequest request, String value);
}
