package com.taogen.demo.springbootcrud.core.web.controller;

import com.taogen.demo.springbootcrud.core.persistence.entity.BaseEntity;
import com.taogen.demo.springbootcrud.core.web.dto.DataPage;
import com.taogen.demo.springbootcrud.core.web.dto.Id;
import com.taogen.demo.springbootcrud.core.web.service.CrudService;
import com.taogen.demo.springbootcrud.core.web.vo.QueryPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Taogen
 */
public class AbstractRestControllerNew<S extends CrudService<T>, T extends BaseEntity>
        implements BaseRestControllerNew<T>{

    protected final Logger logger = LogManager.getLogger(getClass());

    @Autowired
    protected S service;


    @Override
    public ResponseEntity<Id> save(HttpServletRequest request, T entity) {
        service.save(entity);
        return new ResponseEntity(new Id(entity.getId()), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity delete(HttpServletRequest request, Serializable id) {
        service.deleteById(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity update(HttpServletRequest request, Serializable id, T entity) {
        entity.setId(Integer.parseInt(String.valueOf(id)));
        service.update(entity);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<T> get(HttpServletRequest request, Serializable id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @Override
    public ResponseEntity<DataPage<List<T>>> findPage(HttpServletRequest request, QueryPage<T> queryPage) {
        DataPage<List<T>> dataPage = new DataPage<>();
        dataPage.setData(service.findPage(queryPage));
        dataPage.setTotal(service.count());
        return ResponseEntity.ok(dataPage);
    }

    @Override
    public ResponseEntity<List<T>> findAll(HttpServletRequest request, T entity) {
        return ResponseEntity.ok(service.findAllByFields(entity));
    }

    @Override
    public ResponseEntity deleteAll(HttpServletRequest request, String value) {
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
        service.deleteAllByIds(ids);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
