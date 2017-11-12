package com.cevex.easyevent.springmvc.app.dao.entity;

import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.Collection;
import java.util.HashSet;

public class ExpenseFullEntity extends ExpenseEntity {

    //=========================================================================
    //          Attributes
    //=========================================================================

    @OneToMany
    @JoinColumn(name = "contribution_id")
    private Collection<ContributionEntity> contributionList = new HashSet<>();

    //=========================================================================
    //          Getter/Setter
    //=========================================================================

    public Collection<ContributionEntity> getContributionList() {
        return contributionList;
    }

    public void setContributionList(Collection<ContributionEntity> contributionList) {
        this.contributionList = contributionList;
    }
}
