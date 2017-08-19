package com.cevex.easyevent.springmvc.app.model;

import com.cevex.easyevent.springmvc.share.framework.model.ModelElement;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Event extends ModelElement {

    //=========================================================================
    //          Attributes
    //=========================================================================

    @NotNull
    @Size(min = 3, max = 50)
    private String title;

    @Size(min = 3, max = 50)
    private String place;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'hh:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private DateTime start;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'hh:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private DateTime end;

    private Byte[] image;

    private Participant participant;

    private List<Expense> expenses;

    //=========================================================================
    //          Constructor
    //=========================================================================

    public Event() {
    }

    public Event(Long id, String title, String place, DateTime start, DateTime end, Byte[] image, Participant participant, List<Expense> expenses) {
        super(id);
        this.title = title;
        this.place = place;
        this.start = start;
        this.end = end;
        this.image = image;
        this.participant = participant;
        this.expenses = expenses;
    }

    //=========================================================================
    //          Getter/Setter
    //=========================================================================

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public DateTime getStart() {
        return start;
    }

    public void setStart(DateTime start) {
        this.start = start;
    }

    public DateTime getEnd() {
        return end;
    }

    public void setEnd(DateTime end) {
        this.end = end;
    }

    public Byte[] getImage() {
        return image;
    }

    public void setImage(Byte[] image) {
        this.image = image;
    }

    public Participant getParticipant() {
        return participant;
    }

    public void setParticipant(Participant participant) {
        this.participant = participant;
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public void setExpenses(List<Expense> expenses) {
        this.expenses = expenses;
    }
}
