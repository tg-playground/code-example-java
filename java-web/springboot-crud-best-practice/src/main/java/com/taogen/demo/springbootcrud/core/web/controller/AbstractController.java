package com.taogen.demo.springbootcrud.core.web.controller;

import com.taogen.demo.springbootcrud.core.web.dto.DataPage;
import com.taogen.demo.springbootcrud.core.web.dto.Id;
import com.taogen.demo.springbootcrud.core.persistence.entity.BaseEntity;
import com.taogen.demo.springbootcrud.core.web.service.CrudService;
import com.taogen.demo.springbootcrud.core.web.model.GenericResponseModel;
import com.taogen.demo.springbootcrud.core.web.vo.QueryPage;
import com.taogen.demo.springbootcrud.core.web.model.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.List;

/**
 * @author Taogen
 */
public class AbstractController<S extends CrudService<T>, T extends BaseEntity>
        implements BaseController<T> {

    @Autowired
    protected S service;

    @Override
    public GenericResponseModel findList(HttpServletRequest request, QueryPage<T> queryPage) {
        String requestId = request.getHeader("X-Request-Id");
        GenericResponseModel result = new GenericResponseModel(requestId);
        List<T> list = service.findPage(queryPage);
        Long total = service.count();
        DataPage<List<T>> dataPage = new DataPage(total, list);
        result.setResponseBody(dataPage);
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
