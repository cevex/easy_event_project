package com.cevex.easyevent.springmvc.app.dao;

import com.cevex.easyevent.springmvc.app.dao.entity.ParticipantEntity;
import com.cevex.easyevent.springmvc.share.framework.AbstractDao;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ParticipantDao extends AbstractDao<Long, ParticipantEntity> {

    //=========================================================================
    //          Find List
    //=========================================================================

    /**
     * Find all Participants of a specific event.
     *
     * @param eventId - The event holding the participants
     * @return The participants if found, null otherwise
     */
    @SuppressWarnings("unchecked")
    public List<ParticipantEntity> findParticipantList(long eventId) {
        String QUERY = "SELECT * " +
                "FROM t_participant " +
                "WHERE participant_event_id = :eventId";

        SQLQuery query = this.buildQuery(QUERY);
        query.setParameter("eventId", eventId);

        return (List<ParticipantEntity>) query.list();
    }

    /**
     * Find all participants of a specific expense.
     *
     * @param eventId   - Search in this event.
     * @param expenseId - Find participants for this expense.
     * @return The participant if found, null otherwise
     */
    @SuppressWarnings("unchecked")
    public List<ParticipantEntity> findParticipantListOfExpense(Long eventId, Long expenseId) {
        String QUERY = "SELECT t_participant.*, t_contribution.* " +
                "FROM t_participant " +
                "  LEFT JOIN t_contribution ON (t_participant.participant_id = t_contribution.contribution_participant_id) " +
                "  LEFT JOIN t_expense ON (t_contribution.contribution_expense_id = t_expense.expense_id) " +
                "WHERE t_participant.participant_event_id=:eventId" +
                "  AND t_contribution.contribution_expense_id=:expenseId";

        SQLQuery query = buildQuery(QUERY);
        query.setParameter("eventId", eventId);
        query.setParameter("expenseId", expenseId);

        return (List<ParticipantEntity>) query.list();
    }

    //=========================================================================
    //          Find Single
    //=========================================================================

    /**
     * Find a Participant by unique identifier.
     *
     * @param participantId - Unique identifier for participant
     * @return The participant if found, null otherwise
     */
    public ParticipantEntity findParticipant(Long participantId) {
        String QUERY = "SELECT * " +
                "FROM t_participant " +
                "  LEFT JOIN t_contribution ON (t_participant.participant_id = t_contribution.contribution_participant_id) " +
                "WHERE participant_id = :participantId";

        SQLQuery query = this.buildQuery(QUERY);
        query.setParameter("participantId", participantId);

        return (ParticipantEntity) query.uniqueResult();
    }

    /**
     * Find a Participant by username and event.
     *
     * @param eventId  - Search in this event.
     * @param username - Name of the participant to find
     * @return The participant if found, null otherwise
     */
    public ParticipantEntity findParticipant(long eventId, String username) {
        String QUERY = "SELECT * " +
                "FROM t_participant " +
                "  LEFT JOIN t_contribution ON (t_participant.participant_id = t_contribution.contribution_participant_id) " +
                "WHERE participant_event_id=:eventId " +
                "  AND participant_user_name=:participantName";

        SQLQuery query = this.buildQuery(QUERY);
        query.setParameter("eventId", eventId);
        query.setParameter("participantName", username);

        return (ParticipantEntity) query.uniqueResult();
    }

}

