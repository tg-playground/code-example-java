package com.taogen.springmvcdatahandle.controller;

import com.taogen.springmvcdatahandle.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class MyController
{
//    @InitBinder
//    protected void initBinder(WebDataBinder binder)
//    {
//        binder.setValidator(new FooValidator());
//    }

    @RequestMapping(value="/postUser")
    public String validateFormDisplayError(@Valid User user,  BindingResult bindingResult)
    {
        if (bindingResult.hasErrors()) {
            return "index";
        }
        return "success";
    }


}
