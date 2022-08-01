package com.taogen.springtest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyController
{
    @RequestMapping("/sayHello")
    @ResponseBody
    public String sayHello()
    {
        return "hello by MyController";
    }

    @RequestMapping("/toIndex")
    public String toIndexPage()
    {
        return "index";
    }

    @RequestMapping(value="/returnJson", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String returnJson()
    {
        return "{message:ok, user: { name : Tom }}";
    }

    @RequestMapping(value="/returnJson", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String returnJsonByPost(String name)
    {
        return "{message:ok, user: { name : "+name+"}}";
    }
}
