package com.cevex.easyevent.springmvc.app.service;

import com.cevex.easyevent.springmvc.app.dao.EventDao;
import com.cevex.easyevent.springmvc.app.dao.entity.EventEntity;
import com.cevex.easyevent.springmvc.app.mapper.EventMapper;
import com.cevex.easyevent.springmvc.app.model.Event;
import com.cevex.easyevent.springmvc.share.rest.error.exception.AlreadyExistsException;
import com.cevex.easyevent.springmvc.share.rest.error.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class EventService {

    //=========================================================================
    //          Attributes
    //=========================================================================

    private EventDao eventDao;

    private EventMapper eventMapper;

    //=========================================================================
    //          Constructor
    //=========================================================================

    @Autowired
    public EventService(EventDao eventDao, EventMapper eventMapper) {
        this.eventDao = eventDao;
        this.eventMapper = eventMapper;
    }

    //=========================================================================
    //          Control
    //=========================================================================

    private boolean isEventExist(Long id) {
        return id != null && this.findEvent(id) != null;
    }

    //=========================================================================
    //          Retrieve
    //=========================================================================

    public List<Event> getAllEvents() {
        List<EventEntity> events = eventDao.findAllEvents();
        return eventMapper.mapEventList(events);
    }

    public Event getEvent(long id) {
        EventEntity eventEntity = findEvent(id);
        return eventMapper.mapEvent(eventEntity);
    }

    private EventEntity findEvent(long id) {
        EventEntity eventEntity = eventDao.findEvent(id);

        if (eventEntity == null) {
            throw new NotFoundException("Event: id=" + id + "=> doesn't exist");
        }

        return eventEntity;
    }

    //=========================================================================
    //          Create
    //=========================================================================

    public void createEvent(Event event) {
        if (!isEventExist(event.getId())) {
            eventDao.saveEvent(eventMapper.mapEvent(event));
        } else {
            throw new AlreadyExistsException("Event: id=" + event.getId() + "=> already exist");
        }
    }

    //=========================================================================
    //          Update
    //=========================================================================

    public Event updateEvent(long id, Event event) {
        EventEntity entity = findEvent(id);
        eventMapper.updateEventEntity(entity, event);
        return event;
    }

    //=========================================================================
    //          Delete
    //=========================================================================

    public void deleteEvent(long id) {
        if (isEventExist(id)) {
            eventDao.deleteEvent(id);
        }
    }

}
