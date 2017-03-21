package com.colaui.hibernate;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.transform.ResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;

import com.colaui.provider.Page;

public class HibernateDao<T, PK extends Serializable> {
	private static final Log logger = LogFactory.getLog(HibernateDao.class);

	@SuppressWarnings("rawtypes")
	private static final List EMPTY_UNMUTABLE_LIST = java.util.Collections.EMPTY_LIST;

	protected SessionFactory sessionFactory;
	protected Class<T> entityType = getEntityType();

	@Autowired(required = false)
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	protected SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public Session getSession() {
		return getSessionFactory().getCurrentSession();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected Class<T> getEntityType() {
		Class cl = getClass();
		Class<T> resultType = null;
		Type superType = cl.getGenericSuperclass();

		if (superType instanceof ParameterizedType) {
			Type[] paramTypes = ((ParameterizedType) superType)
					.getActualTypeArguments();
			if (paramTypes.length > 0) {
				resultType = (Class<T>) paramTypes[0];
			} else {
				logger.warn("Can not determine EntityType for class ["
						+ cl.getSimpleName() + "].");
			}
		} else {
			logger.warn("[" + cl.getSimpleName()
					+ "] is not a parameterized type.");
		}
		return resultType;
	}

	protected String getIdPropertyName() {
		ClassMetadata meta = getSessionFactory().getClassMetadata(entityType);
		return meta.getIdentifierPropertyName();
	}

	@SuppressWarnings("unchecked")
	public T get(PK id) {
		return (T) getSession().load(entityType, id);
	}

	public Criteria createCriteria() {
		return getSession().createCriteria(entityType);
	}

	public Criteria createCriteria(Criterion... criterions) {
		Criteria criteria = createCriteria();
		for (Criterion c : criterions) {
			criteria.add(c);
		}
		return criteria;
	}

	public Query createQuery(String hql, Object... parameters) {
		Query q = getSession().createQuery(hql);
		if (parameters != null) {
			for (int i = 0; i < parameters.length; ++i) {
				q.setParameter(i, parameters[i]);
			}
		}
		return q;
	}

	public Query createQuery(String queryString, Map<String, ?> parameters) {
		Query query = getSession().createQuery(queryString);
		if (parameters != null) {
			query.setProperties(parameters);
		}
		return query;
	}

	@SuppressWarnings("unchecked")
	public List<T> find(Criteria criteria) {
		return criteria.list();
	}

	@SuppressWarnings("rawtypes")
	protected long countCriteriaResult(Criteria criteria) {
		CriteriaHelper implHelper = new CriteriaHelper(criteria);
		long count = 0;
		List orderEntries = null;

		try {
			orderEntries = implHelper.getOrderEntries();
			implHelper.setOrderEntries(EMPTY_UNMUTABLE_LIST);

			Projection projection = implHelper.getProjection();
			ResultTransformer transformer = implHelper.getResultTransformer();

			count = ((Number) criteria.setProjection(Projections.rowCount())
					.uniqueResult()).longValue();
			criteria.setProjection(projection);
			if (projection == null) {
				criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
			}
			if (transformer != null) {
				criteria.setResultTransformer(transformer);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				implHelper.setOrderEntries(orderEntries);
			} catch (Exception e) {
				logger.warn(e, e);
			}
		}
		return count;
	}

	public List<T> find(Criterion... criterions) {
		return find(createCriteria(criterions));
	}

	public List<T> find(DetachedCriteria detachedCriteria) {
		Criteria criteria = detachedCriteria
				.getExecutableCriteria(getSession());
		return find(criteria);
	}

	public Page<T> getPage(int pageSize, int pageNo) {
		return this.getPage(pageSize, pageNo, createCriteria());
	}

	@SuppressWarnings("unchecked")
	public Page<T> getPage(int pageSize, int pageNo, Criteria criteria) {
		long totalCount = countCriteriaResult(criteria);
		Page<T> page = new Page<T>(pageSize, pageNo);
		page.set$entityCount((int) totalCount);
		setPageParameterToCriteria(criteria, page);
		page.set$data(criteria.list());
		return page;
	}

	public Page<T> getPage(int pageSize, int pageNo,
			DetachedCriteria detachedCriteria) {
		Criteria criteria = detachedCriteria
				.getExecutableCriteria(getSession());
		return getPage(pageSize, pageNo, criteria);
	}

	public Page<T> getPage(int pageSize, int pageNo, Criterion... criterions) {
		return getPage(pageSize, pageNo, createCriteria(criterions));
	}

	@SuppressWarnings("unchecked")
	public Page<T> getPage(int pageSize, int pageNo, String hql,
			Object... parameters) {

		Query q = createQuery(hql, parameters);
		long totalCount = countHqlResult(hql, parameters);
		Page<T> page = new Page<T>(pageSize, pageNo);
		page.set$entityCount((int) totalCount);
		setPageParameterToQuery(q, page);
		page.set$data(q.list());
		return page;
	}

	@SuppressWarnings("unchecked")
	public Page<T> getPage(int pageSize, int pageNo, String hql,
			Map<String, ?> parameters) {
		Page<T> page = new Page<T>(pageSize, pageNo);
		Query q = createQuery(hql, parameters);
		long totalCount = countHqlResult(hql, parameters);
		page.set$entityCount((int) totalCount);
		setPageParameterToQuery(q, page);
		page.set$data(q.list());
		return page;
	}

	public List<T> find(int from, int limit) {
		return this.find(from, limit, createCriteria());
	}

	@SuppressWarnings("unchecked")
	public List<T> find(int from, int limit, Criteria criteria) {
		setFromLimitToCriteria(criteria, from, limit);
		return criteria.list();
	}

	public List<T> find(int from, int limit, DetachedCriteria detachedCriteria) {
		Criteria criteria = detachedCriteria
				.getExecutableCriteria(getSession());
		return find(from, limit, criteria);
	}

	public List<T> find(int from, int limit, Criterion... criterions) {
		return find(from, limit, createCriteria(criterions));
	}

	@SuppressWarnings("unchecked")
	public List<T> find(int from, int limit, String hql, Object... parameters) {
		Query q = createQuery(hql, parameters);
		setFromLimitToQuery(q, from, limit);
		return q.list();
	}

	@SuppressWarnings("unchecked")
	public List<T> find(int from, int limit, String hql,
			Map<String, ?> parameters) {
		Query q = createQuery(hql, parameters);
		setFromLimitToQuery(q, from, limit);
		return q.list();
	}

	protected long countHqlResult(String hql, Object... parameters) {
		String countHql = generateCountHql(hql);
		return ((Number) findUnique(countHql, parameters)).longValue();
	}

	protected long countHqlResult(String hql, Map<String, ?> parameters) {
		String countHql = generateCountHql(hql);
		return ((Number) findUnique(countHql, parameters)).longValue();
	}

	private String generateCountHql(String hql) {
		hql = "from " + StringUtils.substringAfter(hql, "from");
		hql = StringUtils.substringBefore(hql, "order by");
		String countHql = "select count(*) " + hql;
		return countHql;
	}

	protected Criteria setFromLimitToCriteria(Criteria c, int from, int limit) {
		c.setFirstResult(from);
		c.setMaxResults(limit - from);
		return c;
	}

	protected Criteria setPageParameterToCriteria(Criteria c, Page<T> page) {
		c.setFirstResult(page.getFrom());
		c.setMaxResults(page.getPageSize());
		return c;
	}

	protected Query setFromLimitToQuery(Query q, int from, int limit) {
		q.setFirstResult(from);
		q.setMaxResults(limit - from);
		return q;
	}

	protected Query setPageParameterToQuery(Query q, Page<T> page) {
		q.setFirstResult(page.getFrom());
		q.setMaxResults(page.getPageSize());
		return q;
	}

	public List<T> get(Collection<PK> ids) {
		return find(new Criterion[] { Restrictions.in(getIdPropertyName(), ids) });
	}

	@SuppressWarnings("unchecked")
	public List<T> getAll() {
		return createCriteria().list();
	}

	@SuppressWarnings("unchecked")
	public <X> X findUnique(String hql, Object... parameters) {
		return (X) createQuery(hql, parameters).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public <X> X findUnique(String hql, Map<String, ?> parameters) {
		return (X) createQuery(hql, parameters).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public <X> List<X> find(String hql, Object... parameters) {
		return createQuery(hql, parameters).list();
	}

	@SuppressWarnings("unchecked")
	public <X> List<X> find(String hql, Map<String, ?> parameters) {
		return createQuery(hql, parameters).list();
	}

	public void save(T entity) {
		getSession().saveOrUpdate(entity);
	}

	public void update(T entity) {
		getSession().update(entity);
	}

	public void delete(T entity) {
		getSession().delete(entity);
	}

	public void delete(PK id) {
		delete(get(id));
	}
}