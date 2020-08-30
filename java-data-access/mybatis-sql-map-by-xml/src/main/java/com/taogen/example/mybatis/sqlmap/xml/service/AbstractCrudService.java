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
        return sqlSessionFactoryService.getSqlSessionFactory().openSession(false);
    }

    private M getMapperFromSession(SqlSession session) {
        return session.getMapper(clazz);
    }

    @Override
    public int save(T entity) {
        SqlSession session = getSqlSession();
        int result = 0;
        try {
            result = getMapperFromSession(session).saveSelective(entity);
            session.commit();
        } catch (Exception e) {
            logger.error(e);
            session.rollback();
            result = 0;
        } finally {
            session.close();
        }
        return result;
    }

    @Override
    public int saveAll(Collection<T> entities, Boolean testException) {
        SqlSession session = getSqlSession();
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
            result = 0;
        } finally {
            session.close();
        }
        return result;
    }

    @Override
    public int saveOrUpdate(T entity) {
        SqlSession session = getSqlSession();
        int result = 0;
        try {
            M mapper = getMapperFromSession(session);
            if (mapper.getById(entity) == null) {
                result = mapper.saveSelective(entity);
            } else {
                result = mapper.updateSelective(entity);
            }
            session.commit();
        } catch (Exception e) {
            logger.error(e);
            session.rollback();
            result = 0;
        } finally {
            session.close();
        }
        return result;
    }

    @Override
    public int deleteById(T entity) {
        SqlSession session = getSqlSession();
        int result = 0;
        try {
            result = getMapperFromSession(session).deleteById(entity);
            session.commit();
        } catch (Exception e) {
            logger.error(e);
            session.rollback();
            result = 0;
        } finally {
            session.close();
        }
        return result;
    }


    @Override
    public int deleteLogically(T entity) {
        SqlSession session = getSqlSession();
        int result = 0;
        try {
            result = getMapperFromSession(session).deleteLogically(entity);
            session.commit();
        } catch (Exception e) {
            logger.error(e);
            session.rollback();
            result = 0;
        } finally {
            session.close();
        }
        return result;
    }

    @Override
    public int deleteAllByIds(Collection<T> entities) {
        SqlSession session = getSqlSession();
        int result = 0;
        try {
            result = getMapperFromSession(session).deleteAll(entities);
            session.commit();
        } catch (Exception e) {
            logger.error(e);
            session.rollback();
            result = 0;
        } finally {
            session.close();
        }
        return result;
    }

    @Override
    public int deleteAllLogically(Collection<T> entities) {
        SqlSession session = getSqlSession();
        int result = 0;
        try {
            for (T entity : entities) {
                result += getMapperFromSession(session).deleteLogically(entity);
            }
            session.commit();
        } catch (Exception e) {
            logger.error("{}: {}", e.getClass().getName(), e.getMessage(), e);
            session.rollback();
            result = 0;
        } finally {
            session.close();
        }
        return result;
    }

    @Override
    public int deleteAllByMap(Map<String, Object> conditions) {
        SqlSession session = getSqlSession();
        int result = 0;
        try {
            result = getMapperFromSession(session).deleteAllByMap(conditions);
            session.commit();
        } catch (Exception e) {
            logger.error(e);
            session.rollback();
            result = 0;
        } finally {
            session.close();
        }
        return result;
    }

    @Override
    public int update(T entity) {
        SqlSession session = getSqlSession();
        int result = 0;
        try {
            result = getMapperFromSession(session).updateSelective(entity);
            session.commit();
        } catch (Exception e) {
            logger.error(e);
            session.rollback();
            result = 0;
        } finally {
            session.close();
        }
        return result;
    }

    @Override
    public int updateAllByIds(Collection<T> entities) {
        SqlSession session = getSqlSession();
        int result = 0;
        try {
            for (T entity : entities) {
                result += getMapperFromSession(session).updateSelective(entity);
            }
            session.commit();
        } catch (Exception e) {
            logger.error("{}: {}", e.getClass().getName(), e.getMessage(), e);
            session.rollback();
            result = 0;
        } finally {
            session.close();
        }
        return result;
    }

    @Override
    public T getById(T entity) {
        try (SqlSession session = getSqlSession()) {
            return getMapperFromSession(session).getById(entity);
        } catch (Exception e) {
            logger.error(e);
        }
        return null;
    }

    @Override
    public Long count() {
        try (SqlSession session = getSqlSession()) {
            return getMapperFromSession(session).count();
        } catch (Exception e) {
            logger.error(e);
        }
        return null;
    }

    @Override
    public List<T> findPage(Page page, T entity) {
        try (SqlSession session = getSqlSession()) {
            return getMapperFromSession(session).findPage(page, entity);
        } catch (Exception e) {
            logger.error(e);
        }
        return null;
    }

    @Override
    public List<T> findAllByFields(T entity) {
        try (SqlSession session = getSqlSession()) {
            return getMapperFromSession(session).findAllByFields(entity);
        } catch (Exception e) {
            logger.error(e);
        }
        return null;
    }

    @Override
    public List<T> findAllByMap(Map<String, Object> conditions) {
        try (SqlSession session = getSqlSession()) {
            return getMapperFromSession(session).findAllByMap(conditions);
        } catch (Exception e) {
            logger.error(e);
        }
        return null;
    }
}
