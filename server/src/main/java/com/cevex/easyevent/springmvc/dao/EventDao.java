package com.cevex.easyevent.springmvc.dao;

import com.cevex.easyevent.springmvc.common.AbstractDao;
import com.cevex.easyevent.springmvc.dao.entity.EventEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EventDao extends AbstractDao<Long, EventEntity> {

    public EventEntity findEvent(long id) {
        return getByKey(id);
    }

    public void saveEvent(EventEntity event) {
        persist(event);
    }

    public void deleteEvent(long id) {
        String QUERY = "delete from t_event where event_id = " + Long.toString(id);
        Query query = getSession().createSQLQuery(QUERY);
        query.executeUpdate();
    }

    @SuppressWarnings("unchecked")
    public List<EventEntity> findAllEvents() {
        Criteria criteria = createEntityCriteria();
        return (List<EventEntity>) criteria.list();
    }

}
