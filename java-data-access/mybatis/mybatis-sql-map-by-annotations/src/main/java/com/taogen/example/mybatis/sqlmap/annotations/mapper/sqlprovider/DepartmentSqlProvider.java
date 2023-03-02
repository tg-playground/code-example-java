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
    private static final Map<String, String> COLUMN_MAP_TO_FIELD = Collections.unmodifiableMap(new HashMap<String, String>() {
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
    private BaseSqlProvider baseSqlProvider = new BaseSqlProvider(TABLE_NAME, COLUMN_MAP_TO_FIELD);

    public String saveSelective(final Department entity) {
        String sql = new SQL() {{
            INSERT_INTO(TABLE_NAME);
            if (entity.getId() != null) {
                VALUES(COLUMN_ID, BaseSqlProvider.getSubstituteValueString(COLUMN_ID));
            }
            if (entity.getName() != null) {
                VALUES(COLUMN_NAME, BaseSqlProvider.getSubstituteValueString(COLUMN_NAME));
            }
        }}.toString();
        logger.debug("sql is {}", sql);
        return sql;
    }

    public String deleteById(Department entity) {
        return baseSqlProvider.deleteById(entity);
    }

    public String deleteLogically(Department entity) {
        return baseSqlProvider.deleteLogically(entity);
    }

//    public String deleteAll(@Param("entities") Collection<Department> entities) {
//        String sql = new SQL() {{
//            DELETE_FROM(TABLE_NAME);
//            for (Department department : entities) {
//                WHERE(getColumnEqualsDirectValue(COLUMN_ID, department.getId()));
//            }
//        }}.toString();
//        return sql;
//    }

//    public String deleteAllLogically(@Param("entities") Collection<Department> entities) {
//        String sql = new SQL() {{
//            UPDATE(TABLE_NAME);
//            SET(getColumnEqualsDirectValue(COLUMN_DELETE_FLAG, 1));
//            for (Department department : entities) {
//                WHERE(getColumnEqualsDirectValue(COLUMN_ID, department.getId().toString()));
//            }
//        }}.toString();
//        return sql;
//    }

    public String deleteAllByFields(Department entity) {
        logger.debug("deleteAllByFields");
        String sql = new SQL() {{
            DELETE_FROM(TABLE_NAME);
            WHERE(BaseSqlProvider.getColumnEqualsDirectValue(COLUMN_DELETE_FLAG, 0));
            if (entity.getId() != null) {
                WHERE(baseSqlProvider.getColumnEqualsSubstituteValue(COLUMN_ID));
            }
            if (entity.getName() != null) {
                WHERE(baseSqlProvider.getColumnEqualsSubstituteValue(COLUMN_NAME));
            }
            if (entity.getCreateTime() != null) {
                WHERE(baseSqlProvider.getColumnEqualsSubstituteValue(COLUMN_CREATE_TIME));
            }
        }}.toString();
        logger.debug("sql is {}", sql);
        return sql;
    }

//    public String deleteAllByMap(@Param("conditions") Map<String, Object> conditions) {
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

    public String updateSelective(Department entity) {
        String sql = new SQL() {{
            UPDATE(TABLE_NAME);
            SET(BaseSqlProvider.getColumnEqualsDirectValue(COLUMN_MODIFY_TIME, "NOW()"));
            if (entity.getName() != null) {
                SET(baseSqlProvider.getColumnEqualsSubstituteValue(COLUMN_NAME));
            }
            if (entity.getDeleteFlag() != null) {
                SET(baseSqlProvider.getColumnEqualsSubstituteValue(COLUMN_DELETE_FLAG));
            }
            WHERE(baseSqlProvider.getColumnEqualsSubstituteValue(COLUMN_ID));
        }}.toString();
        logger.debug("sql is {}", sql);
        return sql;
    }

//    public String updateAllFieldsByMap(@Param("entity") Department entity, @Param("conditions") Map<String, Object> conditions) {
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
        return baseSqlProvider.getById(entity);
    }

    public String getOneToManyEmployee(Integer deptId) {
        String sql = new SQL()
                .SELECT("*")
                .FROM("t_employee")
                .WHERE(baseSqlProvider.getColumnEqualsSubstituteValue(COLUMN_DEPT_ID))
                .toString();
        logger.debug("one to many sql is \n{}", sql);
        return sql;
    }

    public String callById(Department entity) {
        return baseSqlProvider.callById(entity);
    }

    public String count() {
        return baseSqlProvider.count();
    }

    public String countByFields(Department entity) {
        String sql = new SQL() {{
            SELECT("count(*)");
            FROM(TABLE_NAME);
            if (entity.getDeleteFlag() != null) {
                WHERE(baseSqlProvider.getColumnEqualsSubstituteValue(COLUMN_DELETE_FLAG));
            }
            if (entity.getId() != null) {
                WHERE(baseSqlProvider.getColumnEqualsSubstituteValue(COLUMN_ID));
            }
            if (entity.getName() != null) {
                WHERE(baseSqlProvider.getColumnEqualsSubstituteValue(COLUMN_NAME));
            }
            if (entity.getCreateTime() != null) {
                WHERE(baseSqlProvider.getColumnEqualsSubstituteValue(COLUMN_CREATE_TIME));
            }
            if (entity.getModifyTime() != null) {
                WHERE(baseSqlProvider.getColumnEqualsSubstituteValue(COLUMN_MODIFY_TIME));
            }
        }}.toString();
        logger.debug("sql is {}", sql);
        return sql;
    }

//    public String countByMap(@Param("conditions") Map<String, Object> conditions) {
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

    public String findAllByFields(Department entity) {
        String sql = new SQL() {{
            SELECT("*");
            FROM(TABLE_NAME);
            if (entity.getDeleteFlag() != null) {
                WHERE(baseSqlProvider.getColumnEqualsSubstituteValue(COLUMN_DELETE_FLAG));
            }
            if (entity.getId() != null) {
                WHERE(baseSqlProvider.getColumnEqualsSubstituteValue(COLUMN_ID));
            }
            if (entity.getName() != null) {
                WHERE(baseSqlProvider.getColumnEqualsSubstituteValue(COLUMN_NAME));
            }
            if (entity.getCreateTime() != null) {
                WHERE(baseSqlProvider.getColumnEqualsSubstituteValue(COLUMN_CREATE_TIME));
            }
            if (entity.getModifyTime() != null) {
                WHERE(baseSqlProvider.getColumnEqualsSubstituteValue(COLUMN_MODIFY_TIME));
            }
        }}.toString();
        logger.debug("sql is {}", sql);
        return sql;
    }

    public String findPage(@Param("page") Page page, @Param("entity") Department entity) {
        String sql = new SQL() {{
            SELECT("*");
            FROM(TABLE_NAME);
            if (entity.getDeleteFlag() != null) {
                WHERE(baseSqlProvider.getColumnEqualsSubstituteValue(COLUMN_DELETE_FLAG, "entity." + COLUMN_DELETE_FLAG));
            }
            if (entity.getName() != null) {
                WHERE(baseSqlProvider.getColumnLikesSubstituteValue(COLUMN_NAME, "entity." + COLUMN_NAME));
            }
            if (entity.getCreateTime() != null) {
                WHERE(baseSqlProvider.getColumnEqualsSubstituteValue(COLUMN_CREATE_TIME, "entity." + COLUMN_CREATE_TIME));
            }
            if (entity.getModifyTime() != null) {
                WHERE(baseSqlProvider.getColumnEqualsSubstituteValue(COLUMN_MODIFY_TIME, "entity." + COLUMN_MODIFY_TIME));
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

//    public String findAllByMap(@Param("conditions") Map<String, Object> conditions) {
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

    public String execInsertSql(String sql) {
        return baseSqlProvider.execInsertSql(sql);
    }

    public String execDeleteSql(String sql) {
        return baseSqlProvider.execDeleteSql(sql);
    }

    public String execUpdateSql(String sql) {
        return baseSqlProvider.execUpdateSql(sql);
    }

    public String execSelectSql(String sql) {
        return baseSqlProvider.execSelectSql(sql);
    }
}
