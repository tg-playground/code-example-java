package com.taogen.demo.springbootcrud.module.employee.controller;

import com.taogen.demo.springbootcrud.core.web.controller.AbstractRestController;
import com.taogen.demo.springbootcrud.core.web.model.GenericResponseModel;
import com.taogen.demo.springbootcrud.core.web.vo.QueryPage;
import com.taogen.demo.springbootcrud.module.employee.entity.Employee;
import com.taogen.demo.springbootcrud.module.employee.service.EmployeeService;
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
@RequestMapping("/employees")
public class EmployeeRestController extends AbstractRestController<EmployeeService, Employee> {


    /**
     * Resolve ModelAttribute nest subclass is null
     * @return
     */
    @ModelAttribute
    public QueryPage<Employee> setupModelAttribute() {
        QueryPage<Employee> queryPage = new QueryPage<>();
        queryPage.setEntity(new Employee());
        return queryPage;
    }

    @Override
    @GetMapping(value = "")
    public GenericResponseModel<List<Employee>> findList(HttpServletRequest request,
                                                         @ModelAttribute("queryPage") QueryPage<Employee> queryPage) {
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
