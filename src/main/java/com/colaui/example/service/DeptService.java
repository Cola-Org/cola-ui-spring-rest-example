package com.colaui.example.service;

import com.colaui.example.model.Dept;
import com.colaui.provider.Page;

import java.util.List;
import java.util.Map;

public interface DeptService {
    Dept find(int id);

    void save(Dept dept);

    void update(Dept dept);

    void delete(int id);

    List<Dept> find(int from, int limit);

    Page<Dept> getPage(int pageSize, int pageNo, Map<String, Object> param);

    List<Dept> getAll();
}
