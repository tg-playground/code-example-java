package com.taogen.demo.springbootcrud.common.controller;

import com.taogen.demo.springbootcrud.common.entity.BaseEntity;
import com.taogen.demo.springbootcrud.common.service.CrudService;
import com.taogen.demo.springbootcrud.common.vo.GenericResponseModel;
import com.taogen.demo.springbootcrud.common.vo.Page;
import com.taogen.demo.springbootcrud.common.vo.ResponseModel;

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
    public GenericResponseModel<List<T>> findPage(HttpServletRequest request, T entity, Page page) {
        String requestId = request.getHeader("X-Request-Id");
        GenericResponseModel result = new GenericResponseModel(requestId);
        result.setResponseBody(service.findPage(entity, page));
        return result;
    }

    @Override
    public GenericResponseModel<List<T>> findAll(HttpServletRequest request, T entity) {
        String requestId = request.getHeader("X-Request-Id");
        GenericResponseModel result = new GenericResponseModel(requestId);
        result.setResponseBody(service.findAllByFields(entity));
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
        result.setResponseBody(service.save(entity));
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
