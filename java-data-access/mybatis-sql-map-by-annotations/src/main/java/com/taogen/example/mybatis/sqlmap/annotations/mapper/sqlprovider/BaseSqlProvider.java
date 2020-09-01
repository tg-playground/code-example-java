package com.taogen.example.mybatis.sqlmap.annotations.mapper.sqlprovider;

import com.taogen.example.mybatis.sqlmap.annotations.entity.BaseEntity;
import org.apache.ibatis.jdbc.SQL;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

/**
 * @author Taogen
 */
public class BaseSqlProvider {
    private static final Logger logger = LogManager.getLogger();
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_DELETE_FLAG = "delete_flag";
    private static final String COLUMN_CREATE_TIME = "create_time";
    private static final String COLUMN_MODIFY_TIME = "modify_time";
    private String tableName;
    private Map<String, String> columnFieldMap;


    public BaseSqlProvider(String tableName, Map<String, String> columnFieldMap) {
        this.tableName = tableName;
        this.columnFieldMap = columnFieldMap;
    }

    public static String getSubstituteValueString(String column) {
        return String.format("#{%s}", column);
    }

    public static String getColumnEqualsDirectValue(String key, Object value) {
        return new StringBuilder()
                .append(key)
                .append("=")
                .append(value)
                .toString();
    }

    public String getColumnEqualsSubstituteValue(String column) {
        return new StringBuilder()
                .append(column)
                .append("=")
                .append(getSubstituteValueString(columnFieldMap.get(column)))
                .toString();
    }

    public String getColumnEqualsSubstituteValue(String column, String value) {
        return new StringBuilder()
                .append(column)
                .append("=")
                .append(getSubstituteValueString(columnFieldMap.get(value)))
                .toString();
    }

    public String getColumnLikesSubstituteValue(String column) {
        return new StringBuilder()
                .append(column)
                .append(" LIKE CONCAT('%',")
                .append(getSubstituteValueString(column))
                .append(", '%')")
                .toString();
    }

    public String getColumnLikesSubstituteValue(String column, String value) {
        return new StringBuilder()
                .append(column)
                .append(" LIKE CONCAT('%',")
                .append(getSubstituteValueString(value))
                .append(", '%')")
                .toString();
    }

    /***************** Provider Begin *********************/


    public String deleteById(BaseEntity entity) {
        String sql = new SQL()
                .DELETE_FROM(tableName)
                .WHERE(getColumnEqualsSubstituteValue(COLUMN_ID))
                .toString();
        logger.debug("sql is {}", sql);
        return sql;
    }

    public String deleteLogically(BaseEntity entity) {
        String sql = new SQL()
                .UPDATE(tableName)
                .SET(getColumnEqualsDirectValue(COLUMN_DELETE_FLAG, 1))
                .WHERE(getColumnEqualsSubstituteValue(COLUMN_ID))
                .toString();
        logger.debug("sql is {}", sql);
        return sql;
    }

    public String getById(BaseEntity entity) {
        String sql = new SQL()
                .SELECT("*")
                .FROM(tableName)
                .WHERE(getColumnEqualsSubstituteValue(COLUMN_ID))
                .toString();
        logger.debug("sql is {}", sql);
        return sql;
    }

    public String callById(BaseEntity entity) {
        String sql = new StringBuilder()
                .append("{call get_dept_by_id(")
                .append(getSubstituteValueString(COLUMN_ID))
                .append(")}").toString();
        logger.debug("sql is {}", sql);
        return sql;
    }

    public String count() {
        String sql = new SQL()
                .SELECT("count(*)")
                .FROM(tableName)
                .toString();
        logger.debug("sql is {}", sql);
        return sql;
    }

    public String execInsertSql(String sql) {
        logger.debug("sql is {}", sql);
        return sql;
    }

    public String execDeleteSql(String sql) {
        logger.debug("sql is {}", sql);
        return sql;
    }

    public String execUpdateSql(String sql) {
        logger.debug("sql is {}", sql);
        return sql;
    }

    public String execSelectSql(String sql) {
        logger.debug("sql is {}", sql);
        return sql;
    }
}
