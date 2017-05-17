package com.cevex.easyevent.springmvc.mapper;

import com.cevex.easyevent.springmvc.dao.entity.EventEntity;
import com.cevex.easyevent.springmvc.model.Event;
import org.joda.time.LocalDate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventMapper {

    public List<Event> mapEventList(List<EventEntity> entityList) {
        List<Event> eventList = new ArrayList<>();
        for (EventEntity entity : entityList) {
            eventList.add(mapEvent(entity));
        }
        return eventList;
    }

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
