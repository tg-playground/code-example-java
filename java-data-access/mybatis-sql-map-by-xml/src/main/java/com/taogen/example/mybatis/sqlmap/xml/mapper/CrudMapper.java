package com.taogen.example.mybatis.sqlmap.xml.mapper;

import com.taogen.example.mybatis.sqlmap.xml.entity.BaseEntity;

/**
 * @author Taogen
 */
public interface CrudMapper<T extends BaseEntity> extends BaseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(T record);

    int insertSelective(T record);

    T selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(T record);

    int updateByPrimaryKey(T record);

}
