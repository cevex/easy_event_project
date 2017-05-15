package com.cevex.easyevent.springmvc.model;

import java.util.Date;

public class Event {

    private Long id;
    private String title;
    private String place;
    private Date start;
    private Date end;
    private Byte[] image;

    public Event(Long id, String title, String place, Date start, Date end, Byte[] image) {
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

    public Byte[] getImage() {
        return image;
    }

    public void setImage(Byte[] image) {
        this.image = image;
    }
}
