package com.taogen.example.mybatis.sqlmap.xml.service;

import com.taogen.example.mybatis.sqlmap.xml.entity.BaseEntity;
import com.taogen.example.mybatis.sqlmap.xml.entity.Page;
import com.taogen.example.mybatis.sqlmap.xml.mapper.CrudMapper;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author Taogen
 */
public interface CrudService<M extends CrudMapper, T extends BaseEntity> extends BaseService {
    int save(T entity);

    int saveAll(Collection<T> entities);

    int deleteById(T entity);

    int deleteAllByIds(Collection<T> entities);

    int update(T entity);

    int updateAllByIds(Collection<T> entities);

    T getById(int id);

    T getById(T entity);

    List<T> findPage(Page page, T entity);

    List<T> findAllByFields(T entity);

    List<T> findAllByMap(Map<String, Object> parameters);

}
