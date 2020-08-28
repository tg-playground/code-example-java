package com.taogen.example.mybatis.sqlmap.xml.service;

import com.taogen.example.mybatis.sqlmap.xml.entity.BaseEntity;
import com.taogen.example.mybatis.sqlmap.xml.entity.Page;
import com.taogen.example.mybatis.sqlmap.xml.mapper.CrudMapper;
import com.taogen.example.mybatis.sqlmap.xml.service.impl.SqlSessionFactoryServiceImpl;
import org.apache.ibatis.session.SqlSession;

import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author Taogen
 */
public abstract class AbstractCrudService<M extends CrudMapper<T>, T extends BaseEntity>
        extends AbstractBaseService implements CrudService<M, T> {

    private Class<M> clazz;
    private M mapper;
    private SqlSessionFactoryService sqlSessionFactoryService = new SqlSessionFactoryServiceImpl();
    private SqlSession sqlSession;

    public AbstractCrudService() {
        this.sqlSession = sqlSessionFactoryService.getSqlSessionFactory().openSession(true);
        this.clazz = (Class<M>) ((ParameterizedType)getClass()
                .getGenericSuperclass())
                .getActualTypeArguments()[0];
        this.mapper = (M) sqlSession.getMapper(clazz);
    }

    @Override
    public int save(T entity) {
        return mapper.saveSelective(entity);
    }

    @Override
    public int saveAll(Collection<T> entities, Boolean testException) {
        int result = 0;
        try {
            for (T t : entities) {
                result += mapper.saveSelective(t);
                if (testException && result >= 1){
                    int i = 1/0;
                }
            }
        } catch (Exception e) {
           sqlSession.rollback();
        }
        return result;
    }

    @Override
    public int deleteById(T entity) {
        return mapper.deleteById(entity.getId());
    }

    @Override
    public int deleteAllByIds(Collection<T> entities) {
        return mapper.deleteAll(entities);
    }

    @Override
    public int update(T entity) {
        return mapper.updateSelective(entity);
    }

    @Override
    public int updateAllByIds(Collection<T> entities) {
        return mapper.updateAll(entities);
    }

    @Override
    public T getById(int id) {
        return mapper.getById(id);
    }

    @Override
    public T getById(T entity) {
        return mapper.getById(entity.getId());
    }

    @Override
    public Long count() {
        return mapper.count();
    }

    @Override
    public List<T> findPage(Page page, T entity) {
        return mapper.findPage(page, entity);
    }

    @Override
    public List<T> findAllByFields(T entity) {
        return mapper.findAllByFields(entity);
    }

    @Override
    public List<T> findAllByMap(Map<String, Object> parameters) {
        return mapper.findAllByMap(parameters);
    }
}
