package com.taogen.demo.springjdbctemplate.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.jdbc.support.rowset.SqlRowSetMetaData;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Taogen
 */
@Service
public class JdbcCrudServiceImpl implements JdbcCrudService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public long insert(String sql, Object[] args, int[] argTypes) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection
                    .prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            if (args != null && args.length > 0) {
                for (int i = 0; i < args.length; i++) {
                    ps.setObject(i + 1, args[i], argTypes[i]);
                }
            }
            return ps;
        }, keyHolder);
        return keyHolder.getKey().longValue();
    }

    @Override
    public int update(String sql, Object[] args, int[] argTypes) {
        return jdbcTemplate.update(sql, args, argTypes);
    }

    @Override
    public int delete(String sql, Object[] args, int[] argTypes) {
        return jdbcTemplate.update(sql, args, argTypes);
    }

    @Override
    public Map<String, Object> selectOne(String sql, Object[] args, int[] argTypes) {
        return jdbcTemplate.queryForMap(sql, args, argTypes);
    }

    @Override
    public List<Map<String, Object>> selectList(String sql, Object[] args, int[] argTypes) {
        return jdbcTemplate.queryForList(sql, args, argTypes);
    }

    @Override
    public long selectCount(String sql, Object[] args, int[] argTypes) {
        Map<String, Object> resultMap = jdbcTemplate.queryForMap(sql, args, argTypes);
        return Long.valueOf(new ArrayList<>(resultMap.values()).get(0).toString());
    }

    @Override
    public List<String> getQueryLabels(String sql, Object[] args, int[] argTypes) {
        SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet(sql, args, argTypes);
        SqlRowSetMetaData metaData = sqlRowSet.getMetaData();
        return getQueryLabelsByMetaData(metaData);
    }

    private List<String> getQueryLabelsByMetaData(SqlRowSetMetaData sqlRowSetMetaData) {
        List<String> labels = new ArrayList<>();
        int columnNum = sqlRowSetMetaData.getColumnCount();
        for (int i = 0; i < columnNum; i++) {
            labels.add(sqlRowSetMetaData.getColumnLabel(i + 1));
        }
        return labels;
    }
    private List<String> getQueryLabelsByMetaData(ResultSetMetaData resultSetMetaData) throws SQLException, SQLException {
        List<String> labels = new ArrayList<>();
        int columnNum = resultSetMetaData.getColumnCount();
        for (int i = 0; i < columnNum; i++) {
            labels.add(resultSetMetaData.getColumnLabel(i + 1));
        }
        return labels;
    }
}
