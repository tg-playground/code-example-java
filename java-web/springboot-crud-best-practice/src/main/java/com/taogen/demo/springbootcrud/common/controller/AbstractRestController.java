package com.taogen.demo.springbootcrud.common.controller;

import com.taogen.demo.springbootcrud.common.entity.BaseEntity;
import com.taogen.demo.springbootcrud.common.service.CrudService;
import com.taogen.demo.springbootcrud.common.vo.GenericResponseModel;
import com.taogen.demo.springbootcrud.common.vo.Page;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Taogen
 */
public class AbstractRestController<S extends CrudService<T>, T extends BaseEntity>
        implements BaseRestController<T> {

    protected Logger logger;
    protected Class<T> type;
    protected S service;

    @Override
    public GenericResponseModel save(HttpServletRequest request, T entity) {
        GenericResponseModel result = new GenericResponseModel("");
        result.setResponseBody(service.save(entity));
        return result;
    }

    @Override
    public GenericResponseModel delete(HttpServletRequest request, Serializable id) {
        String requestId = request.getHeader("X-Request-Id");
        GenericResponseModel result = new GenericResponseModel(requestId);
        result.setResponseBody(service.deleteById(id));
        return result;
    }

    @Override
    public GenericResponseModel update(HttpServletRequest request, Serializable id, T entity) {
        GenericResponseModel result = new GenericResponseModel("");
        result.setResponseBody(service.update(entity));
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
    public GenericResponseModel<List<T>> findList(HttpServletRequest request, Page<T> page) {
        logger.debug("Param page is {}", page);
        GenericResponseModel result = new GenericResponseModel("");
        List<T> list = service.findPage(page.getEntity(), page);
        result.setResponseBody(list);
        logger.debug("list: {}", list.size());
        return result;
    }

    @Override
    public GenericResponseModel deleteAll(HttpServletRequest request, String value) {
        String requestId = request.getHeader("X-Request-Id");
        GenericResponseModel result = new GenericResponseModel(requestId);
        List<Serializable> ids = null;
        JSONObject jsonObject = new JSONObject(value);
        try {
            ids = jsonObject.getJSONArray("ids").toList().stream().map(id -> (Serializable) id).collect(Collectors.toList());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        result.setResponseBody(service.deleteAllByIds(ids));
        return result;
    }
}
