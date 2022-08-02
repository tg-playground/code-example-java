package com.taogen.example.mybatis.sqlmap.annotations.service.impl;


import com.taogen.example.mybatis.sqlmap.annotations.entity.Department;
import com.taogen.example.mybatis.sqlmap.annotations.entity.Page;
import com.taogen.example.mybatis.sqlmap.annotations.mapper.DepartmentMapper;
import com.taogen.example.mybatis.sqlmap.annotations.service.AbstractCrudService;
import com.taogen.example.mybatis.sqlmap.annotations.service.DepartmentService;
import com.taogen.example.mybatis.sqlmap.annotations.service.SqlSessionFactoryService;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author Taogen
 */
public class DepartmentServiceImpl
        extends AbstractCrudService
        implements DepartmentService {
    private static final Logger logger = LogManager.getLogger();

    private SqlSessionFactoryService sqlSessionFactoryService = new SqlSessionFactoryServiceImpl();

    private SqlSession getSqlSession() {
        return sqlSessionFactoryService.getSqlSessionFactory().openSession(false);
    }

    private DepartmentMapper getMapperFromSession(SqlSession session) {
        return session.getMapper(DepartmentMapper.class);
    }

    @Override
    public int save(Department entity) {
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
    public int saveAll(Collection<Department> entities, Boolean testException) {
        SqlSession session = getSqlSession();
        int result = 0;
        try {
            for (Department t : entities) {
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
    public int saveOrUpdate(Department entity) {
        SqlSession session = getSqlSession();
        int result = 0;
        try {
            DepartmentMapper mapper = getMapperFromSession(session);
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
    public int deleteById(Department entity) {
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
    public int deleteLogically(Department entity) {
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
    public int deleteAllByIds(Collection<Department> entities) {
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
    public int deleteAllLogically(Collection<Department> entities) {
        SqlSession session = getSqlSession();
        int result = 0;
        try {
            for (Department entity : entities) {
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
    public int update(Department entity) {
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
    public int updateAllByIds(Collection<Department> entities) {
        SqlSession session = getSqlSession();
        int result = 0;
        try {
            for (Department entity : entities) {
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
    public Department getById(Department entity) {
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
    public List<Department> findPage(Page page, Department entity) {
        try (SqlSession session = getSqlSession()) {
            return getMapperFromSession(session).findPage(page, entity);
        } catch (Exception e) {
            logger.error(e);
        }
        return null;
    }

    @Override
    public List<Department> findAllByFields(Department entity) {
        try (SqlSession session = getSqlSession()) {
            return getMapperFromSession(session).findAllByFields(entity);
        } catch (Exception e) {
            logger.error(e);
        }
        return null;
    }

    @Override
    public List<Department> findAllByMap(Map<String, Object> conditions) {
        try (SqlSession session = getSqlSession()) {
            return getMapperFromSession(session).findAllByMap(conditions);
        } catch (Exception e) {
            logger.error(e);
        }
        return null;
    }
}
