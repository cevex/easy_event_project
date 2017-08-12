package com.cevex.easyevent.springmvc.app.dao.entity;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;

@Entity
@Table(name = "t_expense")
public class ExpenseEntity {

    //=========================================================================
    //          Attributes
    //=========================================================================

    @Id
    @Column(name = "expense_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "expense_event_id", nullable = false)
    private Long eventId;

    @Column(name = "expense_label")
    private String label;

    @Column(name = "expense_date", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ssXXX")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
    private LocalDateTime date;

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

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
