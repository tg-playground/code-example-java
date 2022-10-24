package com.taogen.demo.springjdbctemplate.jdbc;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author Taogen
 */
@Service
public interface JdbcCrudService {
    /**
     * @param sql
     * @param args
     * @param argTypes java.sql.Types
     * @return auto-generated id
     */
    long insert(String sql, Object[] args, int[] argTypes);

    /**
     *
     * @param sql
     * @param args
     * @param argTypes
     * @return affected rows
     */
    int update(String sql, Object[] args, int[] argTypes);

    /**
     *
     * @param sql
     * @param args
     * @param argTypes
     * @return affected rows
     */
    int delete(String sql, Object[] args, int[] argTypes);

    Map<String, Object> selectOne(String sql, Object[] args, int[] argTypes);

    List<Map<String, Object>> selectList(String sql, Object[] args, int[] argTypes);

    long selectCount(String sql, Object[] args, int[] argTypes);
}
