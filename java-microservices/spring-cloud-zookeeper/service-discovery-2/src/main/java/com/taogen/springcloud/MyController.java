package com.taogen.springcloud;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 *
 * @author taogen
 */
@Controller
@RestController
@RequestMapping("/")
public class MyController {
    @Value("${spring.application.name}")
    private String applicationName;

    @RequestMapping("/greeting")
    public String greeting() {
        return "Hello from " + applicationName + "!" + new Date();
    }
}
