package com.taogen.demo.springbootcrud.core.controller;

import com.taogen.demo.springbootcrud.core.dto.Id;
import com.taogen.demo.springbootcrud.core.entity.BaseEntity;
import com.taogen.demo.springbootcrud.core.service.CrudService;
import com.taogen.demo.springbootcrud.core.vo.GenericResponseModel;
import com.taogen.demo.springbootcrud.core.vo.Page;
import com.taogen.demo.springbootcrud.core.vo.ResponseModel;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.List;

/**
 * @author Taogen
 */
public class AbstractController<S extends CrudService<T>, T extends BaseEntity>
        implements BaseController<T> {

    protected S service;

    public S getService() {
        return service;
    }

    public void setService(S service) {
        this.service = service;
    }

    @Override
    public GenericResponseModel<List<T>> findList(HttpServletRequest request, Page<T> page) {
        String requestId = request.getHeader("X-Request-Id");
        GenericResponseModel result = new GenericResponseModel(requestId);
        result.setResponseBody(service.findPage(page.getEntity(), page));
        return result;
    }

    @Override
    public GenericResponseModel<T> get(HttpServletRequest request, Serializable id) {
        String requestId = request.getHeader("X-Request-Id");
        GenericResponseModel result = new GenericResponseModel(requestId);
        result.setResponseBody(service.getById(id));
        return result;
    }

    @Override
    public ResponseModel delete(HttpServletRequest request, Serializable id) {
        String requestId = request.getHeader("X-Request-Id");
        GenericResponseModel result = new GenericResponseModel(requestId);
        result.setResponseBody(service.deleteById(id));
        return result;
    }

    @Override
    public ResponseModel deleteAll(HttpServletRequest request, List<Serializable> ids) {
        String requestId = request.getHeader("X-Request-Id");
        GenericResponseModel result = new GenericResponseModel(requestId);
        result.setResponseBody(service.deleteAllByIds(ids));
        return result;
    }

    @Override
    public ResponseModel save(HttpServletRequest request, T entity) {
        String requestId = request.getHeader("X-Request-Id");
        GenericResponseModel result = new GenericResponseModel(requestId);
        service.save(entity);
        result.setResponseBody(new Id(entity.getId()));
        return result;
    }

    @Override
    public ResponseModel update(HttpServletRequest request, T entity) {
        String requestId = request.getHeader("X-Request-Id");
        GenericResponseModel result = new GenericResponseModel(requestId);
        result.setResponseBody(service.update(entity));
        return result;
    }
}
