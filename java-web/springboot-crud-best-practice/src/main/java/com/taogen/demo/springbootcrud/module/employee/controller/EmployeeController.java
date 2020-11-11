package com.taogen.demo.springbootcrud.module.employee.controller;

import com.taogen.demo.springbootcrud.core.web.controller.AbstractController;
import com.taogen.demo.springbootcrud.core.web.model.GenericResponseModel;
import com.taogen.demo.springbootcrud.core.web.model.ResponseModel;
import com.taogen.demo.springbootcrud.core.web.vo.QueryPage;
import com.taogen.demo.springbootcrud.module.employee.entity.Employee;
import com.taogen.demo.springbootcrud.module.employee.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.List;

/**
 * @author Taogen
 */
@RestController
@RequestMapping("/employee")
public class EmployeeController extends AbstractController<EmployeeService, Employee> {

    @PostMapping("/findList")
    @Override
    public GenericResponseModel<List<Employee>> findList(HttpServletRequest request,
                                                         @ModelAttribute("page") QueryPage<Employee> queryPage) {
        return super.findList(request, queryPage);
    }

    @GetMapping("/get")
    @Override
    public GenericResponseModel<Employee> get(HttpServletRequest request,
                                              @RequestParam(value = "id", required = true) Serializable id) {
        return super.get(request, id);
    }

    @GetMapping("/delete")
    @Override
    public ResponseModel delete(HttpServletRequest request,
                                @RequestParam(value = "id", required = true) Serializable id) {
        return super.delete(request, id);
    }

    @PostMapping("/deleteAll")
    @Override
    public ResponseModel deleteAll(HttpServletRequest request,
                                   @RequestParam(value = "ids", required = true) List<Serializable> ids) {
        return super.deleteAll(request, ids);
    }

    @PostMapping("/save")
    @Override
    public ResponseModel save(HttpServletRequest request,
                              @ModelAttribute("entity") Employee entity) {
        return super.save(request, entity);
    }

    @PostMapping("/update")
    @Override
    public ResponseModel update(HttpServletRequest request,
                                @ModelAttribute("entity") Employee entity) {
        return super.update(request, entity);
    }
}
