package com.taogen.demo.springbootcrud.module.user.controller;

import com.taogen.demo.springbootcrud.core.web.controller.AbstractRestController;
import com.taogen.demo.springbootcrud.core.web.dto.DataPage;
import com.taogen.demo.springbootcrud.core.web.model.GenericResponseModel;
import com.taogen.demo.springbootcrud.core.web.model.ResponseModel;
import com.taogen.demo.springbootcrud.core.web.vo.QueryPage;
import com.taogen.demo.springbootcrud.module.user.entity.User;
import com.taogen.demo.springbootcrud.module.user.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.Serializable;
import java.util.List;

/**
 * @author Taogen
 */
@RestController
@RequestMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserRestController extends AbstractRestController<UserService, User> {

    @Override
    @PostMapping(value = "/pages", consumes = MediaType.APPLICATION_JSON_VALUE)
    public GenericResponseModel<DataPage<List<User>>> findPage(HttpServletRequest request,
                                                              @Valid @RequestBody QueryPage<User> queryPage) {
        return super.findPage(request, queryPage);
    }

    @Override
    @PostMapping(value = "/searches", consumes = MediaType.APPLICATION_JSON_VALUE)
    public GenericResponseModel<List<User>> findAll(HttpServletRequest request,
                                                    @RequestBody User user) {
        return super.findAll(request, user);
    }

    @Override
    @GetMapping("/{id}")
    public GenericResponseModel<User> get(HttpServletRequest request,
                                          @PathVariable("id") Serializable id) {
        return super.get(request, id);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseModel delete(HttpServletRequest request,
                                       @PathVariable("id") Serializable id) {
        return super.delete(request, id);
    }

    @Override
    @DeleteMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseModel deleteAll(HttpServletRequest request,
                                   @RequestBody String value) {
        return super.deleteAll(request, value);
    }


    @Override
    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public GenericResponseModel save(HttpServletRequest request,
                                     @Valid @RequestBody User User) {
        return super.save(request, User);
    }

    @Override
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseModel update(HttpServletRequest request,
                                       @PathVariable("id") Serializable id,
                                       @Valid @RequestBody User User) {
        return super.update(request, id, User);
    }
}
