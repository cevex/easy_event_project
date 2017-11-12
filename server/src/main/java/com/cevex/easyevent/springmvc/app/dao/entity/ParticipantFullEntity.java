package com.cevex.easyevent.springmvc.app.dao.entity;

import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

public class ParticipantFullEntity extends ParticipantEntity {

    //=========================================================================
    //          Attributes
    //=========================================================================

    @OneToMany
    @JoinColumn(name = "contribution_participant_id")
    private Collection<ContributionEntity> contributionList = new ArrayList<>();

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
