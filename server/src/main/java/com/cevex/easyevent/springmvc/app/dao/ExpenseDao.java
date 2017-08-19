package com.cevex.easyevent.springmvc.app.dao;

import com.cevex.easyevent.springmvc.app.dao.entity.ExpenseEntity;
import com.cevex.easyevent.springmvc.share.framework.AbstractDao;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ExpenseDao extends AbstractDao<Long, ExpenseEntity> {


    //=========================================================================
    //          Find List
    //=========================================================================

    /**
     * Find all Expenses of a specific event.
     *
     * @param eventId - The event holding the expenses
     * @return The expenses if found, null otherwise
     */
    @SuppressWarnings("unchecked")
    public List<ExpenseEntity> findExpenseList(long eventId) {
        String QUERY = "SELECT * " +
                "FROM t_expense " +
                "WHERE expense_event_id=:eventId";

        SQLQuery query = this.buildQuery(QUERY);
        query.setParameter("eventId", eventId);

        return (List<ExpenseEntity>) query.list();
    }

    /**
     * Find all expenses of a specific participant.
     *
     * @param eventId         - Search in this event.
     * @param participantName - Name of the participant to find the expense for
     * @return The expense if found, null otherwise
     */
    @SuppressWarnings("unchecked")
    public List<ExpenseEntity> findExpenseListOfParticipant(Long eventId, String participantName) {
        String QUERY = "SELECT t_expense.*, t_contribution.* " +
                "FROM t_expense " +
                "  LEFT JOIN t_contribution ON (t_expense.expense_id = t_contribution.contribution_expense_id) " +
                "  LEFT JOIN t_participant ON (t_contribution.contribution_participant_id = t_participant.participant_id) " +
                "WHERE t_expense.expense_event_id=:eventId" +
                "  AND t_participant.participant_user_name=:participantName";

        SQLQuery query = this.buildQuery(QUERY);
        query.setParameter("eventId", eventId);
        query.setParameter("participantName", participantName);

        return (List<ExpenseEntity>) query.list();
    }

    //=========================================================================
    //          Find Single
    //=========================================================================

    /**
     * Find a Expense by unique identifier.
     *
     * @param expenseId - Unique identifier for expense
     * @return The expense if found, null otherwise
     */
    public ExpenseEntity findExpense(Long expenseId) {
        String QUERY = "SELECT * " +
                "FROM t_expense " +
                "  LEFT JOIN t_contribution ON (t_expense.expense_id = t_contribution.contribution_expense_id) " +
                "WHERE expense_id=:expenseId";

        SQLQuery query = this.buildQuery(QUERY);
        query.setParameter("expenseId", expenseId);

        return (ExpenseEntity) query.uniqueResult();
    }

    /**
     * Find a Expense by username and event.
     *
     * @param eventId   - Search in this event.
     * @param expenseId - Unique identifier for expense
     * @return The expense if found, null otherwise
     */
    public ExpenseEntity findExpenseWithContibution(long eventId, Long expenseId) {
        String QUERY = "SELECT * " +
                "FROM t_expense " +
                "  LEFT JOIN t_contribution ON (t_expense.expense_id = t_contribution.contribution_expense_id) " +
                "WHERE expense_id=:expenseId" +
                "  AND expense_event_id=:eventId ";

        SQLQuery query = this.buildQuery(QUERY);
        query.setParameter("eventId", eventId);
        query.setParameter("expenseId", expenseId);

        return (ExpenseEntity) query.uniqueResult();
    }

    /**
     * Find a Expense by username and event.
     *
     * @param eventId   - Search in this event.
     * @param expenseId - Unique identifier for expense
     * @return The expense if found, null otherwise
     */
    public ExpenseEntity findExpense(long eventId, Long expenseId) {
        String QUERY = "SELECT * " +
                "FROM t_expense " +
                "WHERE expense_id=:expenseId" +
                "  AND expense_event_id=:eventId ";

        SQLQuery query = this.buildQuery(QUERY);
        query.setParameter("eventId", eventId);
        query.setParameter("expenseId", expenseId);

        return (ExpenseEntity) query.uniqueResult();
    }
}
