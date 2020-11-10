package com.taogen.demo.springbootcrud;

import com.taogen.demo.springbootcrud.core.filehandling.properites.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/")
@SpringBootApplication
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
