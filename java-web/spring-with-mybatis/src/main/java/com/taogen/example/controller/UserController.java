package com.taogen.example.controller;

import com.taogen.example.entity.User;
import com.taogen.example.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Taogen
 */
@Controller
@RequestMapping(value = "/user")
@Log4j2
public class UserController {

    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping(value = "/listUsers", method = {RequestMethod.POST, RequestMethod.GET})
    public Object listAllUsers(HttpServletRequest request, HttpServletResponse response) {
        List<User> userList = userService.listAllUsers();
        Map<String, Object> map = new HashMap<>(0);
        map.put("users", userList);
        log.info("users: {}", userList);
        return map;
    }

    @RequestMapping("list")
    public String listAllUsers(HttpServletRequest request) {
        List<User> userList = userService.listAllUsers();
        request.setAttribute("userList", userList);
        return "web/user_list";
    }

    @ResponseBody
    @RequestMapping(value = "/getUser", method = {RequestMethod.POST, RequestMethod.GET})
    public Object getUser(Integer id) {
        User user = userService.getUser(id);
        Map<String, Object> map = new HashMap<>(0);
        map.put("user", user);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/addUser", method = {RequestMethod.POST, RequestMethod.GET})
    public Object insertUser(User user) {
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            userService.saveUser(user);
            result.put("success", true);
            result.put("user", user);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            result.put("success", false);
            return result;
        }
    }

    @ResponseBody
    @RequestMapping(value = "/editUser", method = {RequestMethod.POST, RequestMethod.GET})
    public Object editUser(User user) {
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            userService.updateUser(user);
            result.put("success", true);
            result.put("user", user);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            result.put("success", false);
            return result;
        }
    }

    @ResponseBody
    @RequestMapping(value = "/deleteUser", method = {RequestMethod.POST, RequestMethod.GET})
    public Object deleteUser(Integer id) {
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            userService.deleteUser(id);
            result.put("success", true);
            result.put("userid", id);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            result.put("success", false);
            return result;
        }
    }
}
