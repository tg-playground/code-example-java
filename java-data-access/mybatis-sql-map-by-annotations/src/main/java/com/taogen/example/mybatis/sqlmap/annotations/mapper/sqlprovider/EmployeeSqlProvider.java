package com.taogen.example.mybatis.sqlmap.annotations.mapper.sqlprovider;

import com.taogen.example.mybatis.sqlmap.annotations.entity.Department;
import com.taogen.example.mybatis.sqlmap.annotations.entity.Employee;
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
public class EmployeeSqlProvider {
    private static final Logger logger = LogManager.getLogger();
    private static final String TABLE_NAME = "t_employee";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_DELETE_FLAG = "delete_flag";
    private static final String COLUMN_CREATE_TIME = "create_time";
    private static final String COLUMN_MODIFY_TIME = "modify_time";
    private static final String COLUMN_NICKNAME = "nickname";
    private static final String COLUMN_AGE = "age";
    private static final Map<String, String> COLUMN_MAP_TO_FIELD = Collections.unmodifiableMap(new HashMap<String, String>() {
        {
            put("id", "id");
            put("name", "name");
            put("delete_flag", "deleteFlag");
            put("create_time", "createTime");
            put("modify_time", "modifyTime");
            put("nickname", "nickname");
            put("age", "age");
        }
    });
    private static final String ONE_TO_ONE_COLUMN_LIST = new StringBuilder()
            .append("a.id, a.name, a.nickname,a.age,a.delete_flag as 'deleteFlag', a.create_time as 'createTime', a.modify_time as 'modifyTime', ")
            .append(" b.id as 'department.id', b.name as 'department.name', b.delete_flag as 'department.deleteFlag', b.create_time as 'department.createTime', b.modify_time as 'department.modifyTime' ")
            .toString();
    private BaseSqlProvider baseSqlProvider = new BaseSqlProvider(TABLE_NAME, COLUMN_MAP_TO_FIELD);

    public String saveSelective(final Employee entity) {
        String sql = new SQL() {{
            INSERT_INTO(TABLE_NAME);
            if (entity.getId() != null) {
                VALUES(COLUMN_ID, BaseSqlProvider.getSubstituteValueString(COLUMN_ID));
            }
            if (entity.getName() != null) {
                VALUES(COLUMN_NAME, BaseSqlProvider.getSubstituteValueString(COLUMN_NAME));
            }
            if (entity.getNickname() != null) {
                VALUES(COLUMN_NICKNAME, BaseSqlProvider.getSubstituteValueString(COLUMN_NICKNAME));
            }
            if (entity.getAge() != null) {
                VALUES(COLUMN_AGE, BaseSqlProvider.getSubstituteValueString(COLUMN_AGE));
            }
        }}.toString();
        logger.debug("sql is {}", sql);
        return sql;
    }


    public String deleteById(Employee entity) {
        return baseSqlProvider.deleteById(entity);
    }

    public String deleteLogically(Employee entity) {
        return baseSqlProvider.deleteLogically(entity);
    }

//    public static String deleteAll(@Param("entities") Collection<Employee> entities) {
//        String sql = new SQL() {{
//            DELETE_FROM(TABLE_NAME);
//            for (Employee department : entities) {
//                WHERE(getColumnEqualsDirectValue(COLUMN_ID, department.getId()));
//            }
//        }}.toString();
//        return sql;
//    }

//    public static String deleteAllLogically(@Param("entities") Collection<Employee> entities) {
//        String sql = new SQL() {{
//            UPDATE(TABLE_NAME);
//            SET(getColumnEqualsDirectValue(COLUMN_DELETE_FLAG, 1));
//            for (Employee department : entities) {
//                WHERE(getColumnEqualsDirectValue(COLUMN_ID, department.getId().toString()));
//            }
//        }}.toString();
//        return sql;
//    }

    public String deleteAllByFields(Employee entity) {
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
            if (entity.getNickname() != null) {
                WHERE(baseSqlProvider.getColumnEqualsSubstituteValue(COLUMN_NICKNAME));
            }
            if (entity.getAge() != null) {
                WHERE(baseSqlProvider.getColumnEqualsSubstituteValue(COLUMN_AGE));
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

    public String updateSelective(Employee entity) {
        String sql = new SQL() {{
            UPDATE(TABLE_NAME);
            SET(BaseSqlProvider.getColumnEqualsDirectValue(COLUMN_MODIFY_TIME, "NOW()"));
            if (entity.getName() != null) {
                SET(baseSqlProvider.getColumnEqualsSubstituteValue(COLUMN_NAME));
            }
            if (entity.getDeleteFlag() != null) {
                SET(baseSqlProvider.getColumnEqualsSubstituteValue(COLUMN_DELETE_FLAG));
            }
            if (entity.getNickname() != null) {
                SET(baseSqlProvider.getColumnEqualsSubstituteValue(COLUMN_NICKNAME));
            }
            if (entity.getAge() != null) {
                SET(baseSqlProvider.getColumnEqualsSubstituteValue(COLUMN_AGE));
            }
            if (entity.getDepartment() != null && entity.getDepartment().getId() != null) {
                SET("dept_id=#{department.id,jdbcType=INTEGER}");
            }
            WHERE(baseSqlProvider.getColumnEqualsSubstituteValue(COLUMN_ID));
        }}.toString();
        logger.debug("sql is {}", sql);
        return sql;
    }

//    public static String updateAllFieldsByMap(@Param("entity") Employee entity, @Param("conditions") Map<String, Object> conditions) {
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

    public String getById(Employee entity) {
        String sql = new SQL()
                .SELECT(ONE_TO_ONE_COLUMN_LIST)
                .FROM(TABLE_NAME + " as a")
                .LEFT_OUTER_JOIN("t_department b ON a.dept_id = b.id")
                .WHERE("a.id=#{id,jdbcType=INTEGER}")
                .toString();
        logger.debug("sql is \n{}", sql);
        return sql;
    }

    public String getOneToOneDepartment(Department entity) {
        String sql = new SQL()
                .SELECT("*")
                .FROM("t_department")
                .WHERE(baseSqlProvider.getColumnEqualsSubstituteValue(COLUMN_ID)).toString();

        logger.debug("getOneToOneDepartment sql is \n{}", sql);
        return sql;
    }

    public String callById(Employee entity) {
        return baseSqlProvider.callById(entity);
    }

    public String count() {
        return baseSqlProvider.count();
    }

    public String countByFields(Employee entity) {
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
            if (entity.getNickname() != null) {
                WHERE(baseSqlProvider.getColumnEqualsSubstituteValue(COLUMN_NICKNAME));
            }
            if (entity.getAge() != null) {
                WHERE(baseSqlProvider.getColumnEqualsSubstituteValue(COLUMN_AGE));
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

    public String findAllByFields(Employee entity) {
        String sql = new SQL() {{
            SELECT(ONE_TO_ONE_COLUMN_LIST);
            FROM(TABLE_NAME + " as a");
            LEFT_OUTER_JOIN("t_department b ON a.dept_id = b.id");
            if (entity.getDeleteFlag() != null) {
                WHERE(baseSqlProvider.getColumnEqualsSubstituteValue(COLUMN_DELETE_FLAG));
            }
            if (entity.getId() != null) {
                WHERE("a." + baseSqlProvider.getColumnEqualsSubstituteValue(COLUMN_ID));
            }
            if (entity.getName() != null) {
                WHERE("a." + baseSqlProvider.getColumnEqualsSubstituteValue(COLUMN_NAME));
            }
            if (entity.getCreateTime() != null) {
                WHERE("a." + baseSqlProvider.getColumnEqualsSubstituteValue(COLUMN_CREATE_TIME));
            }
            if (entity.getModifyTime() != null) {
                WHERE("a." + baseSqlProvider.getColumnEqualsSubstituteValue(COLUMN_MODIFY_TIME));
            }
            if (entity.getNickname() != null) {
                WHERE("a." + baseSqlProvider.getColumnEqualsSubstituteValue(COLUMN_NICKNAME));
            }
            if (entity.getAge() != null) {
                WHERE("a." + baseSqlProvider.getColumnEqualsSubstituteValue(COLUMN_AGE));
            }
        }}.toString();
        logger.debug("sql is {}", sql);
        return sql;
    }

    public String findPage(@Param("page") Page page, @Param("entity") Employee entity) {
        String sql = new SQL() {{
            SELECT(ONE_TO_ONE_COLUMN_LIST);
            FROM(TABLE_NAME + " as a");
            LEFT_OUTER_JOIN("t_department b ON a.dept_id = b.id");
            if (entity.getDeleteFlag() != null) {
                WHERE(baseSqlProvider.getColumnEqualsSubstituteValue("a." + COLUMN_DELETE_FLAG, "entity." + COLUMN_DELETE_FLAG));
            }
            if (entity.getName() != null) {
                WHERE(baseSqlProvider.getColumnLikesSubstituteValue("a." + COLUMN_NAME, "entity." + COLUMN_NAME));
            }
            if (entity.getCreateTime() != null) {
                WHERE(baseSqlProvider.getColumnEqualsSubstituteValue("a." + COLUMN_CREATE_TIME, "entity." + COLUMN_CREATE_TIME));
            }
            if (entity.getModifyTime() != null) {
                WHERE(baseSqlProvider.getColumnEqualsSubstituteValue("a." + COLUMN_MODIFY_TIME, "entity." + COLUMN_MODIFY_TIME));
            }
            if (entity.getNickname() != null) {
                WHERE(baseSqlProvider.getColumnLikesSubstituteValue("a." + COLUMN_NICKNAME, "entity." + COLUMN_NICKNAME));
            }
            if (entity.getAge() != null) {
                WHERE(baseSqlProvider.getColumnEqualsSubstituteValue("a." + COLUMN_AGE), "entity." + COLUMN_AGE);
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
