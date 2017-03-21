package com.colaui.example.dao;

import com.colaui.example.model.DictionaryDetail;
import com.colaui.hibernate.HibernateDao;
import org.springframework.stereotype.Repository;

@Repository
public class DictionaryDetailsDao extends HibernateDao<DictionaryDetail, Integer> {
}
