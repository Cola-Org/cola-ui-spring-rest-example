package com.colaui.example.dao;

import com.colaui.example.model.Dept;
import com.colaui.example.model.Employee;
import com.colaui.hibernate.HibernateDao;
import org.springframework.stereotype.Repository;

@Repository
public class DeptDao extends HibernateDao<Dept, Integer> {
}
