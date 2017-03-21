package com.colaui.example.service.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.colaui.example.dao.ProductDao;
import com.colaui.example.model.Product;
import com.colaui.example.service.ProductService;
import com.colaui.provider.Page;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao productDao;

    @Override
    public Product find(long id) {
        return productDao.get(id);
    }

    @Override
    public void save(Product product) {
        productDao.save(product);
    }

    @Override
    public void update(Product product) {
        productDao.update(product);
    }

    @Override
    public void delete(long id) {
        productDao.delete(id);
    }

    @Override
    public List<Product> find(int from, int limit) {

        return productDao.find(from, limit);
    }

    @Override
    public Page<Product> queryOfCategoryId(int pageSize, int pageNo,
                                           long categoryId) {
        Criteria criteria = productDao.createCriteria();
        Criterion lastRest = Restrictions.eq("categoryId", categoryId);
        criteria.add(lastRest);
        return productDao.getPage(pageSize, pageNo, criteria);
    }

}
