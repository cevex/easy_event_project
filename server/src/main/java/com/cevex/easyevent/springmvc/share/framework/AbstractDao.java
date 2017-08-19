package com.cevex.easyevent.springmvc.share.framework;

import com.cevex.easyevent.springmvc.share.framework.utils.GenericClassUtils;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@Transactional
@Repository
public abstract class AbstractDao<PK extends Serializable, ENTITY> {

    //=========================================================================
    //          Attributes
    //=========================================================================

    private final Class<ENTITY> persistentClass;

    @Autowired
    private SessionFactory sessionFactory;

    //=========================================================================
    //          Constructor
    //=========================================================================

    /**
     * Default constructor
     */
    @SuppressWarnings("unchecked")
    public AbstractDao() {
        this.persistentClass = (Class<ENTITY>) GenericClassUtils.getParameterClass(this.getClass(), 1);
    }

    //=========================================================================
    //          Action
    //=========================================================================

    @SuppressWarnings("unchecked")
    public List<ENTITY> findAll() {
        Criteria criteria = createEntityCriteria();
        return (List<ENTITY>) criteria.list();
    }

    /**
     * Find an entry with is unique identifier.
     */
    @SuppressWarnings("unchecked")
    public ENTITY find(PK key) {
        return (ENTITY) getSession().get(persistentClass, key);
    }

    /**
     * Create an entry
     */
    public void save(ENTITY entity) {
        getSession().persist(entity);
    }

    /**
     * Delete an entry
     */
    public void delete(ENTITY entity) {
        getSession().delete(entity);
    }

    //=========================================================================
    //          Technical
    //=========================================================================

    protected SQLQuery buildQuery(String request) {
        return getSession()
                .createSQLQuery(request)
                .addEntity(persistentClass.getCanonicalName());
    }

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    protected Criteria createEntityCriteria() {
        return getSession().createCriteria(persistentClass);
    }

}