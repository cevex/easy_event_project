package com.cevex.easyevent.springmvc.dao.entity;

import javax.persistence.*;

@Entity
@Table(name = "t_participant")
public class ParticipantEntity {

    //=========================================================================
    //          Attributes
    //=========================================================================

    @Id
    @Column(name = "participant_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "participant_event_id", nullable = false)
    private Long eventId;

    @Column(name = "participant_user_name")
    private String username;

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
}
