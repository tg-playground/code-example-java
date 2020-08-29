package com.taogen.example.mybatis.sqlmap.xml.mapper;

import com.taogen.example.mybatis.sqlmap.xml.entity.BaseEntity;
import com.taogen.example.mybatis.sqlmap.xml.entity.Page;
import org.apache.ibatis.annotations.Param;

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

    int deleteById(T entity);

    int deleteAll(@Param("entities") Collection<T> entities);

    int deleteAllByField(T entity);

    int deleteAllByMap(@Param("conditions") Map<String, Object> conditions);

//    int update(T entity);

    int updateSelective(T entity);

//    int updateAll(Collection<T> entities);

    int updateAllFieldsByMap(@Param("entity") T entity, @Param("conditions") Map<String, Object> conditions);

    T getById(T entity);

    long count();

    long countByField(T entity);

    long countByMap(@Param("conditions") Map<String, Object> conditions);

    List<T> findAllByFields(T entity);

    List<T> findPage(@Param("page") Page page, @Param("entity") T entity);

    List<T> findAllByMap(@Param("conditions") Map<String, Object> conditions);

    int execInsertSql(String sql);

    int execDeleteSql(String sql);

    int execUpdateSql(String sql);

    List<T> execSelectSql(String sql);
}
