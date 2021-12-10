package com.taogen.example;

import com.taogen.example.entity.Employee;
import com.taogen.example.entity.User;
import com.taogen.example.service.DistributedTransactionService;
import com.taogen.example.service.EmployeeService;
import com.taogen.example.service.MyTransactionService;
import com.taogen.example.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Hello world!
 */
@SpringBootApplication
@RestController
@RequestMapping
@Log4j2
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Autowired
    private UserService userService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private MyTransactionService myTransactionService;

    @Autowired
    private DistributedTransactionService distributedTransactionService;

    @GetMapping(value = "hello", produces = "text/plain;charset=UTF-8")
    public String hello() {
        return new StringBuilder()
                .append("Hello World!\n")
                .append("当前时间：")
                .append(new Date())
                .toString();
    }

    @GetMapping(value = "singleDataSource", produces = "text/plain;charset=UTF-8")
    public String singleDataSource() {
        List<User> userList = userService.queryList();
        return new StringBuilder()
                .append(new Date())
                .append("\r\n")
                .append(userList)
                .toString();
    }

    @GetMapping(value = "multipleDataSources", produces = "text/plain;charset=UTF-8")
    public String multiDataSources() {
        List<User> userList = userService.queryList();
        log.debug("userList: {}", userList);
        List<Employee> employeeList = employeeService.queryList();
        log.debug("employeeList: {}", employeeList);
        return new StringBuilder()
                .append(new Date())
                .append("\r\n")
                .append("\r\n")
                .append(userList)
                .append("\r\n")
                .append("\r\n")
                .append(employeeList)
                .toString();
    }

    @GetMapping(value = "multipleDataSourcesTransaction", produces = "text/plain;charset=UTF-8")
    public String multipleDataSourcesTransaction() {
        Exception exception = null;
        try {
            myTransactionService.testOverDataSourceTransactions();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            exception = e;
        }
        StringBuilder result = new StringBuilder()
                .append(new Date());
        if (exception != null) {
            result.append("\r\n")
                    .append(exception.getMessage())
                    .append("\r\n")
                    .append(Arrays.asList(exception.getStackTrace()));
        }
        return result.toString();

    }

    @GetMapping(value = "atomikosDistributedTransaction", produces = "text/plain;charset=UTF-8")
    public String multipleDataSourcesTransaction2() {
        Exception exception = null;
        try {
            distributedTransactionService.testDistributedTransaction();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            exception = e;
        }
        StringBuilder result = new StringBuilder()
                .append(new Date());
        if (exception != null) {
            result.append("\r\n")
                    .append(exception.getMessage())
                    .append("\r\n")
                    .append(Arrays.asList(exception.getStackTrace()));
        }
        return result.toString();
    }
}
