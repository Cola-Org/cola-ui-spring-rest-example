package com.colaui.example.dao;

import org.springframework.stereotype.Repository;

import com.colaui.example.model.Category;
import com.colaui.hibernate.HibernateDao;
@Repository
public class CategoryDao extends HibernateDao<Category, Long> {
}
