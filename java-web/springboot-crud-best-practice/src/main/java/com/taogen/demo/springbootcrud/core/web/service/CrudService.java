package com.taogen.demo.springbootcrud.core.web.service;


import com.taogen.demo.springbootcrud.core.web.vo.QueryPage;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author Taogen
 */
public interface CrudService<T> extends BaseService {
    int save(T entity);

    int saveAll(Collection<T> entities, Boolean testException);

    int saveOrUpdate(T entity);

    int deleteById(Serializable id);

    int deleteLogically(Serializable id);

    int deleteAllByIds(Collection<Serializable> ids);

    int deleteAllLogically(Collection<T> entities);

    int deleteAllByMap(Map<String, Object> conditions);

    int update(T entity);

    int updateAllByIds(Collection<T> entities);

    T getById(Serializable id);

    Long count();

    List<T> findPage(QueryPage<T> queryPage);

    List<T> findAllByFields(T entity);

    List<T> findAllByMap(Map<String, Object> conditions);

}
