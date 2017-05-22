package com.cevex.easyevent.springmvc.mapper;

import com.cevex.easyevent.springmvc.dao.entity.EventEntity;
import com.cevex.easyevent.springmvc.model.Event;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
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
        event.setTitle(entity.getTitle());
        event.setPlace(entity.getPlace());
        event.setStart(entity.getStart().toDateTime());
        event.setEnd(entity.getEnd().toDateTime());
        event.setImage(entity.getImage());

        return event;
    }

    public EventEntity mapEvent(Event event) {
        EventEntity entity = new EventEntity();

        entity.setId(event.getId());
        updateEventEntity(entity, event);

        return entity;
    }

    public void updateEventEntity(EventEntity entity, Event event) {
        entity.setTitle(event.getTitle());
        entity.setPlace(event.getPlace());
        entity.setStart(new LocalDateTime(event.getStart()));
        entity.setEnd(new LocalDateTime(event.getEnd()));
        entity.setImage(event.getImage());
    }
}
