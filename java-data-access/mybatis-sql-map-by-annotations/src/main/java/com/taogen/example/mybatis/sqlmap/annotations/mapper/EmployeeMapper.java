package com.taogen.example.mybatis.sqlmap.annotations.mapper;

import com.taogen.example.mybatis.sqlmap.annotations.entity.Department;
import com.taogen.example.mybatis.sqlmap.annotations.entity.Employee;
import com.taogen.example.mybatis.sqlmap.annotations.entity.Page;
import com.taogen.example.mybatis.sqlmap.annotations.mapper.sqlprovider.DepartmentSqlProvider;
import com.taogen.example.mybatis.sqlmap.annotations.mapper.sqlprovider.EmployeeSqlProvider;
import org.apache.ibatis.annotations.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author Employeeaogen
 */
public interface EmployeeMapper extends CrudMapper {

    @InsertProvider(type = EmployeeSqlProvider.class, method = "saveSelective")
    int saveSelective(Employee entity);


    @DeleteProvider(type = EmployeeSqlProvider.class, method = "deleteById")
    int deleteById(Employee entity);

    @UpdateProvider(type = EmployeeSqlProvider.class, method = "deleteLogically")
    int deleteLogically(Employee entity);

    @Delete({"<script>",
            "delete from t_employee",
            "where id in ",
            "<foreach item='item' index='index' collection='entities' open='(' separator=',' close=')'>",
            "#{item.id}",
            "</foreach>",
            "</script>"})
    int deleteAll(@Param("entities") Collection<Employee> entities);

    @Update({"<script>",
            "update t_employee",
            "set delete_flag = 1",
            "where id in ",
            "<foreach item='item' index='index' collection='entities' open='(' separator=',' close=')'>",
            "#{item.id}",
            "</foreach>",
            "</script>"})
    int deleteAllLogically(@Param("entities") Collection<Employee> entities);

    @DeleteProvider(type = EmployeeSqlProvider.class, method = "deleteAllByFields")
    int deleteAllByFields(Employee entity);

    @Delete({"<script>",
            "delete from t_employee",
            "where",
            "<foreach collection='conditions' index='key' item='value' open='' separator=' and ' close=''>",
            "${key}=#{value}",
            "</foreach>",
            "</script>"})
    int deleteAllByMap(@Param("conditions") Map<String, Object> conditions);

    @UpdateProvider(type = EmployeeSqlProvider.class, method = "updateSelective")
    int updateSelective(Employee entity);

//    int updateAllFieldsByMap(@Param("entity") Employee entity, @Param("conditions") Map<String, Object> conditions);

    @SelectProvider(type = EmployeeSqlProvider.class, method = "getById")
    @Results(id = "entityResultGetById", value = {
            @Result(property = "id", column = "id", id = true),
            @Result(property = "name", column = "name"),
            @Result(property = "nickname", column = "nickname"),
            @Result(property = "age", column = "age"),
            @Result(property = "deleteFlag", column = "delete_flag"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "modifyTime", column = "modify_time"),
    })
    Employee getById(Employee entity);

    @SelectProvider(type = EmployeeSqlProvider.class, method = "callById")
    @Results(id = "entityResultCallById", value = {
            @Result(property = "id", column = "id", id = true),
            @Result(property = "name", column = "name"),
            @Result(property = "nickname", column = "nickname"),
            @Result(property = "age", column = "age"),
            @Result(property = "deleteFlag", column = "delete_flag"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "modifyTime", column = "modify_time")
    })
    Employee callById(Employee entity);

    @SelectProvider(type = EmployeeSqlProvider.class, method = "count")
    long count();

    @SelectProvider(type = EmployeeSqlProvider.class, method = "countByFields")
    long countByFields(Employee entity);

    @Select({"<script>",
            "select count(*)",
            "from t_employee",
            "where",
            "<foreach collection='conditions' index='key' item='value' open='(' separator='and' close=')'>",
            "${key}=#{value}",
            "</foreach>",
            "</script>"})
    long countByMap(@Param("conditions") Map<String, Object> conditions);

    @SelectProvider(type = EmployeeSqlProvider.class, method = "findAllByFields")
    @Results(id = "entityResultFindAllByFields", value = {
            @Result(property = "id", column = "id", id = true),
            @Result(property = "name", column = "name"),
            @Result(property = "nickname", column = "nickname"),
            @Result(property = "age", column = "age"),
            @Result(property = "deleteFlag", column = "delete_flag"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "modifyTime", column = "modify_time")
    })
    List<Employee> findAllByFields(Employee entity);

    @SelectProvider(type = EmployeeSqlProvider.class, method = "findPage")
    @Results(id = "entityResultFindPage", value = {
            @Result(property = "id", column = "id", id = true),
            @Result(property = "name", column = "name"),
            @Result(property = "deleteFlag", column = "delete_flag"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "modifyTime", column = "modify_time")
    })
    List<Employee> findPage(@Param("page") Page page, @Param("entity") Employee entity);

    @Select({"<script>",
            "select *",
            "from t_employee",
            "where",
            "<foreach collection='conditions' index='key' item='value' open='' separator=' and ' close=''>",
            "${key}=#{value}",
            "</foreach>",
            "</script>"})
    @Results(id = "entityResultFindAllByMap", value = {
            @Result(property = "id", column = "id", id = true),
            @Result(property = "name", column = "name"),
            @Result(property = "nickname", column = "nickname"),
            @Result(property = "age", column = "age"),
            @Result(property = "deleteFlag", column = "delete_flag"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "modifyTime", column = "modify_time")
    })
    List<Employee> findAllByMap(@Param("conditions") Map<String, Object> conditions);

    @InsertProvider(type = EmployeeSqlProvider.class, method = "execInsertSql")
    Integer execInsertSql(String sql);

    @DeleteProvider(type = EmployeeSqlProvider.class, method = "execDeleteSql")
    Integer execDeleteSql(String sql);

    @UpdateProvider(type = EmployeeSqlProvider.class, method = "execUpdateSql")
    Integer execUpdateSql(String sql);

    @SelectProvider(type = EmployeeSqlProvider.class, method = "execSelectSql")
    @Results(id = "entityResultExecSelectSql", value = {
            @Result(property = "id", column = "id", id = true),
            @Result(property = "name", column = "name"),
            @Result(property = "nickname", column = "nickname"),
            @Result(property = "age", column = "age"),
            @Result(property = "deleteFlag", column = "delete_flag"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "modifyTime", column = "modify_time")
    })
    List<Employee> execSelectSql(String sql);
}