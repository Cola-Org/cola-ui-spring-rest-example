package com.colaui.example.dao;

import org.springframework.stereotype.Repository;

import com.colaui.example.model.Product;
import com.colaui.hibernate.HibernateDao;
@Repository
public class ProductDao extends HibernateDao<Product, Long> {

}
