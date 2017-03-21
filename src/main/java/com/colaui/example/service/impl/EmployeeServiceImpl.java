package com.colaui.example.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.colaui.example.dao.EmployeeDao;
import com.colaui.example.model.Employee;
import com.colaui.example.service.EmployeeService;
import com.colaui.provider.Page;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeDao employeeDao;

    public Employee find(int id) {
        return employeeDao.get(id);
    }

    public void save(Employee employee) {
        employeeDao.save(employee);
    }

    public void update(Employee employee) {
        employeeDao.update(employee);
    }

    public void delete(int id) {
        employeeDao.delete(id);
    }

    public List<Employee> find(int from, int limit) {
        return employeeDao.find(from, limit);
    }

    public Page<Employee> getPage(int pageSize, int pageNo, Map<String, Object> param) {
        String contain = (String) param.get("contain");
        Integer companyId = (Integer) param.get("companyId");
        Integer deptId = (Integer) param.get("deptId");
        Criteria criteria = employeeDao.createCriteria();
        if (StringUtils.isNotEmpty(contain)) {
            Criterion lastRest = Restrictions.like("name", contain, MatchMode.ANYWHERE);
            Criterion firstRest = Restrictions.like("email", contain, MatchMode.ANYWHERE);
            criteria.add(Restrictions.or(lastRest, firstRest));
        }
        if (companyId != null && companyId > 0) {
            criteria.add(Restrictions.eq("companyId", companyId));
        }
        if (deptId != null && deptId > 0) {
            criteria.add(Restrictions.eq("deptId", deptId));
        }
        criteria.addOrder(Order.asc("id"));
        return employeeDao.getPage(pageSize, pageNo, criteria);
    }

    @Override
    public List<Employee> getAll() {
        return employeeDao.getAll();
    }

    @Override
    public Map<String, Object> checkEmail(String email) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        StringBuffer sql = new StringBuffer("from Employee where email=?");
        List<Employee> result = employeeDao.find(sql.toString(), new Object[]{email});
        resultMap.put("type", result.size() > 0 ? true : false);
        return resultMap;
    }

}
