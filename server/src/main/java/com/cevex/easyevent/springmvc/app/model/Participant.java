package com.cevex.easyevent.springmvc.app.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.NotNull;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Participant {

    //=========================================================================
    //          Attributes
    //=========================================================================

    private Long id;

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
        this.id = id;
        this.eventId = eventId;
        this.username = username;
        this.contributionList = contributionList;
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
