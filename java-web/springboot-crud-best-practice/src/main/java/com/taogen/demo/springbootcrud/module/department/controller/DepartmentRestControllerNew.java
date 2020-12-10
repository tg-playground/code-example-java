package com.taogen.demo.springbootcrud.module.department.controller;

import com.taogen.demo.springbootcrud.core.web.controller.AbstractRestControllerNew;
import com.taogen.demo.springbootcrud.core.web.dto.DataPage;
import com.taogen.demo.springbootcrud.core.web.vo.QueryPage;
import com.taogen.demo.springbootcrud.module.department.entity.Department;
import com.taogen.demo.springbootcrud.module.department.service.DepartmentService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.Serializable;
import java.util.List;

/**
 * @author Taogen
 */
@RestController
@RequestMapping(value = "/departmentsNew", produces = MediaType.APPLICATION_JSON_VALUE)
public class DepartmentRestControllerNew extends AbstractRestControllerNew<DepartmentService, Department> {
    @Override
    @PostMapping(value = "/pages", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DataPage<List<Department>>> findPage(HttpServletRequest request,
                                                               @Valid @RequestBody QueryPage<Department> queryPage) {
        return super.findPage(request, queryPage);
    }

    @Override
    @PostMapping(value = "/searches", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Department>> findAll(HttpServletRequest request,
                                                    @RequestBody Department department) {
        return super.findAll(request, department);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Department> get(HttpServletRequest request,
                                          @PathVariable("id") Serializable id) {
        return super.get(request, id);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity delete(HttpServletRequest request,
                                 @PathVariable("id") Serializable id) {
        return super.delete(request, id);
    }

    @Override
    @DeleteMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteAll(HttpServletRequest request,
                                    @RequestBody String value) {
        return super.deleteAll(request, value);
    }


    @Override
    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity save(HttpServletRequest request,
                               @Valid @RequestBody Department department) {
        return super.save(request, department);
    }

    @Override
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity update(HttpServletRequest request,
                                 @PathVariable("id") Serializable id,
                                 @Valid @RequestBody Department department) {
        return super.update(request, id, department);
    }
}
