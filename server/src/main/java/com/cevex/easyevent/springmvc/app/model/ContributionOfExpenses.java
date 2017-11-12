package com.cevex.easyevent.springmvc.app.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ContributionOfExpenses extends Contribution {

    //=========================================================================
    //          Attributes
    //=========================================================================

    private Participant participant;

    //=========================================================================
    //          Constructor
    //=========================================================================

    public ContributionOfExpenses() {

    }

    public ContributionOfExpenses(Long id, String amount, Participant participant) {
        super(id, amount);
        this.participant = participant;
    }

    //=========================================================================
    //          Getter/Setter
    //=========================================================================


}
