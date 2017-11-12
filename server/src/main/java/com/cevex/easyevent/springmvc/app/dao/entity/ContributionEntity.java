package com.cevex.easyevent.springmvc.app.dao.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "t_contribution")
public class ContributionEntity implements Serializable {

    //=========================================================================
    //          Attributes
    //=========================================================================

    @Id
    @Column(name = "contribution_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "contribution_expense_id", nullable = false)
    private Long expenseId;

    @Column(name = "contribution_participant_id", nullable = false)
    private Long participantId;

    @Column(name = "contribution_amount", nullable = false)
    private String amount;

    //=========================================================================
    //          Getter/Setter
    //=========================================================================

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(Long expenseId) {
        this.expenseId = expenseId;
    }

    public Long getParticipantId() {
        return participantId;
    }

    public void setParticipantId(Long participantId) {
        this.participantId = participantId;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
