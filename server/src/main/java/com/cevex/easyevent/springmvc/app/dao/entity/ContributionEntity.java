package com.cevex.easyevent.springmvc.app.dao.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "t_contribution")
@IdClass(ContributionPK.class)
public class ContributionEntity implements Serializable {

    //=========================================================================
    //          Attributes
    //=========================================================================

    @Id
    @Column(name = "contribution_expense_id", nullable = false)
    private Long expenseId;

    @Id
    @Column(name = "contribution_participant_id", nullable = false)
    private String participantId;

    @Column(name = "contribution_amount", nullable = false)
    private String amount;

    //=========================================================================
    //          Getter/Setter
    //=========================================================================

    public Long getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(Long expenseId) {
        this.expenseId = expenseId;
    }

    public String getParticipantId() {
        return participantId;
    }

    public void setParticipantId(String participantId) {
        this.participantId = participantId;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
