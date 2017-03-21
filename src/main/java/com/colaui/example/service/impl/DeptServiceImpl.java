package com.colaui.example.service.impl;

import com.colaui.example.dao.DeptDao;
import com.colaui.example.model.Dept;
import com.colaui.example.service.DeptService;
import com.colaui.provider.Page;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptDao deptDao;

    public Dept find(int id) {
        return deptDao.get(id);
    }

    public void save(Dept dept) {
        deptDao.save(dept);
    }

    public void update(Dept dept) {
        deptDao.update(dept);
    }

    public void delete(int id) {
        deptDao.delete(id);
    }

    public List<Dept> find(int from, int limit) {
        return deptDao.find(from, limit);
    }

    public Page<Dept> getPage(int pageSize, int pageNo, Map<String, Object> param) {
        String contain = (String) param.get("contain");
        Integer companyId = (Integer) param.get("companyId");
        Criteria criteria = deptDao.createCriteria();
        if (StringUtils.isNotEmpty(contain)) {
            Criterion lastRest = Restrictions.like("id", contain, MatchMode.ANYWHERE);
            Criterion firstRest = Restrictions.like("name", contain, MatchMode.ANYWHERE);
            criteria.add(Restrictions.or(lastRest, firstRest));
        }
        if (companyId != null && companyId > 0) {
            criteria.add(Restrictions.eq("companyId", companyId));
        }
        criteria.addOrder(Order.asc("id"));
        return deptDao.getPage(pageSize, pageNo, criteria);
    }

    @Override
    public List<Dept> getAll() {
        return deptDao.getAll();
    }

}
