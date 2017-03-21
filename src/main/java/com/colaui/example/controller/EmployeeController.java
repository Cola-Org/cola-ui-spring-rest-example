package com.colaui.example.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.colaui.example.model.Employee;
import com.colaui.example.service.EmployeeService;
import com.colaui.provider.Page;

@RestController
@RequestMapping("employee")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Employee> getAll() {
        return employeeService.getAll();
    }

    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public Page<Employee> paging(@RequestParam int pageSize,
                                 @RequestParam int pageNo, @RequestParam(required = false) String contain) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("contain", contain);
        return employeeService.getPage(pageSize, pageNo, param);
    }

    @RequestMapping(value = "/{id}/", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") int id) {
        employeeService.delete(id);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public void save(@RequestBody Employee employee) {
        employeeService.save(employee);
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT, produces = "application/json; charset=utf-8")
    public void update(@RequestBody Employee employee) {
        employeeService.update(employee);
    }

    @RequestMapping(value = "/{id}/", method = RequestMethod.GET)
    public Employee find(@PathVariable("id") int id) {
        return employeeService.find(id);
    }

    @RequestMapping(value = "/find/{from}/{limit}", method = RequestMethod.GET)
    public List<Employee> find(@PathVariable("from") int from,
                               @PathVariable("limit") int limit) {
        return employeeService.find(from, limit);
    }

    @RequestMapping(value = "/exists", method = RequestMethod.GET)
    public Map<String, Object> checkEmail(@RequestParam String email) {
        return employeeService.checkEmail(email);
    }


}
