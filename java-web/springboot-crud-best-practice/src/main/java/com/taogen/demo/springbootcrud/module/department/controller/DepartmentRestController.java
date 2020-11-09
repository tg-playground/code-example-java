package com.taogen.demo.springbootcrud.module.department.controller;

import com.taogen.demo.springbootcrud.common.controller.AbstractRestController;
import com.taogen.demo.springbootcrud.common.vo.GenericResponseModel;
import com.taogen.demo.springbootcrud.common.vo.Page;
import com.taogen.demo.springbootcrud.module.department.entity.Department;
import com.taogen.demo.springbootcrud.module.department.service.DepartmentService;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.List;

/**
 * @author Taogen
 */
@RestController
@RequestMapping(value = "/departments", produces = MediaType.APPLICATION_JSON_VALUE)
public class DepartmentRestController extends AbstractRestController<DepartmentService, Department> {

    @Autowired
    public void init(DepartmentService service) {
        this.service = service;
        this.type = Department.class;
        this.logger = LogManager.getLogger();
    }

    @Override
    @GetMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public GenericResponseModel<List<Department>> findList(HttpServletRequest request,
                                                           @RequestBody Page<Department> page) {
        return super.findList(request, page);
    }

    @Override
    @GetMapping("/{id}")
    public GenericResponseModel<Department> get(HttpServletRequest request,
                                                @PathVariable("id") Serializable id) {
        return super.get(request, id);
    }

    @Override
    @DeleteMapping("/{id}")
    public GenericResponseModel delete(HttpServletRequest request, @PathVariable("id") Serializable id) {
        return super.delete(request, id);
    }

    @Override
    @DeleteMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public GenericResponseModel deleteAll(HttpServletRequest request, @RequestBody String value) {
        return super.deleteAll(request, value);
    }


    @Override
    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public GenericResponseModel save(HttpServletRequest request, @RequestBody Department department) {
        return super.save(request, department);
    }

    @Override
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public GenericResponseModel update(HttpServletRequest request, @PathVariable("id") Serializable id, @RequestBody Department department) {
        return super.update(request, id, department);
    }
}
