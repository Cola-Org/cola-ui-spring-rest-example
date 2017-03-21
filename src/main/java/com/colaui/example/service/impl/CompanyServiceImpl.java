package com.colaui.example.service.impl;

import com.colaui.example.dao.CompanyDao;
import com.colaui.example.model.Company;
import com.colaui.example.model.Dept;
import com.colaui.example.service.CompanyService;
import com.colaui.provider.Page;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {
	@Autowired
	private CompanyDao companyDao;

	public Company find(int id) {
		return companyDao.get(id);
	}

	public void save(Company Company) {
		companyDao.save(Company);
	}

	public void update(Company Company) {
		companyDao.update(Company);
	}

	public void delete(int id) {
		companyDao.delete(id);
	}

	public List<Company> find(int from, int limit) {
		return companyDao.find(from, limit);
	}

	public Page<Company> getPage(int pageSize, int pageNo, String contain) {
		Criteria criteria = companyDao.createCriteria();
		if (StringUtils.isNotEmpty(contain)) {
			Criterion lastRest= Restrictions.like("lastName", contain, MatchMode.ANYWHERE);
			Criterion firstRest= Restrictions.like("firstName", contain, MatchMode.ANYWHERE);
			criteria.add(Restrictions.or(lastRest, firstRest));
		}
		return companyDao.getPage(pageSize, pageNo, criteria);
	}

	@Override
	public List<Company> getAll() {
		return companyDao.getAll();
	}

}
