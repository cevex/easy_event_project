package com.cevex.easyevent.springmvc.app.service;

import com.cevex.easyevent.springmvc.app.dao.ParticipantDao;
import com.cevex.easyevent.springmvc.app.dao.entity.ParticipantEntity;
import com.cevex.easyevent.springmvc.app.mapper.ParticipantMapper;
import com.cevex.easyevent.springmvc.app.model.Participant;
import com.cevex.easyevent.springmvc.share.framework.error.exception.AlreadyExistsException;
import com.cevex.easyevent.springmvc.share.framework.error.exception.EmptyException;
import com.cevex.easyevent.springmvc.share.framework.error.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ParticipantService {

    //=========================================================================
    //          Attributes
    //=========================================================================

    private ParticipantDao participantDao;

    private ParticipantMapper participantMapper;

    //=========================================================================
    //          Constructor
    //=========================================================================

    @Autowired
    public ParticipantService(ParticipantDao participantDao, ParticipantMapper participantMapper) {
        this.participantDao = participantDao;
        this.participantMapper = participantMapper;
    }

    //=========================================================================
    //          Retrieve List
    //=========================================================================

    /**
     * Retrieve all Participants of a specific event.
     *
     * @param eventId - The event holding the participants
     * @return The participants List if found, null otherwise
     */
    public List<Participant> getParticipantList(Long eventId) {
        List<ParticipantEntity> entityList = participantDao.findList(eventId);
        if (entityList == null || entityList.size() <= 0) {
            throw new EmptyException("No participants for [eventId= " + eventId + "]");
        }
        return participantMapper.mapToModelList(entityList);
    }

    //=========================================================================
    //          Retrieve Single
    //=========================================================================

    /**
     * Retrieve a ParticipantFull by username and event.
     *
     * @param participantId - The participants to retrieve.
     * @return The participant if found
     */
    public Participant getParticipant(Long participantId) {
        ParticipantEntity entity = participantDao.find(participantId);
        return participantMapper.mapToModel(entity);
    }

    /**
     * Retrieve a ParticipantFull by username and event.
     *
     * @param participantId - The participants to retrieve.
     * @return The participant if found
     */
    public Participant getFullParticipant(Long participantId) {
        //TODO : Implement service getFullParticipant
        throw new NotImplementedException();
    }

    /**
     * Find a participant entity by name
     *
     * @param participantId - Identifiers of the participant to find
     * @return The entity if found
     * @throws NotFoundException - If not found
     */
    private ParticipantEntity findEntity(Long participantId) {
        ParticipantEntity entity = participantDao.find(participantId);
        if (entity == null) {
            throw new NotFoundException("ParticipantFull not found");
        }
        return entity;
    }

    //=========================================================================
    //          Create
    //=========================================================================

    /**
     * Create a new participant
     *
     * @param eventId     - Identifiers of the element to check
     * @param participant - The participant to create
     * @throws AlreadyExistsException - If username already taken
     */
    public void createParticipant(Long eventId, Participant participant) {
        if (!this.isExist(eventId, participant.getUsername())) {
            ParticipantEntity entity = participantMapper.mapToEntity(participant);
            entity.setEventId(eventId);
            participantDao.save(entity);
        } else {
            throw new AlreadyExistsException("Participant with name '" + participant.getUsername() + "' already exist");
        }
    }

    //=========================================================================
    //          Update
    //=========================================================================

    /**
     * Update an existing element
     *
     * @param participantId - Identifiers of the participant to update
     * @param participant   - The participant to save
     * @return The updated participant.
     * @throws NotFoundException - If not found
     */
    public Participant updateParticipant(Long participantId, Participant participant) {
        ParticipantEntity entity = this.findEntity(participantId);
        participantMapper.updateEntity(entity, participant);
        return participant;
    }

    //=========================================================================
    //          Delete
    //=========================================================================

    /**
     * Delete an existing element
     *
     * @param participantId - Identifiers of the participant to delete
     * @throws NotFoundException - If not found
     */
    public void deleteParticipant(Long participantId) {
        ParticipantEntity entity = this.findEntity(participantId);
        participantDao.delete(entity);
    }

    //=========================================================================
    //          Control
    //=========================================================================

    /**
     * Define if a participant exist with this name
     *
     * @param eventId  - Identifiers of the element to check
     * @param username - Name of the participant to find
     * @return true if exists, false otherwise
     * @throws NotFoundException - If not found
     */
    private boolean isExist(Long eventId, String username) {
        return participantDao.findParticipant(eventId, username) != null;
    }

}
