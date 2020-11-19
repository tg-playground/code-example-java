package com.taogen.demo.springbootcrud.core.web.controller;

import com.taogen.demo.springbootcrud.core.persistence.entity.BaseEntity;
import com.taogen.demo.springbootcrud.core.web.dto.DataPage;
import com.taogen.demo.springbootcrud.core.web.dto.Id;
import com.taogen.demo.springbootcrud.core.web.model.GenericResponseModel;
import com.taogen.demo.springbootcrud.core.web.service.CrudService;
import com.taogen.demo.springbootcrud.core.web.vo.QueryPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Taogen
 */
public class AbstractRestController<S extends CrudService<T>, T extends BaseEntity>
        implements BaseRestController<T> {

    protected final Logger logger = LogManager.getLogger(getClass());

    @Autowired
    protected S service;

    /**
     * Save resources
     * - URL: prefix + {resource}
     * - Method: POST
     * - Content-Type: application/json
     * - Body: {entity data}
     *
     * @param request
     * @param entity
     * @return
     */
    @Override
    public GenericResponseModel save(HttpServletRequest request, T entity) {
        GenericResponseModel result = new GenericResponseModel("");
        service.save(entity);
        result.setResponseBody(new Id(entity.getId()));
        return result;
    }

    /**
     * Delete resources
     * - URL: prefix + {resources} + {id}
     * - Method: DELETE
     * - Content-Type: application/json or null
     * - Body: null
     *
     * @param request
     * @param id
     * @return
     */
    @Override
    public GenericResponseModel delete(HttpServletRequest request, Serializable id) {
        String requestId = request.getHeader("X-Request-Id");
        GenericResponseModel result = new GenericResponseModel(requestId);
        result.setResponseBody(service.deleteById(id));
        return result;
    }

    /**
     * Update resources
     * - URL: prefix + {resources} + {id}
     * - Method: PUT
     * - Content-Type: application/json
     * - Body: {entity data}
     *
     * @param request
     * @param id
     * @param entity
     * @return
     */
    @Override
    public GenericResponseModel update(HttpServletRequest request, Serializable id, T entity) {
        Integer intId = Integer.parseInt(String.valueOf(id));
        entity.setId(intId);
        GenericResponseModel result = new GenericResponseModel("");
        result.setResponseBody(service.update(entity));
        return result;
    }

    /**
     * Get resources
     * - URL: prefix + {resources} + {id}
     * - method: GET
     * - Content-Type: null
     * - body: null
     *
     * @param request
     * @param id
     * @return
     */
    @Override
    public GenericResponseModel<T> get(HttpServletRequest request, Serializable id) {
        String requestId = request.getHeader("X-Request-Id");
        GenericResponseModel result = new GenericResponseModel(requestId);
        result.setResponseBody(service.getById(id));
        return result;
    }

    /**
     * Find resources page
     * - URL: prefix + {resource} + /pages
     * - Content-Type: application/json
     * - Method: POST
     * - Body: {page:{}, params:{}}
     *
     * @param request
     * @param queryPage
     * @return
     */
    @Override
    public GenericResponseModel findPage(HttpServletRequest request, @RequestBody QueryPage<T> queryPage) {
        logger.debug("Param page is {}", queryPage);
        GenericResponseModel result = new GenericResponseModel("");
        List<T> list = service.findPage(queryPage);
        logger.debug("list: {}", list.size());
        Long total = service.count();
        DataPage<List<T>> dataPage = new DataPage(total, list);
        result.setResponseBody(dataPage);
        return result;
    }

    /**
     * Search resources
     * - URL: prefix + {resource} + /searches
     * - Content-Type: application/json
     * - Method: POST
     * - Body: {params:{}}
     *
     * @param request
     * @param entity
     * @return
     */
    @Override
    public GenericResponseModel findAll(HttpServletRequest request, @RequestBody T entity) {
        logger.debug("Param page is {}", entity);
        GenericResponseModel result = new GenericResponseModel("");
        List<T> list = service.findAllByFields(entity);
        logger.debug("list: {}", list.size());
        Long total = service.count();
        DataPage<List<T>> dataPage = new DataPage(total, list);
        result.setResponseBody(dataPage);
        return result;
    }



    /**
     * Delete multiple resources
     * - URL: prefix + {resources}
     * - Method: DELETE
     * - Content-Type: application/json
     * - Body: {ids=id1,id2...}
     *
     * @param request
     * @param value
     * @return
     */
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
