package com.cevex.easyevent.springmvc.service;

import com.cevex.easyevent.springmvc.dao.EventDao;
import com.cevex.easyevent.springmvc.dao.entity.EventEntity;
import com.cevex.easyevent.springmvc.exception.AlreadyExistsException;
import com.cevex.easyevent.springmvc.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
@Transactional
public class EventService {

    @Autowired
    private EventDao dao;

    private boolean isEventExist(Long id) {
        return id != null && dao.findEvent(id) != null;
    }

    public EventEntity getEvent(long id) {
        EventEntity event = dao.findEvent(id);

        if (event == null) {
            throw new NotFoundException("EventEntity: id=" + id + "=> already exist");
        }

        return event;
    }

    public void createEvent(EventEntity event) {
        if (isEventExist(event.getId())) {
            dao.saveEvent(event);
        } else {
            throw new AlreadyExistsException("EventEntity: id=" + event.getId() + "=> already exist");
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
            dao.deleteEvent(id);
        }
    }

    public List<EventEntity> getAllEvents() {
        return dao.findAllEvents();
    }
}
