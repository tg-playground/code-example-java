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

    int deleteById(Integer id);

    int deleteAll(@Param("entities") Collection<T> entities);

    int deleteAllByField(T entity);

    int deleteAllByMap(@Param("params") Map<String, Object> params);

//    int update(T entity);

    int updateSelective(T entity);

//    int updateAll(Collection<T> entities);

    int updateAllFieldsByMap(@Param("entity") T entity, @Param("params") Map<String, Object> params);

    T getById(Integer id);

    long count();

    long countByField(T entity);

    long countByMap(@Param("params") Map<String, Object> params);

    List<T> findAllByFields(T entity);

    List<T> findPage(@Param("page") Page page, @Param("entity") T entity);

    List<T> findAllByMap(@Param("params") Map<String, Object> params);
}
