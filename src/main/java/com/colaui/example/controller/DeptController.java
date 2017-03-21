package com.colaui.example.controller;

import com.colaui.example.model.Dept;
import com.colaui.example.model.Employee;
import com.colaui.example.service.DeptService;
import com.colaui.example.service.EmployeeService;
import com.colaui.provider.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("dept")
public class DeptController {
    @Autowired
    private DeptService deptService;
    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Dept> getAll() {
        return deptService.getAll();
    }

    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public Page<Dept> paging(@RequestParam int pageSize,
                             @RequestParam int pageNo, @RequestParam(required = false) String contain) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("contain", contain);
        return deptService.getPage(pageSize, pageNo, param);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public void save(@RequestBody Dept employee) {
        deptService.save(employee);
    }

    @RequestMapping(value = "/{id}/", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") int id) {
        deptService.delete(id);
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT, produces = "application/json; charset=utf-8")
    public void update(@RequestBody Dept employee) {
        deptService.update(employee);
    }

    @RequestMapping(value = "/{id}/", method = RequestMethod.GET)
    public Dept find(@PathVariable("id") int id) {
        return deptService.find(id);
    }

    @RequestMapping(value = "/find/{from}/{limit}", method = RequestMethod.GET)
    public List<Dept> find(@PathVariable("from") int from,
                           @PathVariable("limit") int limit) {
        return deptService.find(from, limit);
    }

    @RequestMapping(value = "/{id}/employees", method = RequestMethod.GET)
    public List<Employee> findEmployee(@PathVariable("id") int id) {
        return (List<Employee>) deptService.find(id).getEmployees();
    }

    @RequestMapping(value = "/{id}/find/employees", method = RequestMethod.GET)
    public Page<Employee> pageEmployee(@PathVariable("id") int id, @RequestParam int pageSize,
                                       @RequestParam int pageNo) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("deptId", id);
        return  employeeService.getPage(pageSize, pageNo, param);
    }
}
