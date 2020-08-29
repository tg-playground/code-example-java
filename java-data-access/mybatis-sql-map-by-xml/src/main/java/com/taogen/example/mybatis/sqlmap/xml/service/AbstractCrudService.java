package com.taogen.example.mybatis.sqlmap.xml.service;

import com.taogen.example.mybatis.sqlmap.xml.entity.BaseEntity;
import com.taogen.example.mybatis.sqlmap.xml.entity.Page;
import com.taogen.example.mybatis.sqlmap.xml.mapper.CrudMapper;
import com.taogen.example.mybatis.sqlmap.xml.service.impl.SqlSessionFactoryServiceImpl;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author Taogen
 */
public abstract class AbstractCrudService<M extends CrudMapper<T>, T extends BaseEntity>
        extends AbstractBaseService implements CrudService<M, T> {

    private static final Logger logger = LogManager.getLogger();

    private Class<M> clazz;
    private SqlSessionFactoryService sqlSessionFactoryService = new SqlSessionFactoryServiceImpl();

    public AbstractCrudService() {
        this.clazz = (Class<M>) ((ParameterizedType) getClass()
                .getGenericSuperclass())
                .getActualTypeArguments()[0];
    }

    private SqlSession getSqlSession() {
        return sqlSessionFactoryService.getSqlSessionFactory().openSession(true);
    }

    private SqlSession getSqlSessionWithoutAutoCommit() {
        return sqlSessionFactoryService.getSqlSessionFactory().openSession(false);
    }

    private M getMapperFromSession(SqlSession session) {
        return session.getMapper(clazz);
    }

    @Override
    public int save(T entity) {
        SqlSession session = getSqlSession();
        try {
            return getMapperFromSession(session).saveSelective(entity);
        } catch (Exception e) {
            session.rollback();
        } finally {
            session.close();
        }
        return 0;
    }

    @Override
    public int saveAll(Collection<T> entities, Boolean testException) {
        SqlSession session = getSqlSessionWithoutAutoCommit();
        int result = 0;
        try {
            for (T t : entities) {
                result += getMapperFromSession(session).saveSelective(t);
                if (testException && result >= 1) {
                    int i = 1 / 0;
                }
            }
            session.commit();
        } catch (Exception e) {
            logger.error("{}: {}", e.getClass().getName(), e.getMessage(), e);
            session.rollback();
            return 0;
        } finally {
            session.close();
        }
        return result;
    }

    @Override
    public int deleteById(T entity) {
        SqlSession session = getSqlSession();
        try {
            return getMapperFromSession(session).deleteById(entity);
        } catch (Exception e) {
            session.rollback();
        } finally {
            session.close();
        }
        return 0;
    }

    @Override
    public int deleteAllByIds(Collection<T> entities) {
        SqlSession session = getSqlSession();
        try {
            return getMapperFromSession(session).deleteAll(entities);
        } catch (Exception e) {
            session.rollback();
        } finally {
            session.close();
        }
        return 0;
    }

    @Override
    public int deleteAllByMap(Map<String, Object> conditions) {
        SqlSession session = getSqlSession();
        try {
            return getMapperFromSession(session).deleteAllByMap(conditions);
        } catch (Exception e) {
            session.rollback();
        } finally {
            session.close();
        }
        return 0;
    }

    @Override
    public int update(T entity) {
        SqlSession session = getSqlSession();
        try {
            return getMapperFromSession(session).updateSelective(entity);
        } catch (Exception e) {
            session.rollback();
        } finally {
            session.close();
        }
        return 0;
    }

    @Override
    public int updateAllByIds(Collection<T> entities) {
        SqlSession session = getSqlSessionWithoutAutoCommit();
        int result = 0;
        try {
            for (T entity : entities) {
                result += getMapperFromSession(session).updateSelective(entity);
            }
            session.commit();
        } catch (Exception e) {
            logger.error("{}: {}", e.getClass().getName(), e.getMessage(), e);
            session.rollback();
        } finally {
            session.close();
        }
        return result;
    }

    @Override
    public T getById(T entity) {
        SqlSession session = getSqlSession();
        try {
            return getMapperFromSession(session).getById(entity);
        } catch (Exception e) {
            session.rollback();
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public Long count() {
        SqlSession session = getSqlSession();
        try {
            return getMapperFromSession(session).count();
        } catch (Exception e) {
            session.rollback();
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public List<T> findPage(Page page, T entity) {
        SqlSession session = getSqlSession();
        try {
            return getMapperFromSession(session).findPage(page, entity);
        } catch (Exception e) {
            session.rollback();
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public List<T> findAllByFields(T entity) {
        SqlSession session = getSqlSession();
        try {
            return getMapperFromSession(session).findAllByFields(entity);
        } catch (Exception e) {
            session.rollback();
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public List<T> findAllByMap(Map<String, Object> parameters) {
        SqlSession session = getSqlSession();
        try {
            return getMapperFromSession(session).findAllByMap(parameters);
        } catch (Exception e) {
            session.rollback();
        } finally {
            session.close();
        }
        return null;
    }
}
