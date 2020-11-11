package com.taogen.demo.springbootcrud.core.web.service;

import com.taogen.demo.springbootcrud.core.persistence.mapper.CrudMapper;
import com.taogen.demo.springbootcrud.core.persistence.entity.BaseEntity;
import com.taogen.demo.springbootcrud.core.web.vo.QueryPage;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author Taogen
 */
public abstract class AbstractCrudService<M extends CrudMapper<T>, T extends BaseEntity>
        extends AbstractBaseService implements CrudService<T> {

//    private static final Logger logger = LogManager.getLogger();

    protected M mapper;

    public M getMapper() {
        return mapper;
    }

    public void setMapper(M mapper) {
        this.mapper = mapper;
    }

    @Override
    public int save(T entity) {
        return mapper.saveSelective(entity);
    }

    @Override
    public int saveAll(Collection<T> entities, Boolean testException) {
        return -1;
    }

    @Override
    public int saveOrUpdate(T entity) {
        int result = 0;
        if (mapper.getById(entity.getId()) == null) {
            result = mapper.saveSelective(entity);
        } else {
            result = mapper.updateSelective(entity);
        }

        return result;
    }

    @Override
    public int deleteById(Serializable id) {
        return mapper.deleteById(id);
    }


    @Override
    public int deleteLogically(Serializable id) {
        return mapper.deleteLogically(id);
    }

    @Override
    public int deleteAllByIds(Collection<Serializable> ids) {
        return mapper.deleteAll(ids);
    }

    @Override
    public int deleteAllLogically(Collection<T> entities) {
        return -1;
    }

    @Override
    public int deleteAllByMap(Map<String, Object> conditions) {
        return mapper.deleteAllByMap(conditions);
    }

    @Override
    public int update(T entity) {
        return mapper.updateSelective(entity);
    }

    @Override
    public int updateAllByIds(Collection<T> entities) {
        return -1;
    }

    @Override
    public T getById(Serializable id) {
        return mapper.getById(id);
    }

    @Override
    public Long count() {
        return mapper.count();
    }

    @Override
    public List<T> findPage(QueryPage<T> queryPage) {
        return mapper.findPage(queryPage);
    }

    @Override
    public List<T> findAllByFields(T entity) {
        return mapper.findAllByFields(entity);
    }

    @Override
    public List<T> findAllByMap(Map<String, Object> conditions) {
        return mapper.findAllByMap(conditions);
    }
}
