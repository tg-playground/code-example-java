package com.taogen.springcloud;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author taogen
 */
@Controller
@RequestMapping("/")
public class MyController {

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/unauthorized")
    @ResponseBody
    public String unauthorized() {
        return "unauthorized";
    }

}
