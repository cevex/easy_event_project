package com.cevex.easyevent.springmvc.mapper;

import com.cevex.easyevent.springmvc.dao.entity.EventEntity;
import com.cevex.easyevent.springmvc.model.Event;
import org.joda.time.LocalDate;

/**
 * Created by cedric on 16/05/17.
 */
public class EventMapper {

    public Event mapEvent(EventEntity entity) {
        Event event = new Event();

        event.setId(entity.getId());
        event.setPlace(entity.getPlace());
        event.setStart(entity.getStart().toDate());
        event.setEnd(entity.getEnd().toDate());
        event.setImage(entity.getImage());

        return event;
    }

    public EventEntity mapEvent(Event event) {
        EventEntity entity = new EventEntity();

        entity.setId(event.getId());
        entity.setPlace(event.getPlace());
        entity.setStart(new LocalDate(event.getStart()));
        entity.setEnd(new LocalDate(event.getEnd()));
        entity.setImage(event.getImage());

        return entity;
    }
}
