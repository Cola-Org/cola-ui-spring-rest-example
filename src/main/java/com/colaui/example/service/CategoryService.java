package com.colaui.example.service;

import java.util.List;

import com.colaui.example.model.Category;
import com.colaui.provider.Page;

public interface CategoryService {
    Category find(long id);

    void save(Category category);

    void update(Category category);

    void delete(long id);

    List<Category> find(int from, int limit);

    Page<Category> getPage(int pageSize, int pageNo, String contain);
}
