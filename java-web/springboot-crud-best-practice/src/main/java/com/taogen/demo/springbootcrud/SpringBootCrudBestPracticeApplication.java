package com.taogen.demo.springbootcrud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/")
@SpringBootApplication
@ComponentScan("com.taogen.demo.springbootcrud")
@MapperScan({"com.taogen.demo.springbootcrud.module.*.dao"})
public class SpringBootCrudBestPracticeApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootCrudBestPracticeApplication.class, args);
    }

    @GetMapping(value = "/hello", produces = {"application/json"})
    public String sayHello() {
        return new StringBuilder()
                .append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()))
                .append(" hello")
                .toString();
    }
}
