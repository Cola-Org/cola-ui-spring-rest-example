package com.colaui.example.service.impl;

import com.colaui.example.dao.DictionaryCodeDao;
import com.colaui.example.model.DictionaryCode;
import com.colaui.example.service.DictionaryCodeService;
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
public class DictionaryCodeServiceImpl implements DictionaryCodeService {
	@Autowired
	private DictionaryCodeDao dictionaryCodeDao;
	
	public DictionaryCode find(int id) {
		return dictionaryCodeDao.get(id);
	}

	public void save(DictionaryCode dictionaryCode) {
		dictionaryCodeDao.save(dictionaryCode);
	}

	public void update(DictionaryCode dictionaryCode) {
		dictionaryCodeDao.update(dictionaryCode);
	}

	public void delete(int id) {
		dictionaryCodeDao.delete(id);
	}

	public List<DictionaryCode> find(int from, int limit) {
		return dictionaryCodeDao.find(from, limit);
	}

	public Page<DictionaryCode> getPage(int pageSize, int pageNo, String contain) {
		Criteria criteria = dictionaryCodeDao.createCriteria();
		if (StringUtils.isNotEmpty(contain)) {
			Criterion lastRest= Restrictions.like("lastName", contain, MatchMode.ANYWHERE);
			Criterion firstRest= Restrictions.like("firstName", contain, MatchMode.ANYWHERE);
			criteria.add(Restrictions.or(lastRest, firstRest));
		}
		return dictionaryCodeDao.getPage(pageSize, pageNo, criteria);
	}

	@Override
	public List<DictionaryCode> getDictByCodes(Collection<Integer> codes) {
		Criteria criteria = dictionaryCodeDao.createCriteria();
		if (codes.size() > 0) {
			criteria.add(Restrictions.in("code", codes));
		}
		return dictionaryCodeDao.find(criteria);
	}

}
