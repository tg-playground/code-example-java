package com.taogen.example.mybatis.sqlmap.annotations.service.impl;

import com.taogen.example.mybatis.sqlmap.annotations.entity.Employee;
import com.taogen.example.mybatis.sqlmap.annotations.entity.Page;
import com.taogen.example.mybatis.sqlmap.annotations.mapper.EmployeeMapper;
import com.taogen.example.mybatis.sqlmap.annotations.service.AbstractCrudService;
import com.taogen.example.mybatis.sqlmap.annotations.service.EmployeeService;
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
public class EmployeeServiceImpl
        extends AbstractCrudService
        implements EmployeeService {

    private static final Logger logger = LogManager.getLogger();

    private SqlSessionFactoryService sqlSessionFactoryService = new SqlSessionFactoryServiceImpl();

    private SqlSession getSqlSession() {
        return sqlSessionFactoryService.getSqlSessionFactory().openSession(false);
    }

    private EmployeeMapper getMapperFromSession(SqlSession session) {
        return session.getMapper(EmployeeMapper.class);
    }

    @Override
    public int save(Employee entity) {
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
    public int saveAll(Collection<Employee> entities, Boolean testException) {
        SqlSession session = getSqlSession();
        int result = 0;
        try {
            for (Employee t : entities) {
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
    public int saveOrUpdate(Employee entity) {
        SqlSession session = getSqlSession();
        int result = 0;
        try {
            EmployeeMapper mapper = getMapperFromSession(session);
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
    public int deleteById(Employee entity) {
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
    public int deleteLogically(Employee entity) {
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
    public int deleteAllByIds(Collection<Employee> entities) {
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
    public int deleteAllLogically(Collection<Employee> entities) {
        SqlSession session = getSqlSession();
        int result = 0;
        try {
            for (Employee entity : entities) {
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
    public int update(Employee entity) {
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
    public int updateAllByIds(Collection<Employee> entities) {
        SqlSession session = getSqlSession();
        int result = 0;
        try {
            for (Employee entity : entities) {
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
    public Employee getById(Employee entity) {
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
    public List<Employee> findPage(Page page, Employee entity) {
        try (SqlSession session = getSqlSession()) {
            return getMapperFromSession(session).findPage(page, entity);
        } catch (Exception e) {
            logger.error(e);
        }
        return null;
    }

    @Override
    public List<Employee> findAllByFields(Employee entity) {
        try (SqlSession session = getSqlSession()) {
            return getMapperFromSession(session).findAllByFields(entity);
        } catch (Exception e) {
            logger.error(e);
        }
        return null;
    }

    @Override
    public List<Employee> findAllByMap(Map<String, Object> conditions) {
        try (SqlSession session = getSqlSession()) {
            return getMapperFromSession(session).findAllByMap(conditions);
        } catch (Exception e) {
            logger.error(e);
        }
        return null;
    }
}
