package com.cevex.easyevent.springmvc.app.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Contribution {

    //=========================================================================
    //          Attributes
    //=========================================================================

    private Long id;

    private Long expenseId;

    private String participantId;

    private String amount;

    //=========================================================================
    //          Constructor
    //=========================================================================

    public Contribution() {

    }

    public Contribution(Long id, Long expenseId, String participantId, String amount) {
        this.id = id;
        this.expenseId = expenseId;
        this.participantId = participantId;
        this.amount = amount;
    }

    //=========================================================================
    //          Getter/Setter
    //=========================================================================

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
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
}
