package com.colaui.example.service;

import com.colaui.example.model.Company;
import com.colaui.provider.Page;

import java.util.List;

public interface CompanyService {
    Company find(int id);

    void save(Company Company);

    void update(Company Company);

    void delete(int id);

    List<Company> find(int from, int limit);

    Page<Company> getPage(int pageSize, int pageNo, String contain);

    List<Company> getAll();

}
