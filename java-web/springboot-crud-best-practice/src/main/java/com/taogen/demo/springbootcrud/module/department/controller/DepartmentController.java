package com.taogen.demo.springbootcrud.module.department.controller;

import com.taogen.demo.springbootcrud.common.controller.AbstractController;
import com.taogen.demo.springbootcrud.common.vo.GenericResponseModel;
import com.taogen.demo.springbootcrud.common.vo.Page;
import com.taogen.demo.springbootcrud.common.vo.ResponseModel;
import com.taogen.demo.springbootcrud.module.department.dao.DepartmentMapper;
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

    @PostMapping("/findPage")
    @Override
    public GenericResponseModel<List<Department>> findPage(HttpServletRequest request,
                                                           @ModelAttribute("entity") Department entity,
                                                           @ModelAttribute("page") Page page) {
        return super.findPage(request, entity, page);
    }

    @PostMapping("/findAll")
    @Override
    public GenericResponseModel<List<Department>> findAll(HttpServletRequest request,
                                                          @ModelAttribute("entity") Department entity) {
        return super.findAll(request, entity);
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
