package com.taogen.demo.springbootcrud.core.web.controller;

import com.taogen.demo.springbootcrud.core.web.dto.DataPage;
import com.taogen.demo.springbootcrud.core.web.dto.Id;
import com.taogen.demo.springbootcrud.core.web.model.GenericResponseModel;
import com.taogen.demo.springbootcrud.core.web.model.ResponseModel;
import com.taogen.demo.springbootcrud.core.web.vo.QueryPage;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.List;

/**
 * @author Taogen
 */
public interface BaseRestControllerNew<T> {
    ResponseEntity<Id> save(HttpServletRequest request, T entity);

    ResponseEntity delete(HttpServletRequest request, Serializable id);

    ResponseEntity update(HttpServletRequest request, Serializable id, T entity);

    ResponseEntity<T> get(HttpServletRequest request, Serializable id);

    ResponseEntity<DataPage<List<T>>> findPage(HttpServletRequest request, QueryPage<T> queryPage);

    ResponseEntity<List<T>> findAll(HttpServletRequest request, T entity);

    ResponseEntity deleteAll(HttpServletRequest request, String value);
}
