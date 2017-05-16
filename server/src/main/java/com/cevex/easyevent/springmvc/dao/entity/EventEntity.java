package com.cevex.easyevent.springmvc.dao.entity;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Blob;

@Entity
@Table(name = "t_event")
public class EventEntity {

    @Id
    @Column(name = "event_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 3, max = 50)
    @Column(name = "event_title", nullable = false)
    private String title;

    @Size(min = 3, max = 50)
    @Column(name = "event_place")
    private String place;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ssXXX")
    @Column(name = "event_start_date", nullable = false)
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
    private LocalDate start;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ssXXX")
    @Column(name = "event_end_date", nullable = false)
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
    private LocalDate end;

    @Lob
    @Type(type = "org.hibernate.type.BlobType")
    @Column(name = "event_image")
    private Blob image;

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

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }

    public Blob getImage() {
        return image;
    }

    public void setImage(Blob image) {
        this.image = image;
    }
}
