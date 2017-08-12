package com.cevex.easyevent.springmvc.dao.entity;

import javax.persistence.*;

@Entity
@Table(name = "t_contribution")
public class ContributionEntity {

    //=========================================================================
    //          Attributes
    //=========================================================================

    @Id
    @Column(name = "contribution_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "contribution_expense_id", nullable = false)
    private Long expenseId;

    @Column(name = "contribution_participant_id")
    private String participantId;

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
