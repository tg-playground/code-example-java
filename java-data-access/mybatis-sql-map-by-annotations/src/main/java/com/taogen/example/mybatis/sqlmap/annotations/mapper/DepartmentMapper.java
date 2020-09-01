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
 * @author Departmentaogen
 */
public interface DepartmentMapper extends CrudMapper {

    @InsertProvider(type = DepartmentSqlProvider.class, method = "saveSelective")
    int saveSelective(Department entity);

    @DeleteProvider(type = DepartmentSqlProvider.class, method = "deleteById")
    int deleteById(Department entity);

    @UpdateProvider(type = DepartmentSqlProvider.class, method = "deleteLogically")
    int deleteLogically(Department entity);

    @Delete({"<script>",
            "delete from t_department",
            "where id in ",
            "<foreach item='item' index='index' collection='entities' open='(' separator=',' close=')'>",
            "#{item.id}",
            "</foreach>",
            "</script>"})
    int deleteAll(@Param("entities") Collection<Department> entities);

    @Update({"<script>",
            "update t_department",
            "set delete_flag = 1",
            "where id in ",
            "<foreach item='item' index='index' collection='entities' open='(' separator=',' close=')'>",
            "#{item.id}",
            "</foreach>",
            "</script>"})
    int deleteAllLogically(@Param("entities") Collection<Department> entities);

    @DeleteProvider(type = DepartmentSqlProvider.class, method = "deleteAllByFields")
    int deleteAllByFields(Department entity);

    @Delete({"<script>",
            "delete from t_department",
            "where",
            "<foreach collection='conditions' index='key' item='value' open='' separator=' and ' close=''>",
            "${key}=#{value}",
            "</foreach>",
            "</script>"})
    int deleteAllByMap(@Param("conditions") Map<String, Object> conditions);

    @UpdateProvider(type = DepartmentSqlProvider.class, method = "updateSelective")
    int updateSelective(Department entity);

//    @Update("update t_department"+
//            "<set>"+
//            "modify_time = NOW(), "+
//            " <if test='entity.name' != null> "+
//            " name=#{entity.name, jdbcType=VARCHAR}, "+
//            " </if> "+
//            " <if test='entity.deleteFlag' != null> "+
//            " delete_flag=#{entity.deleteFlag, jdbcType=VARCHAR}, "+
//            " </if> "+
//            "</set>"+
//            "where "+
//            "<foreach collection='conditions' index='key' item='value' open='' separator=' and ' close=''>"+
//            "${key}=#{value}"+
//            "</foreach> ")
//    int updateAllFieldsByMap(@Param("entity") Department entity, @Param("conditions") Map<String, Object> conditions);

    @SelectProvider(type = DepartmentSqlProvider.class, method = "getById")
    @Results(id = "entityResultGetById", value = {
            @Result(property = "id", column = "id", id = true),
            @Result(property = "name", column = "name"),
            @Result(property = "deleteFlag", column = "delete_flag"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "modifyTime", column = "modify_time"),
            @Result(property = "employees", column = "id", javaType = List.class,
                    many = @Many(select = "getOneToManyEmployee"))
    })
    Department getById(Department entity);

    @SelectProvider(type = DepartmentSqlProvider.class, method = "getOneToManyEmployee")
    @Results(id = "oneToManyResult", value = {
            @Result(property = "id", column = "id", id = true),
            @Result(property = "name", column = "name"),
            @Result(property = "nickname", column = "nickname"),
            @Result(property = "age", column = "age"),
            @Result(property = "deleteFlag", column = "delete_flag"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "modifyTime", column = "modify_time")
    })
    List<Employee> getOneToManyEmployee(Integer dpetId);

    @SelectProvider(type = DepartmentSqlProvider.class, method = "callById")
    @Results(id = "entityResultCallById", value = {
            @Result(property = "id", column = "id", id = true),
            @Result(property = "name", column = "name"),
            @Result(property = "deleteFlag", column = "delete_flag"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "modifyTime", column = "modify_time")
    })
    Department callById(Department entity);

    @SelectProvider(type = DepartmentSqlProvider.class, method = "count")
    long count();

    @SelectProvider(type = DepartmentSqlProvider.class, method = "countByFields")
    long countByFields(Department entity);

    @Select({"<script>",
            "select count(*)",
            "from t_department",
            "where",
            "<foreach collection='conditions' index='key' item='value' open='(' separator='and' close=')'>",
            "${key}=#{value}",
            "</foreach>",
            "</script>"})
    long countByMap(@Param("conditions") Map<String, Object> conditions);

    @SelectProvider(type = DepartmentSqlProvider.class, method = "findAllByFields")
    @Results(id = "entityResultFindAllByFields", value = {
            @Result(property = "id", column = "id", id = true),
            @Result(property = "name", column = "name"),
            @Result(property = "deleteFlag", column = "delete_flag"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "modifyTime", column = "modify_time")
    })
    List<Department> findAllByFields(Department entity);

    @SelectProvider(type = DepartmentSqlProvider.class, method = "findPage")
    @Results(id = "entityResultFindPage", value = {
            @Result(property = "id", column = "id", id = true),
            @Result(property = "name", column = "name"),
            @Result(property = "deleteFlag", column = "delete_flag"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "modifyTime", column = "modify_time")
    })
    List<Department> findPage(@Param("page") Page page, @Param("entity") Department entity);

    @Select({"<script>",
            "select *",
            "from t_department",
            "where",
            "<foreach collection='conditions' index='key' item='value' open='' separator=' and ' close=''>",
            "${key}=#{value}",
            "</foreach>",
            "</script>"})
    @Results(id = "entityResultFindAllByMap", value = {
            @Result(property = "id", column = "id", id = true),
            @Result(property = "name", column = "name"),
            @Result(property = "deleteFlag", column = "delete_flag"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "modifyTime", column = "modify_time")
    })
    List<Department> findAllByMap(@Param("conditions") Map<String, Object> conditions);

    @InsertProvider(type = DepartmentSqlProvider.class, method = "execInsertSql")
    Integer execInsertSql(String sql);

    @DeleteProvider(type = DepartmentSqlProvider.class, method = "execDeleteSql")
    Integer execDeleteSql(String sql);

    @UpdateProvider(type = DepartmentSqlProvider.class, method = "execUpdateSql")
    Integer execUpdateSql(String sql);

    @SelectProvider(type = DepartmentSqlProvider.class, method = "execSelectSql")
    @Results(id = "entityResultExecSelectSql", value = {
            @Result(property = "id", column = "id", id = true),
            @Result(property = "name", column = "name"),
            @Result(property = "deleteFlag", column = "delete_flag"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "modifyTime", column = "modify_time")
    })
    List<Department> execSelectSql(String sql);
}