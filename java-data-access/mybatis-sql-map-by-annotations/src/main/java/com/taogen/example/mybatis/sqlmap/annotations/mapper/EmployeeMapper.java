package com.taogen.example.mybatis.sqlmap.annotations.mapper;

import com.taogen.example.mybatis.sqlmap.annotations.entity.Employee;
import com.taogen.example.mybatis.sqlmap.annotations.entity.Page;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author Employeeaogen
 */
public interface EmployeeMapper extends CrudMapper {

    int saveSelective(Employee entity);

    int deleteById(Employee entity);

    int deleteLogically(Employee entity);

    int deleteAll(@Param("entities") Collection<Employee> entities);

    int deleteAllLogically(@Param("entities") Collection<Employee> entities);

    int deleteAllByFields(Employee entity);

    int deleteAllByMap(@Param("conditions") Map<String, Object> conditions);

    int updateSelective(Employee entity);

    int updateAllFieldsByMap(@Param("entity") Employee entity, @Param("conditions") Map<String, Object> conditions);

    Employee getById(Employee entity);

    Employee callById(Employee entity);

    long count();

    long countByFields(Employee entity);

    long countByMap(@Param("conditions") Map<String, Object> conditions);

    List<Employee> findAllByFields(Employee entity);

    List<Employee> findPage(@Param("page") Page page, @Param("entity") Employee entity);

    List<Employee> findAllByMap(@Param("conditions") Map<String, Object> conditions);

    int execInsertSql(String sql);

    int execDeleteSql(String sql);

    int execUpdateSql(String sql);

    List<Employee> execSelectSql(String sql);
}