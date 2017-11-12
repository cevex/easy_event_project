package com.cevex.easyevent.springmvc.app.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ParticipantFull extends Participant {

    //=========================================================================
    //          Attributes
    //=========================================================================

    private List<ContributionOfParticipant> contributionList;

    //=========================================================================
    //          Constructor
    //=========================================================================

    public ParticipantFull() {
    }

    public ParticipantFull(Long id, String username, List<ContributionOfParticipant> contributionList) {
        super(id, username);
        this.contributionList = contributionList;
    }

    //=========================================================================
    //          Getter/Setter
    //=========================================================================

    public List<ContributionOfParticipant> getContributionList() {
        return contributionList;
    }

    public void setContributionList(List<ContributionOfParticipant> contributionList) {
        this.contributionList = contributionList;
    }
}
