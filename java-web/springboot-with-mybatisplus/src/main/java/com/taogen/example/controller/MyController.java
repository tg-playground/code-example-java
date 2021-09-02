package com.taogen.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * @author Taogen
 */
@Controller
public class MyController {

    @ResponseBody
    @RequestMapping("/sayHello")
    public String sayHello() {
        return "hello by My Controller!" + new Date();
    }

    @RequestMapping("/indexPage")
    public String indexPage() {
        return "index";
    }
}
