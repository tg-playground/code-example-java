package com.taogen.example.mybatis.sqlmap.annotations.mapper;

import com.taogen.example.mybatis.sqlmap.annotations.entity.BaseEntity;
import com.taogen.example.mybatis.sqlmap.annotations.entity.Page;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author Taogen
 */
public interface CrudMapper<T extends BaseEntity> extends BaseMapper {

//    int saveSelective(T entity);
//
//    int deleteById(T entity);
//
//    int deleteLogically(T entity);
//
//    int deleteAll(@Param("entities") Collection<T> entities);
//
//    int deleteAllLogically(@Param("entities") Collection<T> entities);
//
//    int deleteAllByFields(T entity);
//
//    int deleteAllByMap(@Param("conditions") Map<String, Object> conditions);
//
//    int updateSelective(T entity);
//
//    int updateAllFieldsByMap(@Param("entity") T entity, @Param("conditions") Map<String, Object> conditions);
//
//    T getById(T entity);
//
//    T callById(T entity);
//
//    long count();
//
//    long countByFields(T entity);
//
//    long countByMap(@Param("conditions") Map<String, Object> conditions);
//
//    List<T> findAllByFields(T entity);
//
//    List<T> findPage(@Param("page") Page page, @Param("entity") T entity);
//
//    List<T> findAllByMap(@Param("conditions") Map<String, Object> conditions);
//
//    int execInsertSql(String sql);
//
//    int execDeleteSql(String sql);
//
//    int execUpdateSql(String sql);
//
//    List<T> execSelectSql(String sql);
}
