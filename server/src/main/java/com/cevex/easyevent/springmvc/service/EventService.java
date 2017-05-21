package com.cevex.easyevent.springmvc.service;

import com.cevex.easyevent.springmvc.dao.EventDao;
import com.cevex.easyevent.springmvc.dao.entity.EventEntity;
import com.cevex.easyevent.springmvc.error.exception.AlreadyExistsException;
import com.cevex.easyevent.springmvc.error.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;


@Service
@Transactional
public class EventService {

    private EventDao eventDao;

    @Autowired
    public EventService(EventDao eventDao) {
        this.eventDao = eventDao;
    }

    private boolean isEventExist(Long id) {
        return id != null && eventDao.findEvent(id) != null;
    }

    public EventEntity getEvent(long id) {
        EventEntity event = eventDao.findEvent(id);

        if (event == null) {
            throw new NotFoundException("Event: id=" + id + "=> already exist");
        }

        return event;
    }

    public void createEvent(EventEntity event) {
        if (isEventExist(event.getId())) {
            throw new AlreadyExistsException("Event: id=" + event.getId() + "=> already exist");
        }

    }

    public void updateEvent(EventEntity event) {
        EventEntity entity = this.getEvent(event.getId());

        entity.setPlace(event.getPlace());
        entity.setEnd(event.getEnd());
        entity.setStart(event.getStart());
    }

    public void deleteEvent(long id) {
        if (isEventExist(id)) {
            eventDao.deleteEvent(id);
        }
    }

    public List<EventEntity> getAllEvents() {
        return eventDao.findAllEvents();
    }
}
