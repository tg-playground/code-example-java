package com.taogen.demo.springbootcrud.module.department.controller;

import com.taogen.demo.springbootcrud.core.web.controller.AbstractController;
import com.taogen.demo.springbootcrud.core.web.dto.GenericResponseModel;
import com.taogen.demo.springbootcrud.core.web.vo.QueryPage;
import com.taogen.demo.springbootcrud.core.web.dto.ResponseModel;
import com.taogen.demo.springbootcrud.module.department.entity.Department;
import com.taogen.demo.springbootcrud.module.department.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.List;

/**
 * @author Taogen
 */
@RestController
@RequestMapping("/department")
public class DepartmentController extends AbstractController<DepartmentService, Department> {

    @Autowired
    @Override
    public void setService(DepartmentService service) {
        this.service = service;
    }

    @PostMapping("/findList")
    @Override
    public GenericResponseModel<List<Department>> findList(HttpServletRequest request,
                                                           @ModelAttribute("page") QueryPage queryPage) {
        return super.findList(request, queryPage);
    }

    @GetMapping("/get")
    @Override
    public GenericResponseModel<Department> get(HttpServletRequest request,
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
                              @ModelAttribute("entity") Department entity) {
        return super.save(request, entity);
    }

    @PostMapping("/update")
    @Override
    public ResponseModel update(HttpServletRequest request,
                                @ModelAttribute("entity") Department entity) {
        return super.update(request, entity);
    }
}
