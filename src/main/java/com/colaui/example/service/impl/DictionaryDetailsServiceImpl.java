package com.colaui.example.service.impl;

import com.colaui.example.dao.DictionaryDetailsDao;
import com.colaui.example.model.DictionaryDetail;
import com.colaui.example.service.DictionaryDetailsService;
import com.colaui.provider.Page;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DictionaryDetailsServiceImpl implements DictionaryDetailsService {
	@Autowired
	private DictionaryDetailsDao dictionaryDetailsDao;

	public DictionaryDetail find(int id) {
		return dictionaryDetailsDao.get(id);
	}

	public void save(DictionaryDetail employee) {
		dictionaryDetailsDao.save(employee);
	}

	public void update(DictionaryDetail employee) {
		dictionaryDetailsDao.update(employee);
	}

	public void delete(int id) {
		dictionaryDetailsDao.delete(id);
	}

	public List<DictionaryDetail> find(int from, int limit) {
		return dictionaryDetailsDao.find(from, limit);
	}

	public Page<DictionaryDetail> getPage(int pageSize, int pageNo, String contain) {
		Criteria criteria = dictionaryDetailsDao.createCriteria();
		if (StringUtils.isNotEmpty(contain)) {
			Criterion lastRest= Restrictions.like("lastName", contain, MatchMode.ANYWHERE);
			Criterion firstRest= Restrictions.like("firstName", contain, MatchMode.ANYWHERE);
			criteria.add(Restrictions.or(lastRest, firstRest));
		}
		return dictionaryDetailsDao.getPage(pageSize, pageNo, criteria);
	}

}
