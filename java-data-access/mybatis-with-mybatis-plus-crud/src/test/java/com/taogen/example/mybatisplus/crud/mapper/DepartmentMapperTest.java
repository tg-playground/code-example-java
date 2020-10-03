package com.taogen.example.mybatisplus.crud.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.taogen.example.mybatisplus.crud.entity.Department;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DepartmentMapperTest {

    private static final Logger logger = LogManager.getLogger();

    @Autowired
    private DepartmentMapper departmentMapper;

    @Test
    public void insert() {
        Department department = new Department("test insert");
        assertTrue(departmentMapper.insert(department) >= 1);
        logger.info("insert department: {}", department);
    }

    @Test
    public void delete() {
        Department department = new Department("test delete");
        assertTrue(departmentMapper.insert(department) >= 1);
        logger.info("insert department: {}", department);

        UpdateWrapper wrapper = new UpdateWrapper();
        wrapper.eq("id", department.getId());
        departmentMapper.delete(wrapper);
    }

    @Test
    public void deleteBatchIds() {
        Department department = new Department("test deleteBatchIds");
        assertTrue(departmentMapper.insert(department) >= 1);
        logger.info("insert department: {}", department);

        Department department2 = new Department("test deleteById");
        assertTrue(departmentMapper.insert(department2) >= 1);
        logger.info("insert department: {}", department2);

        assertTrue(departmentMapper.deleteBatchIds(Arrays.asList(department, department2)
                .stream().map(d -> d.getId()).collect(Collectors.toList())) >= 2);
    }

    @Test
    public void deleteById() {
        Department department = new Department("test deleteById");
        assertTrue(departmentMapper.insert(department) >= 1);
        logger.info("insert department: {}", department);

        assertTrue(departmentMapper.deleteById(department.getId()) >= 1);
        // after deleted, select is null
        assertNull(departmentMapper.selectById(department.getId()));
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id", department.getId());
        assertEquals(0, departmentMapper.selectList(queryWrapper).size());
    }

    @Test
    public void deleteByMap() {
        String name = "test deleteByMap";
        Department department = new Department(name);
        assertTrue(departmentMapper.insert(department) >= 1);
        logger.info("insert department: {}", department);

        Map<String, Object> params = new HashMap<>();
        params.put("name", name);
        assertTrue(departmentMapper.deleteByMap(params) >= 1);
    }

    @Test
    public void update() {
        String name = "test update";
        Department department = new Department(name);
        assertTrue(departmentMapper.insert(department) >= 1);
        logger.info("insert department: {}", department);

        department = departmentMapper.selectById(department.getId());

        department.setName("updated");
        department.setModifyTime(LocalDateTime.now());
        UpdateWrapper wrapper = new UpdateWrapper();
        wrapper.eq("name", name);
        assertTrue(departmentMapper.update(department, wrapper) >= 1);
    }

    @Test
    public void updateById() {

        String name = "test updateById";
        Department department = new Department(name);
        assertTrue(departmentMapper.insert(department) >= 1);
        logger.info("insert department: {}", department);

        department.setName("updateById");
        assertTrue(departmentMapper.updateById(department) >= 1);
    }

    @Test
    public void selectById() {

        String name = "test selectById";
        Department department = new Department(name);
        assertTrue(departmentMapper.insert(department) >= 1);
        logger.info("insert department: {}", department);

        department = departmentMapper.selectById(department.getId());
        assertNotNull(department);
        assertEquals(name, department.getName());
    }

    @Test
    public void selectOne() {

        String name = "test selectOne";
        Department department = new Department(name);
        assertTrue(departmentMapper.insert(department) >= 1);
        logger.info("insert department: {}", department);

        QueryWrapper<Department> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", name);
        // It may throw TooManyResultsException: Expected one result (or null) to be returned by selectOne(), but found: 2
        department = departmentMapper.selectOne(queryWrapper);
        assertNotNull(department);
        assertEquals(name, department.getName());
    }

    @Test
    public void selectBatchIds() {
        String name = "test selectBatchIds";
        Department department = new Department(name);
        assertTrue(departmentMapper.insert(department) >= 1);
        logger.info("insert department: {}", department);

        Department department2 = new Department(name);
        assertTrue(departmentMapper.insert(department2) >= 1);
        logger.info("insert department: {}", department2);

        List<Department> departmentList = departmentMapper.selectBatchIds(Arrays.asList(department, department2)
                .stream()
                .map(d -> d.getId())
                .collect(Collectors.toList()));
        assertNotNull(departmentList);
        assertTrue(departmentList.size() >= 2);
        assertTrue(departmentList.stream().allMatch(d -> d.getName().equals(name)));
    }

    @Test
    public void selectList() {
        String name = "test selectList";
        Department department = new Department(name);
        assertTrue(departmentMapper.insert(department) >= 1);
        logger.info("insert department: {}", department);

        QueryWrapper<Department> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", name);
        List<Department> departmentList = departmentMapper.selectList(queryWrapper);
        assertNotNull(departmentList);
        assertTrue(departmentList.stream().allMatch(d -> d.getName().equals(name)));
    }

    @Test
    public void selectByMap() {
        String name = "test selectByMap";
        Department department = new Department(name);
        assertTrue(departmentMapper.insert(department) >= 1);
        logger.info("insert department: {}", department);

        Department department2 = new Department(name);
        assertTrue(departmentMapper.insert(department2) >= 1);
        logger.info("insert department: {}", department2);

        Map<String, Object> params = new HashMap<>();
        params.put("name", name);
        List<Department> departmentList = departmentMapper.selectByMap(params);
        assertNotNull(departmentList);
        assertTrue(departmentList.size() >= 2);
        assertTrue(departmentList.stream().allMatch(d -> d.getName().equals(name)));
    }

    @Test
    public void selectMaps() {
    }

    @Test
    public void selectObjs() {
    }

    @Test
    public void selectPage() {
        String name = "test selectPage";
        Department department = new Department(name);
        assertTrue(departmentMapper.insert(department) >= 1);
        logger.info("insert department: {}", department);

        Department department2 = new Department(name);
        assertTrue(departmentMapper.insert(department2) >= 1);
        logger.info("insert department: {}", department2);

        QueryWrapper<Department> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", name);
        IPage<Department> page = new Page<>();
        int pageNo = 1;
        page.setCurrent(pageNo);
        int pageSize = 10;
        page.setSize(pageSize);
        IPage<Department> resultPage = departmentMapper.selectPage(page, queryWrapper);
        List<Department> records = resultPage.getRecords();
        logger.info("select records are {}", records);
        logger.info("result total is : {}", resultPage.getTotal());
        assertNotNull(records);
        assertTrue(records.size() >= 2);
        assertTrue(records.stream().allMatch(d -> d.getName().equals(name)));
    }

    @Test
    public void selectMapsPage() {

    }

    @Test
    public void selectCount() {

        String name = "test selectCount";
        Department department = new Department(name);
        assertTrue(departmentMapper.insert(department) >= 1);
        logger.info("insert department: {}", department);

        QueryWrapper queryWrapper = new QueryWrapper();
        Integer count = departmentMapper.selectCount(queryWrapper);
        assertNotNull(count);
        assertTrue(count > 0);
        logger.info("count is {}", count);
    }
}