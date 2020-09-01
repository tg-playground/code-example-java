package com.taogen.example.mybatis.sqlmap.annotations.mapper.sqlprovider;

import com.taogen.example.mybatis.sqlmap.annotations.entity.Department;
import com.taogen.example.mybatis.sqlmap.annotations.entity.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Taogen
 */
public class DepartmentSqlProvider {
    private static final Logger logger = LogManager.getLogger();
    private static final String TABLE_NAME = "t_department";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_DELETE_FLAG = "delete_flag";
    private static final String COLUMN_CREATE_TIME = "create_time";
    private static final String COLUMN_MODIFY_TIME = "modify_time";
    private static final String COLUMN_DEPT_ID = "dept_id";

    private static final Map<String,String> COLUMN_MAP_TO_FIELD = Collections.unmodifiableMap(new HashMap<String, String>(){
        {
            put("id", "id");
            put("name", "name");
            put("delete_flag", "deleteFlag");
            put("create_time", "createTime");
            put("modify_time", "modifyTime");
        }
    });
//    private static final String ONE_TO_MANY_COLUMN_LIST = new StringBuilder()
//            .append("a.id, a.name, a.delete_flag, a.create_time, a.modify_time, ")
//            .append(" b.id as 'employee.id', b.name as 'employee.name', b.nickname as 'employee.name',b.age as 'employee.age', b.delete_flag as 'employee.deleteFlag', b.create_time as 'employee.createTime', b.modify_time as 'employee.modifyTime' ")
//            .toString();
    public static String getSubstituteValueString(String column) {
        return String.format("#{%s}", column);
    }

    public static String getColumnEqualsSubstituteValue(String column) {
        return new StringBuilder().append(column).append("=").append(getSubstituteValueString(COLUMN_MAP_TO_FIELD.get(column))).toString();
    }

    public static String getColumnLikesSubstituteValue(String column) {
        return new StringBuilder()
                .append(column)
                .append(" LIKE CONCAT('%',")
                .append(getSubstituteValueString(column))
                .append(", '%')")
                .toString();
    }

    public static String getColumnEqualsDirectValue(String key, Object value) {
        return new StringBuilder().append(key).append("=").append(value).toString();
    }

    public static String saveSelective(final Department entity) {
        String sql = new SQL() {{
            INSERT_INTO(TABLE_NAME);
            if (entity.getId() != null) {
                VALUES(COLUMN_ID, getSubstituteValueString(COLUMN_ID));
            }
            if (entity.getName() != null) {
                VALUES(COLUMN_NAME, getSubstituteValueString(COLUMN_NAME));
            }
        }}.toString();
        logger.debug("sql is {}", sql);
        return sql;
    }

    public static String deleteById(Department entity) {
        String sql = new SQL()
                .DELETE_FROM(TABLE_NAME)
                .WHERE(getColumnEqualsSubstituteValue(COLUMN_ID))
                .toString();
        logger.debug("sql is {}", sql);
        return sql;
    }

    public static String deleteLogically(Department entity) {
        String sql = new SQL()
                .UPDATE(TABLE_NAME)
                .SET(getColumnEqualsDirectValue(COLUMN_DELETE_FLAG, 1))
                .WHERE(getColumnEqualsSubstituteValue(COLUMN_ID))
                .toString();
        logger.debug("sql is {}", sql);
        return sql;
    }

//    public static String deleteAll(@Param("entities") Collection<Department> entities) {
//        String sql = new SQL() {{
//            DELETE_FROM(TABLE_NAME);
//            for (Department department : entities) {
//                WHERE(getColumnEqualsDirectValue(COLUMN_ID, department.getId()));
//            }
//        }}.toString();
//        return sql;
//    }

//    public static String deleteAllLogically(@Param("entities") Collection<Department> entities) {
//        String sql = new SQL() {{
//            UPDATE(TABLE_NAME);
//            SET(getColumnEqualsDirectValue(COLUMN_DELETE_FLAG, 1));
//            for (Department department : entities) {
//                WHERE(getColumnEqualsDirectValue(COLUMN_ID, department.getId().toString()));
//            }
//        }}.toString();
//        return sql;
//    }

    public static String deleteAllByFields(Department entity) {
        logger.debug("deleteAllByFields");
        String sql = new SQL() {{
            DELETE_FROM(TABLE_NAME);
            WHERE(getColumnEqualsDirectValue(COLUMN_DELETE_FLAG, 0));
            if (entity.getId() != null) {
                WHERE(getColumnEqualsSubstituteValue(COLUMN_ID));
            }
            if (entity.getName() != null) {
                WHERE(getColumnEqualsSubstituteValue(COLUMN_NAME));
            }
            if (entity.getCreateTime() != null) {
                WHERE(getColumnEqualsSubstituteValue(COLUMN_CREATE_TIME));
            }
        }}.toString();
        logger.debug("sql is {}", sql);
        return sql;
    }

//    public static String deleteAllByMap(@Param("conditions") Map<String, Object> conditions) {
//        String sql = new SQL() {{
//            DELETE_FROM(TABLE_NAME);
//            WHERE(getColumnEqualsDirectValue(COLUMN_DELETE_FLAG, 0));
//            for (String key : conditions.keySet()) {
//                getColumnEqualsDirectValue(key, conditions.get(key).toString());
//            }
//        }}.toString();
//        logger.debug("sql is {}", sql);
//        return sql;
//    }

    public static String updateSelective(Department entity) {
        String sql = new SQL() {{
            UPDATE(TABLE_NAME);
            SET(getColumnEqualsDirectValue(COLUMN_MODIFY_TIME, "NOW()"));
            if (entity.getName() != null) {
                SET(getColumnEqualsSubstituteValue(COLUMN_NAME));
            }
            if (entity.getDeleteFlag() != null) {
                SET(getColumnEqualsSubstituteValue(COLUMN_DELETE_FLAG));
            }
            WHERE(getColumnEqualsSubstituteValue(COLUMN_ID));
        }}.toString();
        logger.debug("sql is {}", sql);
        return sql;
    }

//    public static String updateAllFieldsByMap(@Param("entity") Department entity, @Param("conditions") Map<String, Object> conditions) {
//        String sql = new SQL() {{
//            UPDATE(TABLE_NAME);
//            SET(getColumnEqualsDirectValue(COLUMN_MODIFY_TIME, "NOW()"));
//            if (entity.getName() != null) {
//                SET("name=#{entity.name,jdbcType=VARCHAR}");
//            }
//            if (entity.getDeleteFlag() != null) {
//                SET("delete_flag=#{entity.deleteFlag,jdbcType=BIT}");
//            }
//            for (String key : conditions.keySet()) {
//                WHERE(getColumnEqualsSubstituteValue(key));
//            }
//        }}.toString();
//        logger.debug("sql is {}", sql);
//        return sql;
//    }

    public String getById(Department entity) {
        String sql = new SQL()
                .SELECT("*")
                .FROM(TABLE_NAME)
                .WHERE(getColumnEqualsSubstituteValue(COLUMN_ID))
                .toString();
        logger.debug("sql is \n{}", sql);
        return sql;
    }

    public String getOneToManyEmployee(Integer deptId) {
        String sql = new SQL()
                .SELECT("*")
                .FROM("t_employee")
                .WHERE(getColumnEqualsSubstituteValue(COLUMN_DEPT_ID))
                .toString();
        logger.debug("one to many sql is \n{}", sql);
        return sql;
    }

    public static String callById(Department entity) {
        String sql = new StringBuilder()
                .append("{call get_dept_by_id(")
                .append(getSubstituteValueString(COLUMN_ID))
                .append(")}").toString();
        logger.debug("sql is {}", sql);
        return sql;
    }

    public static String count() {
        String sql = new SQL()
                .SELECT("count(*)")
                .FROM(TABLE_NAME)
                .toString();
        logger.debug("sql is {}", sql);
        return sql;
    }

    public static String countByFields(Department entity) {
        String sql = new SQL() {{
            SELECT("count(*)");
            FROM(TABLE_NAME);
            if (entity.getDeleteFlag() != null) {
                WHERE(getColumnEqualsSubstituteValue(COLUMN_DELETE_FLAG));
            }
            if (entity.getId() != null) {
                WHERE(getColumnEqualsSubstituteValue(COLUMN_ID));
            }
            if (entity.getName() != null) {
                WHERE(getColumnEqualsSubstituteValue(COLUMN_NAME));
            }
            if (entity.getCreateTime() != null) {
                WHERE(getColumnEqualsSubstituteValue(COLUMN_CREATE_TIME));
            }
            if (entity.getModifyTime() != null) {
                WHERE(getColumnEqualsSubstituteValue(COLUMN_MODIFY_TIME));
            }
        }}.toString();
        logger.debug("sql is {}", sql);
        return sql;
    }

//    public static String countByMap(@Param("conditions") Map<String, Object> conditions) {
//        String sql = new SQL() {{
//            SELECT("count(*)");
//            FROM(TABLE_NAME);
//            for (String key : conditions.keySet()) {
//                WHERE(getColumnEqualsSubstituteValue(key, conditions.get(key).toString()));
//            }
//        }}.toString();
//        logger.debug("sql is {}", sql);
//        return sql;
//    }

    public static String findAllByFields(Department entity) {
        String sql = new SQL() {{
            SELECT("*");
            FROM(TABLE_NAME);
            if (entity.getDeleteFlag() != null) {
                WHERE(getColumnEqualsSubstituteValue(COLUMN_DELETE_FLAG));
            }
            if (entity.getId() != null) {
                WHERE(getColumnEqualsSubstituteValue(COLUMN_ID));
            }
            if (entity.getName() != null) {
                WHERE(getColumnEqualsSubstituteValue(COLUMN_NAME));
            }
            if (entity.getCreateTime() != null) {
                WHERE(getColumnEqualsSubstituteValue(COLUMN_CREATE_TIME));
            }
            if (entity.getModifyTime() != null) {
                WHERE(getColumnEqualsSubstituteValue(COLUMN_MODIFY_TIME));
            }
        }}.toString();
        logger.debug("sql is {}", sql);
        return sql;
    }

    public static String findPage(@Param("page") Page page, @Param("entity") Department entity) {
        String sql = new SQL() {{
            SELECT("*");
            FROM(TABLE_NAME);
            if (entity.getDeleteFlag() != null) {
                WHERE(getColumnEqualsSubstituteValue(COLUMN_DELETE_FLAG));
            }
            if (entity.getName() != null) {
                WHERE(getColumnLikesSubstituteValue(COLUMN_NAME));
            }
            if (entity.getCreateTime() != null) {
                WHERE(getColumnEqualsSubstituteValue(COLUMN_CREATE_TIME));
            }
            if (entity.getModifyTime() != null) {
                WHERE(getColumnEqualsSubstituteValue(COLUMN_MODIFY_TIME));
            }
            if (page != null) {
                if (page.getOrderBy() != null) {
                    ORDER_BY(page.getOrderBy());
                }
                if (page.getStart() != null && page.getPageSize() != null) {
                    LIMIT(new StringBuilder().append(page.getStart()).append(",").append(page.getPageSize()).toString());
                }
            }
        }}.toString();
        logger.debug("sql is {}", sql);
        return sql;
    }

//    public static String findAllByMap(@Param("conditions") Map<String, Object> conditions) {
//        String sql = new SQL() {{
//            SELECT("*");
//            FROM(TABLE_NAME);
//            for (String key : conditions.keySet()) {
//                WHERE(getColumnEqualsSubstituteValue(key));
//            }
//        }}.toString();
//        logger.debug("sql is {}", sql);
//        return sql;
//    }

    public static String execInsertSql(String sql) {
        logger.debug("sql is {}", sql);
        return sql;
    }

    public static String execDeleteSql(String sql) {
        logger.debug("sql is {}", sql);
        return sql;
    }

    public static String execUpdateSql(String sql) {
        logger.debug("sql is {}", sql);
        return sql;
    }

    public static String execSelectSql(String sql) {
        logger.debug("sql is {}", sql);
        return sql;
    }
}
