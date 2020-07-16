package com.taogen.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyController
{
    @ResponseBody
    @RequestMapping("/sayHello")
    public String sayHello()
    {
        return "hello by My Controller!";
    }

    @RequestMapping("/indexPage")
    public String indexPage()
    {
        return "index";
    }
}
