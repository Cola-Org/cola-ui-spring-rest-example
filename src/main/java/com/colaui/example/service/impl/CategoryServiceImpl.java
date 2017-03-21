package com.colaui.example.service.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.colaui.example.dao.CategoryDao;
import com.colaui.example.model.Category;
import com.colaui.example.service.CategoryService;
import com.colaui.provider.Page;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryDao categoryDao;

    @Override
    public Category find(long id) {
        return this.categoryDao.get(id);
    }

    @Override
    public void save(Category category) {
        categoryDao.save(category);
    }

    @Override
    public void update(Category category) {
        categoryDao.update(category);
    }

    @Override
    public void delete(long id) {
        categoryDao.delete(id);
    }

    @Override
    public List<Category> find(int from, int limit) {
        return categoryDao.find(from, limit);
    }

    @Override
    public Page<Category> getPage(int pageSize, int pageNo, String contain) {
        Criteria criteria = categoryDao.createCriteria();
        return categoryDao.getPage(pageSize, pageNo, criteria);
    }

}
