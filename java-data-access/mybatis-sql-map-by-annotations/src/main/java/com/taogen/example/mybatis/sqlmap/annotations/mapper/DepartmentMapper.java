package com.taogen.example.mybatis.sqlmap.annotations.mapper;

import com.taogen.example.mybatis.sqlmap.annotations.entity.Department;
import com.taogen.example.mybatis.sqlmap.annotations.entity.Page;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.builder.annotation.ProviderMethodResolver;
import org.apache.ibatis.jdbc.SQL;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author Departmentaogen
 */
public interface DepartmentMapper extends CrudMapper {

    @InsertProvider(DepartmentSqlProvider.class)
    int saveSelective(Department entity);

    @DeleteProvider(DepartmentSqlProvider.class)
    int deleteById(Department entity);

    @DeleteProvider(DepartmentSqlProvider.class)
    int deleteLogically(Department entity);

    @DeleteProvider(DepartmentSqlProvider.class)
    int deleteAll(@Param("entities") Collection<Department> entities);

    @DeleteProvider(DepartmentSqlProvider.class)
    int deleteAllLogically(@Param("entities") Collection<Department> entities);

    @DeleteProvider(DepartmentSqlProvider.class)
    int deleteAllByFields(Department entity);

    @DeleteProvider(DepartmentSqlProvider.class)
    int deleteAllByMap(@Param("conditions") Map<String, Object> conditions);

    @UpdateProvider(DepartmentSqlProvider.class)
    int updateSelective(Department entity);

    @UpdateProvider(DepartmentSqlProvider.class)
    int updateAllFieldsByMap(@Param("entity") Department entity, @Param("conditions") Map<String, Object> conditions);

    @SelectProvider(DepartmentSqlProvider.class)
    Department getById(Department entity);

    @SelectProvider(DepartmentSqlProvider.class)
    Department callById(Department entity);

    @SelectProvider(DepartmentSqlProvider.class)
    long count();

    @SelectProvider(DepartmentSqlProvider.class)
    long countByFields(Department entity);

    @SelectProvider(DepartmentSqlProvider.class)
    long countByMap(@Param("conditions") Map<String, Object> conditions);

    @SelectProvider(DepartmentSqlProvider.class)
    List<Department> findAllByFields(Department entity);

    @SelectProvider(DepartmentSqlProvider.class)
    List<Department> findPage(@Param("page") Page page, @Param("entity") Department entity);

    @SelectProvider(DepartmentSqlProvider.class)
    List<Department> findAllByMap(@Param("conditions") Map<String, Object> conditions);

    @SelectProvider(DepartmentSqlProvider.class)
    int execInsertSql(String sql);

    @SelectProvider(DepartmentSqlProvider.class)
    int execDeleteSql(String sql);

    @SelectProvider(DepartmentSqlProvider.class)
    int execUpdateSql(String sql);

    @SelectProvider(DepartmentSqlProvider.class)
    List<Department> execSelectSql(String sql);

    static class DepartmentSqlProvider implements ProviderMethodResolver {
        private static final Logger logger = LogManager.getLogger();
        private static final String TABLE_NAME = "t_department";
        private static final String COLUMN_ID = "id";
        private static final String COLUMN_NAME = "name";
        private static final String COLUMN_DELETE_FLAG = "delete_flag";
        private static final String COLUMN_CREATE_TIME = "create_time";
        private static final String KEY_MODIFY_TIME = "modify_time";

        public static String getSqlValue(String column) {
            return String.format("#{%s}", column);
        }

        public static String getSqlKeyEqualsValue(String column) {
            return new StringBuilder().append(column).append("=").append(getSqlValue(column)).toString();
        }

        public static String getSqlKeyEqualsValue(String key, String value) {
            return new StringBuilder().append(key).append("=\"").append(value).append("\"").toString();
        }

        public String saveSelective(final Department entity) {
            String sql = new SQL() {{
                INSERT_INTO(TABLE_NAME);
                if (entity.getId() != null) {
                    VALUES(COLUMN_ID, getSqlValue(COLUMN_ID));
                }
                if (entity.getName() != null) {
                    VALUES(COLUMN_NAME, getSqlValue(COLUMN_NAME));
                }
                if (entity.getDeleteFlag() != null) {
                    VALUES(COLUMN_DELETE_FLAG, getSqlValue(COLUMN_DELETE_FLAG));
                }
                if (entity.getCreateTime() != null) {
                    VALUES(COLUMN_CREATE_TIME, getSqlValue(COLUMN_CREATE_TIME));
                }
            }}.toString();
            logger.debug("sql is {}", sql);
            return sql;
        }

        public static String deleteById(Department entity) {
            return new SQL()
                    .DELETE_FROM(TABLE_NAME)
                    .WHERE(getSqlKeyEqualsValue(COLUMN_ID))
                    .toString();
        }

        public static String deleteLogically(Department entity) {
            return new SQL()
                    .UPDATE(TABLE_NAME)
                    .SET(getSqlKeyEqualsValue(COLUMN_DELETE_FLAG, "1"))
                    .WHERE(getSqlKeyEqualsValue(COLUMN_ID))
                    .toString();
        }

        public static String deleteAll(Collection<Department> entities) {
            String sql = new SQL() {{
                DELETE_FROM(TABLE_NAME);
                for (Department department : entities) {
                    WHERE(getSqlKeyEqualsValue(COLUMN_ID, department.getId().toString()));
                }
            }}.toString();
            return sql;
        }

        public static String deleteAllLogically(Collection<Department> entities) {
            String sql = new SQL() {{
                UPDATE(TABLE_NAME);
                SET(getSqlKeyEqualsValue(COLUMN_DELETE_FLAG, "1"));
                for (Department department : entities) {
                    WHERE(getSqlKeyEqualsValue(COLUMN_ID, department.getId().toString()));
                }
            }}.toString();
            return sql;
        }

        public static String deleteAllByFields(Department entity) {
            return null;
        }

        public static String deleteAllByMap(Map<String, Object> conditions) {
            return null;
        }

        public static String updateSelective(Department entity) {
            return null;
        }

        public static String updateAllFieldsByMap(Department entity, @Param("conditions") Map<String, Object> conditions) {
            return null;
        }

        public static String getById(Department entity) {
            String sql = new SQL()
                    .SELECT("*")
                    .FROM(TABLE_NAME)
                    .WHERE(getSqlKeyEqualsValue(COLUMN_ID))
                    .toString();
            logger.debug("sql is {}", sql);
            return sql;
        }

        public static String callById(Department entity) {
            return null;
        }

        public static String count() {
            return null;
        }

        public static String countByFields(Department entity) {
            return null;
        }

        public static String countByMap(Map<String, Object> conditions) {
            return null;
        }

        public static String findAllByFields(Department entity) {
            return null;
        }

        public static String findPage(Page page, Department entity) {
            return null;
        }

        public static String findAllByMap(Map<String, Object> conditions) {
            return null;
        }

        public static String execInsertSql(String sql) {
            return null;
        }

        public static String execDeleteSql(String sql) {
            return null;
        }

        public static String execUpdateSql(String sql) {
            return null;
        }

        public static String execSelectSql(String sql) {
            return null;
        }
    }
}