package com.taogen.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.taogen.demo.domain.User;
import com.taogen.demo.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;

/**
 * Controller
 *
 * @author Taogen
 * @date 2022-04-13
 */
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/user")
public class UserController {

    private final IUserService iUserService;

    /**
     * 查询列表
     */
    @GetMapping("/list")
    public Page<User> list(User user, Page page) {
        LambdaQueryWrapper<User> lqw = Wrappers.lambdaQuery(user);
        Page<User> list = iUserService.page(page, lqw);
        return list;
    }


    /**
     * 获取详细信息
     */
    @GetMapping(value = "/{id}")
    public User getInfo(@PathVariable("id") Long id) {
        return iUserService.getEntity(id);
    }

    /**
     * 新增
     */
    @PostMapping
    public boolean add(@Valid @RequestBody User user) {
        return iUserService.saveEntity(user);
    }

    /**
     * 修改
     */
    @PutMapping
    public boolean edit(@Valid @RequestBody User user) {
        return iUserService.updateEntity(user);
    }

    /**
     * 删除
     */
    @DeleteMapping("/{ids}")
    public boolean remove(@PathVariable Long[] ids) {
        return iUserService.deleteByIds(Arrays.asList(ids));
    }
}
