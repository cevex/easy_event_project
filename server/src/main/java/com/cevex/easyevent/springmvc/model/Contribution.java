package com.cevex.easyevent.springmvc.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Contribution {

    //=========================================================================
    //          Attributes
    //=========================================================================

    private Long id;

    @NotNull
    @Size(min = 3, max = 50)
    private String title;

    @Size(min = 3, max = 50)
    private String place;

    @NotNull
//    @DateFormatConstraint
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'hh:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private DateTime start;

    @NotNull
//    @DateFormatConstraint
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'hh:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private DateTime end;

    private Byte[] image;

    //=========================================================================
    //          Constructor
    //=========================================================================

    public Contribution() {
    }

    public Contribution(Long id, String title, String place, DateTime start, DateTime end, Byte[] image) {
        this.id = id;
        this.title = title;
        this.place = place;
        this.start = start;
        this.end = end;
        this.image = image;
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
}
