package com.cevex.easyevent.springmvc.app.model;

import com.cevex.easyevent.springmvc.share.framework.model.ModelElement;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Contribution extends ModelElement {

    //=========================================================================
    //          Attributes
    //=========================================================================

    private Long expenseId;

    private Long participantId;

    private String amount;

    //=========================================================================
    //          Constructor
    //=========================================================================

    public Contribution() {

    }

    public Contribution(Long id, Long expenseId, Long participantId, String amount) {
        super(id);
        this.expenseId = expenseId;
        this.participantId = participantId;
        this.amount = amount;
    }

    //=========================================================================
    //          Getter/Setter
    //=========================================================================

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

    public Long getParticipantId() {
        return participantId;
    }

    public void setParticipantId(Long participantId) {
        this.participantId = participantId;
    }
}
