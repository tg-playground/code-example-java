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
public abstract class AbstractCrudService<M extends CrudMapper<T>, T extends BaseEntity>
        extends AbstractBaseService implements CrudService<M, T> {
    @Override
    public int save(T entity) {
        return 0;
    }

    @Override
    public int saveAll(Collection<T> entities) {
        return 0;
    }

    @Override
    public int deleteById(T entity) {
        return 0;
    }

    @Override
    public int deleteAllByIds(Collection<T> entities) {
        return 0;
    }

    @Override
    public int update(T entity) {
        return 0;
    }

    @Override
    public int updateAllByIds(Collection<T> entities) {
        return 0;
    }

    @Override
    public T getById(int id) {
        return null;
    }

    @Override
    public T getById(T entity) {
        return null;
    }

    @Override
    public List<T> findPage(Page page, T entity) {
        return null;
    }

    @Override
    public List<T> findAllByFields(T entity) {
        return null;
    }

    @Override
    public List<T> findAllByMap(Map<String, Object> parameters) {
        return null;
    }
}
