package com.cevex.easyevent.springmvc.app.service;

import com.cevex.easyevent.springmvc.app.dao.EventDao;
import com.cevex.easyevent.springmvc.app.dao.entity.EventEntity;
import com.cevex.easyevent.springmvc.app.mapper.EventMapper;
import com.cevex.easyevent.springmvc.app.model.Event;
import com.cevex.easyevent.springmvc.share.framework.error.exception.NotFoundException;
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

    /**
     * Define if an element exist
     *
     * @param eventId - Identifiers of the element to check
     * @return true if exists, false otherwise
     */
    private boolean isExist(Long eventId) {
        return this.getEntity(eventId) != null;
    }

    //=========================================================================
    //          Retrieve
    //=========================================================================

    /**
     * Retrieve an element by identifier
     *
     * @return All the Element
     */
    public List<Event> getEventList() {
        List<EventEntity> entityList = eventDao.findAll();
        return eventMapper.mapToModelList(entityList);
    }

    /**
     * Retrieve an element by identifier
     *
     * @return All the Element
     */
    public Event getEvent(Long eventId) {
        EventEntity entity = getEntity(eventId);
        return eventMapper.mapToModel(entity);
    }

    /**
     * Retrieve an element by identifier
     *
     * @return The entity if found
     * @throws NotFoundException - If not found
     */
    private EventEntity getEntity(Long eventId) {
        EventEntity entity = eventDao.find(eventId);
        if (entity == null) {
            throw new NotFoundException("EventDto not found");
        }
        return entity;
    }

    //=========================================================================
    //          Create
    //=========================================================================

    /**
     * Create a new element
     *
     * @param model - Element to create
     */
    public void createEvent(Event model) {
        eventDao.save(eventMapper.mapToEntity(model));
    }

    //=========================================================================
    //          Update
    //=========================================================================


    /**
     * Update an existing element
     *
     * @param model - Element to create
     */
    public Event updateEvent(Long eventId, Event model) {
        EventEntity entity = this.getEntity(eventId);
        eventMapper.updateEntity(entity, model);
        return model;
    }

    //=========================================================================
    //          Delete
    //=========================================================================

    /**
     * Delete an element
     */
    public void deleteEvent(Long eventId) {
        EventEntity entity = this.getEntity(eventId);
        eventDao.delete(entity);
    }


}
