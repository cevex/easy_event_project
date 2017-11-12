package com.cevex.easyevent.springmvc.app.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.joda.time.DateTime;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExpenseFull extends Expense {

    //=========================================================================
    //          Attributes
    //=========================================================================

    private List<ContributionOfExpenses> contributionList;

    //=========================================================================
    //          Constructor
    //=========================================================================

    public ExpenseFull() {
    }

    public ExpenseFull(Long id, String label, DateTime date, List<ContributionOfExpenses> contributionList) {
        super(id, label, date);
        this.contributionList = contributionList;
    }

    //=========================================================================
    //          Getter/Setter
    //=========================================================================

    public List<ContributionOfExpenses> getContributionList() {
        return contributionList;
    }

    public void setContributionList(List<ContributionOfExpenses> contributionList) {
        this.contributionList = contributionList;
    }
}
