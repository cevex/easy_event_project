package com.cevex.easyevent.springmvc.app.mapper;

import com.cevex.easyevent.springmvc.app.dao.entity.EventEntity;
import com.cevex.easyevent.springmvc.app.model.Event;
import com.cevex.easyevent.springmvc.share.framework.AbstractMapper;
import org.joda.time.LocalDateTime;
import org.springframework.stereotype.Service;

@Service
public class EventMapper extends AbstractMapper<Event, EventEntity> {

    //=========================================================================
    //          DAO -> Model
    //=========================================================================

    @Override
    public Event mapToModel(EventEntity entity) {
        Event event = new Event();

        event.setId(entity.getId());
        event.setTitle(entity.getTitle());
        event.setPlace(entity.getPlace());
        event.setStart(entity.getStart().toDateTime());
        event.setEnd(entity.getEnd().toDateTime());
        event.setImage(entity.getImage());

        return event;
    }

    //=========================================================================
    //          Model -> DAO
    //=========================================================================

    @Override
    public void updateEntity(EventEntity entity, Event model) {
        entity.setTitle(model.getTitle());
        entity.setPlace(model.getPlace());
        entity.setStart(new LocalDateTime(model.getStart()));
        entity.setEnd(new LocalDateTime(model.getEnd()));
        entity.setImage(model.getImage());
    }
}
