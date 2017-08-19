package com.cevex.easyevent.springmvc.app.model;

import com.cevex.easyevent.springmvc.share.framework.model.ModelElement;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.NotNull;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Participant extends ModelElement {

    //=========================================================================
    //          Attributes
    //=========================================================================

    private Long eventId;

    @NotNull
    private String username;

    private List<Contribution> contributionList;

    //=========================================================================
    //          Constructor
    //=========================================================================

    public Participant() {
    }

    public Participant(Long id, Long eventId, String username, List<Contribution> contributionList) {
        super(id);
        this.eventId = eventId;
        this.username = username;
        this.contributionList = contributionList;
    }

    //=========================================================================
    //          Getter/Setter
    //=========================================================================

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Contribution> getContributionList() {
        return contributionList;
    }

    public void setContributionList(List<Contribution> contributionList) {
        this.contributionList = contributionList;
    }
}
