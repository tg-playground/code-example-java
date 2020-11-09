package com.taogen.demo.springbootcrud.common.controller;

import com.taogen.demo.springbootcrud.common.entity.BaseEntity;
import com.taogen.demo.springbootcrud.common.vo.GenericResponseModel;
import com.taogen.demo.springbootcrud.common.vo.Page;
import com.taogen.demo.springbootcrud.common.vo.ResponseModel;
import org.springframework.boot.configurationprocessor.json.JSONException;

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

    GenericResponseModel<List<T>> findList(HttpServletRequest request, Page<T> page);

    GenericResponseModel deleteAll(HttpServletRequest request, String value);
}
