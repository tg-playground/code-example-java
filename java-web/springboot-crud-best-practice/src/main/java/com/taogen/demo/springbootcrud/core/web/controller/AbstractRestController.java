package com.taogen.demo.springbootcrud.core.web.controller;

import com.taogen.demo.springbootcrud.core.persistence.entity.BaseEntity;
import com.taogen.demo.springbootcrud.core.web.dto.DataPage;
import com.taogen.demo.springbootcrud.core.web.dto.GenericResponseModel;
import com.taogen.demo.springbootcrud.core.web.dto.Id;
import com.taogen.demo.springbootcrud.core.web.service.CrudService;
import com.taogen.demo.springbootcrud.core.web.vo.QueryPage;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
        service.save(entity);
        result.setResponseBody(new Id(entity.getId()));
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
        Integer intId = Integer.parseInt(String.valueOf(id));
        entity.setId(intId);
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
    public GenericResponseModel findList(HttpServletRequest request, QueryPage<T> queryPage) {
        logger.debug("Param page is {}", queryPage);
        GenericResponseModel result = new GenericResponseModel("");
        List<T> list = service.findPage(queryPage.getEntity(), queryPage);
        logger.debug("list: {}", list.size());
        Long total = service.count();
        DataPage<List<T>> dataPage = new DataPage(total, list);
        result.setResponseBody(dataPage);
        return result;
    }

    @Override
    public GenericResponseModel deleteAll(HttpServletRequest request, String value) {
        String requestId = request.getHeader("X-Request-Id");
        GenericResponseModel result = new GenericResponseModel(requestId);
        List<Serializable> ids = new ArrayList<>();
        JSONObject jsonObject = new JSONObject(value);
        try {
            JSONArray jsonArray = jsonObject.getJSONArray("ids");
            for (int i = 0; i < jsonArray.length(); i++) {
                ids.add((Serializable) jsonArray.get(i));
            }
            // running test has error: NoSuchMethodError: org.json.JSONArray.toList()
//            ids = jsonObject.getJSONArray("ids").toList().stream().map(id -> (Serializable) id).collect(Collectors.toList());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        result.setResponseBody(service.deleteAllByIds(ids));
        return result;
    }
}
