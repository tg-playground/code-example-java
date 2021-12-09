package com.taogen.example;

import com.taogen.example.entity.Employee;
import com.taogen.example.entity.User;
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
        try {
            myTransactionService.testOverDataSourceTransactions();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return new Date().toString();
    }
}
