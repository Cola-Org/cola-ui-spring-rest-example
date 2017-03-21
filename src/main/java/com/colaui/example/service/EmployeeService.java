package com.colaui.example.service;

import java.util.List;
import java.util.Map;

import com.colaui.example.model.Employee;
import com.colaui.provider.Page;

public interface EmployeeService {
    Employee find(int id);

    void save(Employee employee);

    void update(Employee employee);

    void delete(int id);

    List<Employee> find(int from, int limit);

    Page<Employee> getPage(int pageSize, int pageNo, Map<String, Object> contain);

    List<Employee> getAll();

    Map<String,Object> checkEmail(String email);
}
