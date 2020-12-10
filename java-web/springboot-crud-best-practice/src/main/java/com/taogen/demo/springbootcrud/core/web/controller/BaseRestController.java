package com.taogen.demo.springbootcrud.core.web.controller;

import com.taogen.demo.springbootcrud.core.persistence.entity.BaseEntity;
import com.taogen.demo.springbootcrud.core.web.dto.DataPage;
import com.taogen.demo.springbootcrud.core.web.dto.Id;
import com.taogen.demo.springbootcrud.core.web.model.GenericResponseModel;
import com.taogen.demo.springbootcrud.core.web.model.ResponseModel;
import com.taogen.demo.springbootcrud.core.web.vo.QueryPage;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.List;

/**
 * @author Taogen
 */
public interface BaseRestController<T extends BaseEntity> {

    GenericResponseModel<Id> save(HttpServletRequest request, T entity);

    ResponseModel delete(HttpServletRequest request, Serializable id);

    ResponseModel update(HttpServletRequest request, Serializable id, T entity);

    GenericResponseModel<T> get(HttpServletRequest request, Serializable id);

    GenericResponseModel<DataPage<List<T>>> findPage(HttpServletRequest request, QueryPage<T> queryPage);

    GenericResponseModel<List<T>> findAll(HttpServletRequest request, T entity);

    ResponseModel deleteAll(HttpServletRequest request, String value);
}
