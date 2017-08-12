package com.cevex.easyevent.springmvc.app.dao;

import com.cevex.easyevent.springmvc.share.dao.AbstractDao;
import com.cevex.easyevent.springmvc.app.dao.entity.ExpenseEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ExpenseDao extends AbstractDao<Long, ExpenseEntity> {

    public ExpenseEntity findExpense(long id) {
        return getByKey(id);
    }

    public void saveExpense(ExpenseEntity expense) {
        persist(expense);
    }

    public void deleteExpense(long id) {
        String QUERY = "delete from t_expense where expense_id = " + Long.toString(id);
        Query query = getSession().createSQLQuery(QUERY);
        query.executeUpdate();
    }

    @SuppressWarnings("unchecked")
    public List<ExpenseEntity> findAllExpenses() {
        Criteria criteria = createEntityCriteria();
        return (List<ExpenseEntity>) criteria.list();
    }

}
