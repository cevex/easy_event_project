package com.cevex.easyevent.springmvc.app.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ContributionOfParticipant extends Contribution {

    //=========================================================================
    //          Attributes
    //=========================================================================

    private Expense expense;

    //=========================================================================
    //          Constructor
    //=========================================================================

    public ContributionOfParticipant() {

    }

    public ContributionOfParticipant(Long id, Expense expense, String amount) {
        super(id, amount);
        this.expense = expense;
    }

    //=========================================================================
    //          Getter/Setter
    //=========================================================================


    public Expense getExpense() {
        return expense;
    }

    public void setExpense(Expense expense) {
        this.expense = expense;
    }

}
