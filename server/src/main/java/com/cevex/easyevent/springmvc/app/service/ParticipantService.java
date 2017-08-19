package com.cevex.easyevent.springmvc.app.service;

import com.cevex.easyevent.springmvc.app.dao.ParticipantDao;
import com.cevex.easyevent.springmvc.app.dao.entity.ParticipantEntity;
import com.cevex.easyevent.springmvc.app.mapper.ParticipantMapper;
import com.cevex.easyevent.springmvc.app.model.Participant;
import com.cevex.easyevent.springmvc.share.framework.error.exception.AlreadyExistsException;
import com.cevex.easyevent.springmvc.share.framework.error.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        List<ParticipantEntity> entityList = this.findEntityList(eventId, null);
        return participantMapper.mapToModelList(entityList);
    }

    /**
     * Retrieve an element by identifier
     *
     * @return All the Element
     */
    public List<Participant> getParticipantListOfExpense(Long eventId, Long expenseId) {
        List<ParticipantEntity> entityList = this.findEntityList(eventId, expenseId);
        return participantMapper.mapToModelList(entityList);
    }

    /**
     * Retrieve a participant by name.
     *
     * @param eventId   - Search in this event.
     * @param expenseId - Find participants for this expense. (OPTIONAL)
     * @return The entity if found
     * @throws NotFoundException - If not found
     */
    private List<ParticipantEntity> findEntityList(Long eventId, Long expenseId) {
        List<ParticipantEntity> entityList = expenseId == null ?
                participantDao.findParticipantList(eventId) :
                participantDao.findParticipantListOfExpense(eventId, expenseId);
        validateEntityList(entityList);
        return entityList;
    }


    private void validateEntityList(List<ParticipantEntity> entityList) {
        if (entityList == null || entityList.size() <= 0) {
            throw new NotFoundException("Participant List not found");
        }
    }

    //=========================================================================
    //          Retrieve Single
    //=========================================================================

    /**
     * Retrieve a Participant by username and event.
     *
     * @param eventId  - Search in this event.
     * @param username - The participants to retrieve.
     * @return The participant if found
     */
    public Participant getParticipant(Long eventId, String username) {
        ParticipantEntity entity = findEntity(eventId, username);
        return participantMapper.mapToModel(entity);
    }

    /**
     * Find a participant entity by name
     *
     * @param eventId  - Search in this event.
     * @param username - Name of the participant to find
     * @return The entity if found
     * @throws NotFoundException - If not found
     */
    private ParticipantEntity findEntity(Long eventId, String username) {
        ParticipantEntity entity = participantDao.findParticipant(eventId, username);
        if (entity == null) {
            throw new NotFoundException("Participant not found");
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
            participantDao.save(participantMapper.mapToEntity(participant));
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
     * @param eventId     - Identifiers of the element to check
     * @param username    - Name of the participant to find
     * @param participant - The participant to save
     * @return The updated participant.
     * @throws NotFoundException - If not found
     */
    public Participant updateParticipant(Long eventId, String username, Participant participant) {
        ParticipantEntity entity = this.findEntity(eventId, username);
        participantMapper.updateEntity(entity, participant);
        return participant;
    }

    //=========================================================================
    //          Delete
    //=========================================================================

    /**
     * Delete an existing element
     *
     * @param eventId  - Identifiers of the event to search in
     * @param username - Name of the participant to delete
     * @throws NotFoundException - If not found
     */
    public void deleteParticipant(Long eventId, String username) {
        ParticipantEntity entity = this.findEntity(eventId, username);
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
        return this.findEntity(eventId, username) != null;
    }

}
