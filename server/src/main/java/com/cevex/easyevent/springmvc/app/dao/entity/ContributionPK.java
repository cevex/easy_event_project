package com.cevex.easyevent.springmvc.app.dao.entity;

import java.io.Serializable;

/**
 * Primary key of Contribution Table
 */
public class ContributionPK implements Serializable {

    //=========================================================================
    //          Attributes
    //=========================================================================

    private Long expenseId;

    private String participantId;

    //=========================================================================
    //          Constructor
    //=========================================================================

    public ContributionPK() {
    }

    public ContributionPK(Long expenseId, String participantId) {
        this.expenseId = expenseId;
        this.participantId = participantId;
    }
}