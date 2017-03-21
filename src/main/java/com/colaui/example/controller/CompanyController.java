package com.colaui.example.controller;

import com.colaui.example.model.Company;
import com.colaui.example.model.Dept;
import com.colaui.example.model.Employee;
import com.colaui.example.service.CompanyService;
import com.colaui.example.service.DeptService;
import com.colaui.example.service.EmployeeService;
import com.colaui.provider.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("company")
public class CompanyController {
    @Autowired
    private CompanyService companyService;
    @Autowired
    private DeptService deptService;
    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Company> getAll() {
        return companyService.getAll();
    }

    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public Page<Company> paging(@RequestParam int pageSize,
                                @RequestParam int pageNo, @RequestParam(required = false) String contain) {
        return companyService.getPage(pageSize, pageNo, contain);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public void save(@RequestBody Company Company) {
        companyService.save(Company);
    }

    @RequestMapping(value = "/{id}/", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") int id) {
        companyService.delete(id);
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT, produces = "application/json; charset=utf-8")
    public void update(@RequestBody Company Company) {
        companyService.update(Company);
    }

    @RequestMapping(value = "/{id}/", method = RequestMethod.GET)
    public Company find(@PathVariable("id") int id) {
        return companyService.find(id);
    }

    @RequestMapping(value = "/find/{from}/{limit}", method = RequestMethod.GET)
    public List<Company> find(@PathVariable("from") int from,
                              @PathVariable("limit") int limit) {
        return companyService.find(from, limit);
    }

    @RequestMapping(value = "/{id}/depts", method = RequestMethod.GET)
    public List<Dept> findDept(@PathVariable("id") int id) {
        return (List<Dept>) companyService.find(id).getDepts();
    }

    @RequestMapping(value = "/{id}/find/depts", method = RequestMethod.GET)
    public Page<Dept> pageDept(@PathVariable("id") int id, @RequestParam int pageSize, @RequestParam int pageNo) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("id", id);
        return deptService.getPage(pageSize, pageNo, param);
    }

    @RequestMapping(value = "/{id}/employees", method = RequestMethod.GET)
    public List<Employee> findEmployee(@PathVariable("id") int id) {
        return (List<Employee>) companyService.find(id).getEmployees();
    }

    @RequestMapping(value = "/{id}/find/employees", method = RequestMethod.GET)
    public Page<Employee> pageEmployee(@PathVariable("id") int id, @RequestParam int pageSize,
                                       @RequestParam int pageNo) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("companyId", id);
        return employeeService.getPage(pageSize, pageNo, param);
    }
}
