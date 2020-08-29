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

    int saveAll(Collection<T> entities, Boolean testException);

    int saveOrUpdate(T entity);

    int deleteById(T entity);

    int deleteAllByIds(Collection<T> entities);

    int deleteAllByMap(Map<String, Object> conditions);

    int update(T entity);

    int updateAllByIds(Collection<T> entities);

    T getById(T entity);

    Long count();

    List<T> findPage(Page page, T entity);

    List<T> findAllByFields(T entity);

    List<T> findAllByMap(Map<String, Object> conditions);

}
