package com.taogen.demo.springbootcrud.module.department.controller;

import com.taogen.demo.springbootcrud.core.web.controller.AbstractRestController;
import com.taogen.demo.springbootcrud.core.web.model.GenericResponseModel;
import com.taogen.demo.springbootcrud.core.web.vo.QueryPage;
import com.taogen.demo.springbootcrud.module.department.entity.Department;
import com.taogen.demo.springbootcrud.module.department.service.DepartmentService;
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
@RequestMapping(value = "/departments", produces = MediaType.APPLICATION_JSON_VALUE)
public class DepartmentRestController extends AbstractRestController<DepartmentService, Department> {

    /**
     * Using GET request with requestBody query for REST findPage API
     * @param request
     * @param queryPage
     * @return
     */
    @Override
    @GetMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public GenericResponseModel<List<Department>> findList(HttpServletRequest request,
                                                           @RequestBody QueryPage<Department> queryPage) {
        return super.findList(request, queryPage);
    }

    @Override
    @GetMapping("/{id}")
    public GenericResponseModel<Department> get(HttpServletRequest request,
                                                @PathVariable("id") Serializable id) {
        return super.get(request, id);
    }

    @Override
    @DeleteMapping("/{id}")
    public GenericResponseModel delete(HttpServletRequest request,
                                       @PathVariable("id") Serializable id) {
        return super.delete(request, id);
    }

    @Override
    @DeleteMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public GenericResponseModel deleteAll(HttpServletRequest request,
                                          @RequestBody String value) {
        return super.deleteAll(request, value);
    }


    @Override
    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public GenericResponseModel save(HttpServletRequest request,
                                     @Valid @RequestBody Department department) {
        return super.save(request, department);
    }

    @Override
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public GenericResponseModel update(HttpServletRequest request,
                                       @PathVariable("id") Serializable id,
                                       @Valid @RequestBody Department department) {
        return super.update(request, id, department);
    }
}
