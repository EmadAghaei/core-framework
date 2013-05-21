package com.citydi.common.base;

/**
 * Created by IntelliJ IDEA.
 * User: Aghayi
 * Date: 10/22/12
 * Time: 2:52 PM
 */

import com.citydi.common.base.exceptions.SystemException;
import org.hibernate.*;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateSystemException;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.*;

public class BaseDAO<T extends BaseEntity> extends HibernateDaoSupport /*implements EntityObjectDao<T>*/ {
    //protected final Logger log = Logger.getLogger(getClass().getName());
    private Class<T> persistentClass;
//    @Autowired
//    private SessionFactory sessionFactory;

    public BaseDAO(Class<T> persistentClass) {
        assert (persistentClass != null);
        this.persistentClass = persistentClass;
//        setSessionFactory(sessionFactory);
    }

    public BaseDAO(Class<T> persistentClass, SessionFactory sessionFactory) {
        assert (persistentClass != null);
        assert (sessionFactory != null);
        this.persistentClass = persistentClass;
        setSessionFactory(sessionFactory);
    }

    public Class<T> getPersistentClass() {
        return persistentClass;
    }

    public void flush() {
        getHibernateTemplate().flush();
    }

    public List<T> findAll() {
        return getHibernateTemplate().loadAll(this.persistentClass);
    }

    public T findById(Long id) {
        return (T) getHibernateTemplate().get(this.persistentClass, id);
    }

    public boolean exists(Long id) {
        T entity = (T) getHibernateTemplate().get(this.persistentClass, id);

        return (entity != null);
    }

    public void reattachToSession(T entityObject) {
        getSession().refresh(entityObject, LockOptions.NONE);
    }

    public T persist(T object) {
        return save(object);
    }

    public T edit(T object) {
        return save(object);
    }

    public T save(T object) {
        try {
            getHibernateTemplate().saveOrUpdate(object);
            getHibernateTemplate().flush();
        } catch (HibernateSystemException e) {
            if (e.getCause() instanceof NonUniqueObjectException) {
                object = getHibernateTemplate().merge(object);
                getHibernateTemplate().saveOrUpdate(object);
                getHibernateTemplate().flush();
            } else {
                throw e;
            }
        }

        return object;
    }

    public void save(List<? extends T> entityObjects) {
        getHibernateTemplate().saveOrUpdateAll(entityObjects);
        getHibernateTemplate().flush();
    }

    public void remove(Long id) {
        remove(findById(id));
    }

    public void remove(T entityObject) throws SystemException {
        try {
            getHibernateTemplate().delete(entityObject);
            getHibernateTemplate().flush();
        } catch (DataAccessException e) {
            if (e.getCause() instanceof NonUniqueObjectException) {
                entityObject = getHibernateTemplate().merge(entityObject);
                getHibernateTemplate().delete(entityObject);
                getHibernateTemplate().flush();
            } else {
                throw e;
            }
        }
    }

    public void remove(List<? extends T> entityObject) {
        try {
            getHibernateTemplate().deleteAll(entityObject);
            getHibernateTemplate().flush();
        } catch (DataAccessException e) {
            if (e.getCause() instanceof NonUniqueObjectException) {
                entityObject = getHibernateTemplate().merge(entityObject);
                getHibernateTemplate().delete(entityObject);
                getHibernateTemplate().flush();
            } else {
                throw e;
            }
        }
    }

    public List<T> findByQuery(String query, Object... args) {
        return findByQueryFromTo(query, -1, -1, args);
    }

    public List<T> findByQueryFromTo(String query, int from, int to, Object... args) {
        return findByQueryFromToUseIterate(query, from, to, false, args);
    }

    public List<T> findByQueryUseIterate(String query, Object... args) {
        return findByQueryFromToUseIterate(query, -1, -1, true, args);
    }

    public List<T> findByQueryFromToUseIterate(final String query, final int from, final int to, final boolean useIterate, final Object... args) {
        return getHibernateTemplate().executeFind(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
                Query queryObject = session.createQuery(query);
                prepareQuery(queryObject, from, to);
                return executeQuery(queryObject, args, useIterate);
            }
        });
    }

    public List<T> findByFields(Object... fieldNamesAndValues) {
        return findByFieldsFromToUseIterateOrderBy(-1, -1, false, null, fieldNamesAndValues);
    }

    public List<T> findByFieldsFromTo(int from, int to, Object... fieldNamesAndValues) {
        return findByFieldsFromToUseIterateOrderBy(from, to, false, null, fieldNamesAndValues);
    }

    public List<T> findByFieldsOrderBy(String orderBy, Object... fieldNamesAndValues) {
        return findByFieldsFromToUseIterateOrderBy(-1, -1, false, orderBy, fieldNamesAndValues);
    }

    public List<T> findByFieldsFromToOrderBy(int from, int to, String orderBy, Object... fieldNamesAndValues) {
        return findByFieldsFromToUseIterateOrderBy(from, to, false, orderBy, fieldNamesAndValues);
    }

    public List<T> findByFieldsFromToUseIterateOrderBy(int from, int to, boolean useIterate, String orderBy, Object... fieldNamesAndValues) {
        return findByFields(null, from, to, useIterate, orderBy, fieldNamesAndValues);
    }

    /*public List<T> findFromTo(int from, int to) {
        return findByFields(null, from, to, false, null);
    }*/

    public List<T> findBynameFlag(String nameFlag) {
        List args = new LinkedList();
        if (nameFlag != null) {
            args.add("nameFlag");
            args.add("=");
            args.add(nameFlag);
        }
        return findByFields(args.toArray());
    }

    private List<T> findByFields(String queryPrefix, int from, int to, boolean useIterate, String orderBy, Object... fieldNamesAndValues) {
        String query = (queryPrefix != null ? queryPrefix : "") + "from " + persistentClass.getName() + " result";
        List args = new ArrayList();

        if (fieldNamesAndValues.length % 3 != 0) {
            throw new IllegalArgumentException("'fieldNamesAndValues' parameter length should be a multiple of three: 'fieldName', 'operator', 'fieldValue'");
        }

        if (fieldNamesAndValues.length > 0) {
            query += " where " + createWhereClause(fieldNamesAndValues, args);
        }

        if (orderBy != null) {
            query += " order by result." + orderBy;
        }

        return findByQueryFromToUseIterate(query, from, to, useIterate, args.toArray());
    }

    protected String createWhereClause(Object[] fieldNamesAndValues, List args) {
        String whereClause = "";

        for (int i = 0; i < fieldNamesAndValues.length; i = i + 3) {
            if (i > 0) {
                whereClause += " and ";
            }

            whereClause += "result." + fieldNamesAndValues[i];

            if (fieldNamesAndValues[i + 2] == null) {
                if (fieldNamesAndValues[i + 1].toString().trim().equals("="))
                    whereClause += " is null";
                else
                    whereClause += " is not null";
            } else if (fieldNamesAndValues[i + 1].toString().trim().equals("Query")) {
                whereClause += "=" + fieldNamesAndValues[i + 2].toString();
            } else if (fieldNamesAndValues[i + 1] == "like") {
                args.add("%" + fieldNamesAndValues[i + 2] + "%");
                whereClause += " " + fieldNamesAndValues[i + 1] + " ? ";
            } else {
                args.add(fieldNamesAndValues[i + 2]);
                whereClause += " " + fieldNamesAndValues[i + 1] + " ? ";
            }
        }
        return whereClause;
    }

    public T findSingleByQuery(String query, Object... args) {
        return (T) DataAccessUtils.uniqueResult(findByQuery(query, args));
    }

    public T findSingleByFields(Object... fieldNameOperatorValueTriplets) {
        List<T> list = findByFields(fieldNameOperatorValueTriplets);
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return (T) DataAccessUtils.uniqueResult(findByFields(fieldNameOperatorValueTriplets));
    }

    public int findCountByQuery(final String query, final Object... args) {
        List result = getHibernateTemplate().executeFind(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
            Query queryObject = session.createQuery(query);

            prepareQuery(queryObject, -1, -1);
            return executeQuery(queryObject, args, false);
            }
        });

        return ((Number) DataAccessUtils.uniqueResult(result)).intValue();
    }

    public int findCountByQueryWithSelect(final String query, final Object... args) {
        List result = getHibernateTemplate().executeFind(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
                String fullQuery = "select count(result.id) " + query;
                Query queryObject = session.createQuery(fullQuery);

                prepareQuery(queryObject, -1, -1);
                return executeQuery(queryObject, args, false);
            }
        });

        return ((Number) DataAccessUtils.uniqueResult(result)).intValue();
    }

    public int findCountByFields(Object... fieldNamesAndValues) {
        String query = "select count(result.id) from " + persistentClass.getName() + " result";
        final List args = new ArrayList();

        if (fieldNamesAndValues.length % 3 != 0) {
            throw new IllegalArgumentException("'fieldNamesAndValues' parameter length should be a multiple of three: 'fieldName', 'operator', 'fieldValue'");
        }

        if (fieldNamesAndValues.length > 0) {
            query += " where " + createWhereClause(fieldNamesAndValues, args);
        }

        final String fullQuery = query;

        List result = getHibernateTemplate().executeFind(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
                Query queryObject = session.createQuery(fullQuery);
                prepareQuery(queryObject, -1, -1);
                return executeQuery(queryObject, args.toArray(), false);
            }
        });

        return ((Number) DataAccessUtils.uniqueResult(result)).intValue();

        //return ((Number) DataAccessUtils.uniqueResult(findByFields("select count(result.id) ", -1, -1, false, null, fieldNamesAndValues))).intValue();
    }

    private Object executeQuery(Query queryObject, Object[] args, boolean useIterate) {
        if (args != null) {
            for (int i = 0; i < args.length; i++) {
                queryObject.setParameter(i, args[i]);
            }
        }

        if (useIterate) {
            Iterator resultIter = queryObject.iterate();
            if (!resultIter.hasNext()) {
                return Collections.EMPTY_LIST;
            } else {
                List result = new ArrayList();
                while (resultIter.hasNext()) {
                    BaseEntity entityObject = (BaseEntity) resultIter.next();
                    Hibernate.initialize(entityObject);
                    result.add(entityObject);
                }
                return result;
            }
        } else {
            return queryObject.list();
        }
    }

    private void prepareQuery(Query queryObject, int from, int to) {
        queryObject.setCacheable(true);
        queryObject.setCacheRegion(persistentClass.getName());
        if (from != -1 && to != -1) {
            queryObject.setFetchSize(to - from + 1);
            queryObject.setFirstResult(from);
            queryObject.setMaxResults(to - from + 1);
        }
        SessionFactoryUtils.applyTransactionTimeout(queryObject, getSessionFactory());
    }

    public int findCount() {
        final String query = "select count(result.id) from " + persistentClass.getName() + " result";

        List result = getHibernateTemplate().executeFind(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
                Query queryObject = session.createQuery(query);
                prepareQuery(queryObject, -1, -1);
                return executeQuery(queryObject, new Object[0], false);
            }
        });

        return ((Number) DataAccessUtils.uniqueResult(result)).intValue();

        //return ((Number) DataAccessUtils.uniqueResult(findByFields("select count(result.id) ", -1, -1, false, null))).intValue();
    }

    protected int update(String query, List params) {
        Query queryObject = getSession().createQuery(query);
        for (int i = 0; i < params.size(); i++) {
            queryObject.setParameter(i, params.get(i));
        }
        return queryObject.executeUpdate();
    }

    public Object findMax(String field) {

        StringBuilder query = new StringBuilder("select ");
        query.append("max(m.").append(field).append(')');
        query.append(" from ");
        query.append(persistentClass.getCanonicalName()).append(" m ");

        List max = getHibernateTemplate().find(query.toString());
        if (max != null && max.size() > 0) {
            return max.get(0);
        }
        return null;
    }

    public int createSqlQuery(String query) {
        final SQLQuery sqlQuery = getSession().createSQLQuery(query);
        return sqlQuery.executeUpdate();
    }

    public boolean isNotEmpty(String string) {
        return string != null && !string.trim().isEmpty();
    }


}
