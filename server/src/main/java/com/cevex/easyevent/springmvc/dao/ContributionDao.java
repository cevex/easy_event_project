package com.cevex.easyevent.springmvc.dao;

import com.cevex.easyevent.springmvc.common.AbstractDao;
import com.cevex.easyevent.springmvc.dao.entity.ContributionEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ContributionDao extends AbstractDao<Long, ContributionEntity> {

    public ContributionEntity findContribution(long id) {
        return getByKey(id);
    }

    public void saveContribution(ContributionEntity contribution) {
        persist(contribution);
    }

    public void deleteContribution(long id) {
        String QUERY = "delete from t_contribution where contribution_id = " + Long.toString(id);
        Query query = getSession().createSQLQuery(QUERY);
        query.executeUpdate();
    }

    @SuppressWarnings("unchecked")
    public List<ContributionEntity> findAllContributions() {
        Criteria criteria = createEntityCriteria();
        return (List<ContributionEntity>) criteria.list();
    }

}
