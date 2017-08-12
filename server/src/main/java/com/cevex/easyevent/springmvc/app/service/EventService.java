package com.cevex.easyevent.springmvc.app.service;

import com.cevex.easyevent.springmvc.app.dao.EventDao;
import com.cevex.easyevent.springmvc.app.dao.entity.EventEntity;
import com.cevex.easyevent.springmvc.app.mapper.EventMapper;
import com.cevex.easyevent.springmvc.app.model.Event;
import com.cevex.easyevent.springmvc.share.framework.AbstractService;
import com.cevex.easyevent.springmvc.share.framework.error.exception.AlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class EventService extends AbstractService<Event, EventEntity> {

    //=========================================================================
    //          Constructor
    //=========================================================================

    @Autowired
    public EventService(EventDao eventDao, EventMapper eventMapper) {
        super(eventDao, eventMapper);
    }

    //=========================================================================
    //          Create
    //=========================================================================

    public void createEvent(Event event) {
        if (!isExist(event.getId())) {
            super.create(event);
        } else {
            throw new AlreadyExistsException("Event: id=" + event.getId() + "=> already exist");
        }
    }

}
