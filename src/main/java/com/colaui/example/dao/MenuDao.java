package com.colaui.example.dao;

import org.springframework.stereotype.Repository;

import com.colaui.example.model.Menu;
import com.colaui.hibernate.HibernateDao;
@Repository
public class MenuDao extends HibernateDao<Menu, Long> {

}
