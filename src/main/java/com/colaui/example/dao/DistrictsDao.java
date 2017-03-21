package com.colaui.example.dao;

import com.colaui.example.model.Districts;
import com.colaui.example.model.Employee;
import com.colaui.hibernate.HibernateDao;
import org.springframework.stereotype.Repository;

@Repository
public class DistrictsDao extends HibernateDao<Districts, Integer> {
}
