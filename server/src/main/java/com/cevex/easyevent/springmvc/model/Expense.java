package com.cevex.easyevent.springmvc.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Expense {

    //=========================================================================
    //          Attributes
    //=========================================================================

    private Long id;

    @NotNull
    private Long eventId;

    @Size(min = 3, max = 50)
    private String label;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'hh:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private DateTime date;

    //=========================================================================
    //          Constructor
    //=========================================================================

    public Expense() {
    }

    public Expense(Long id, Long eventId, String label, DateTime date) {
        this.id = id;
        this.eventId = eventId;
        this.label = label;
        this.date = date;
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

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public DateTime getDate() {
        return date;
    }

    public void setDate(DateTime date) {
        this.date = date;
    }
}
