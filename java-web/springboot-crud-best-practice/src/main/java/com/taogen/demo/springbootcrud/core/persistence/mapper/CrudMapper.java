package com.taogen.demo.springbootcrud.core.persistence.mapper;

import com.taogen.demo.springbootcrud.core.persistence.entity.BaseEntity;
import com.taogen.demo.springbootcrud.core.web.vo.QueryPage;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author Taogen
 */
public interface CrudMapper<T extends BaseEntity> extends BaseMapper {
//    int save(T entity);

    int saveSelective(T entity);

//    int saveAll(@Param("entities") Collection<T> entities);

    int deleteById(Serializable id);

    int deleteLogically(Serializable id);

    int deleteAll(@Param("entities") Collection<Serializable> entities);

    int deleteAllLogically(@Param("entities") Collection<T> entities);

    int deleteAllByFields(T entity);

    int deleteAllByMap(@Param("conditions") Map<String, Object> conditions);

//    int update(T entity);

    int updateSelective(T entity);

//    int updateAll(Collection<T> entities);

    int updateAllFieldsByMap(@Param("entity") T entity,
                             @Param("conditions") Map<String, Object> conditions);

    T getById(Serializable id);

    T callById(T entity);

    long count();

    long countByFields(T entity);

    long countByMap(@Param("conditions") Map<String, Object> conditions);

    List<T> findAllByFields(T entity);

    List<T> findPage(@Param("entity") T entity,
                     @Param("queryPage") QueryPage queryPage);

    List<T> findAllByMap(@Param("conditions") Map<String, Object> conditions);

    int execInsertSql(String sql);

    int execDeleteSql(String sql);

    int execUpdateSql(String sql);

    List<T> execSelectSql(String sql);
}
