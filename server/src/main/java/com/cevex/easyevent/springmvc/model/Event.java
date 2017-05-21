package com.cevex.easyevent.springmvc.model;

import com.cevex.easyevent.springmvc.error.validation.DateFormatConstraint;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Blob;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Event {

    private Long id;

    @NotNull
    @Size(min = 3, max = 50)
    private String title;

    @Size(min = 3, max = 50)
    private String place;

    @NotNull
    @DateFormatConstraint
    //@DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm:ss'Z'")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private Date start;

    @NotNull
    @DateFormatConstraint
    //@DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm:ss'Z'")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private Date end;

    private Blob image;

    public Event() {
    }

    public Event(Long id, String title, String place, Date start, Date end, Blob image) {
        this.id = id;
        this.title = title;
        this.place = place;
        this.start = start;
        this.end = end;
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public Blob getImage() {
        return image;
    }

    public void setImage(Blob image) {
        this.image = image;
    }
}
