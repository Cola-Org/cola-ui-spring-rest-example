package com.colaui.example.dao;

import org.springframework.stereotype.Repository;

import com.colaui.example.model.Employee;
import com.colaui.hibernate.HibernateDao;

@Repository
public class EmployeeDao extends HibernateDao<Employee, Integer> {
}
