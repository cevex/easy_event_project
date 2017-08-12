package com.cevex.easyevent.springmvc.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Participant {

    //=========================================================================
    //          Attributes
    //=========================================================================

    private Long id;

    @NotNull
    private Long eventId;

    @NotNull
    private String userName;

    //=========================================================================
    //          Constructor
    //=========================================================================

    public Participant() {
    }

    public Participant(Long id, Long eventId, String userName) {
        this.id = id;
        this.eventId = eventId;
        this.userName = userName;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
