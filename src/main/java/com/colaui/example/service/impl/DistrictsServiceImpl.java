package com.colaui.example.service.impl;

import com.colaui.example.dao.DistrictsDao;
import com.colaui.example.model.Districts;
import com.colaui.example.service.DistrictsService;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DistrictsServiceImpl implements DistrictsService {
	private static final Integer PROVINCE_ = 1;

	@Autowired
	private DistrictsDao districtsDao;

	public Districts find(int id) {
		return districtsDao.get(id);
	}

	public void save(Districts districts) {
		districtsDao.save(districts);
	}

	public void update(Districts districts) {
		districtsDao.update(districts);
	}

	public void delete(int id) {
		districtsDao.delete(id);
	}

	public List<Districts> find(int from, int limit) {
		return districtsDao.find(from, limit);
	}

	public Page<Districts> getPage(int pageSize, int pageNo, String contain) {
		Criteria criteria = districtsDao.createCriteria();
		if (StringUtils.isNotEmpty(contain)) {
			Criterion lastRest= Restrictions.like("lastName", contain, MatchMode.ANYWHERE);
			Criterion firstRest= Restrictions.like("firstName", contain, MatchMode.ANYWHERE);
			criteria.add(Restrictions.or(lastRest, firstRest));
		}
		return districtsDao.getPage(pageSize, pageNo, criteria);
	}

	@Override
	public List<Districts> getAll() {
		return districtsDao.getAll();
	}

	@Override
	public List<Districts> getProvinces() {
		Criteria criteria = districtsDao.createCriteria();
		criteria.add(Restrictions.eq("level", PROVINCE_));
		criteria.addOrder(Order.asc("id"));
		return districtsDao.find(criteria);
	}

	@Override
	public List<Districts> getChildrenById(int parentId) {
		Map<String, Object> param = new HashMap<String, Object>();
		StringBuffer sql = new StringBuffer("from Districts where parentId=:parentId");
		param.put("parentId", parentId);
		return districtsDao.find(sql.toString(), param);
	}

}
