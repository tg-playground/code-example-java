package com.taogen.demo.springbootcrud.module.employee.controller;

import com.taogen.demo.springbootcrud.core.web.controller.AbstractRestController;
import com.taogen.demo.springbootcrud.core.web.model.GenericResponseModel;
import com.taogen.demo.springbootcrud.core.web.vo.QueryPage;
import com.taogen.demo.springbootcrud.module.employee.entity.Employee;
import com.taogen.demo.springbootcrud.module.employee.service.EmployeeService;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Taogen
 */
@RestController
@RequestMapping("/employees")
public class EmployeeRestController extends AbstractRestController<EmployeeService, Employee> {


    @Override
    @GetMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public GenericResponseModel<List<Employee>> findList(HttpServletRequest request,
                                                         @Validated @RequestBody QueryPage<Employee> queryPage) {
        return super.findList(request, queryPage);
    }

    @Override
    @GetMapping("/{id}")
    public GenericResponseModel<Employee> get(HttpServletRequest request,
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
                                     @Valid @RequestBody Employee employee) {
        return super.save(request, employee);
    }

    @Override
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public GenericResponseModel update(HttpServletRequest request,
                                       @PathVariable("id") Serializable id,
                                       @Valid @RequestBody Employee employee) {
        return super.update(request, id, employee);
    }
}
