package com.taogen.example.service.impl;

import com.taogen.example.entity.Employee;
import com.taogen.example.entity.User;
import com.taogen.example.mapper.master.UserMapper;
import com.taogen.example.mapper.slave.EmployeeMapper;
import com.taogen.example.service.DistributedTransactionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author Taogen
 */
@Service
public class DistributedTransactionServiceImpl implements DistributedTransactionService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private EmployeeMapper employeeMapper;


    @Transactional(transactionManager = "xatx",
            propagation = Propagation.REQUIRED, rollbackFor = RuntimeException.class)
    @Override
    public void testDistributedTransaction() {
        User user = new User();
        user.setUserName("Tom_" + new Date());
        user.setUserEmail("tom@123.com");
        user.setUserPassword("123456");
        int result = userMapper.insert(user);

        Employee employee = new Employee();
        employee.setName("Tom_" + new Date());
        employee.setNickname("Tom");
        employee.setAge(18);
        int result2 = employeeMapper.insert(employee);

        throw new RuntimeException("test distributed transaction");
    }
}
