package com.colaui.example.dao;

import com.colaui.example.model.Company;
import com.colaui.hibernate.HibernateDao;
import org.springframework.stereotype.Repository;

@Repository
public class CompanyDao extends HibernateDao<Company, Integer> {
}
