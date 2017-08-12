package com.cevex.easyevent.springmvc.dao;

import com.cevex.easyevent.springmvc.common.AbstractDao;
import com.cevex.easyevent.springmvc.dao.entity.ParticipantEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ParticipantDao extends AbstractDao<Long, ParticipantEntity> {

    public ParticipantEntity findParticipant(long id) {
        return getByKey(id);
    }

    public void saveParticipant(ParticipantEntity participant) {
        persist(participant);
    }

    public void deleteParticipant(long id) {
        String QUERY = "delete from t_participant where participant_id = " + Long.toString(id);
        Query query = getSession().createSQLQuery(QUERY);
        query.executeUpdate();
    }

    @SuppressWarnings("unchecked")
    public List<ParticipantEntity> findAllParticipants() {
        Criteria criteria = createEntityCriteria();
        return (List<ParticipantEntity>) criteria.list();
    }

}
