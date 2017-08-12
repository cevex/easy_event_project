package com.cevex.easyevent.springmvc.service;

import com.cevex.easyevent.springmvc.dao.ParticipantDao;
import com.cevex.easyevent.springmvc.dao.entity.ParticipantEntity;
import com.cevex.easyevent.springmvc.error.exception.AlreadyExistsException;
import com.cevex.easyevent.springmvc.error.exception.NotFoundException;
import com.cevex.easyevent.springmvc.mapper.ParticipantMapper;
import com.cevex.easyevent.springmvc.model.Participant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ParticipantService {

    private ParticipantDao participantDao;

    private ParticipantMapper participantMapper;

    @Autowired
    public ParticipantService(ParticipantDao participantDao, ParticipantMapper participantMapper) {
        this.participantDao = participantDao;
        this.participantMapper = participantMapper;
    }

    private boolean isParticipantExist(Long id) {
        return id != null && participantDao.findParticipant(id) != null;
    }

    public List<Participant> getAllParticipants() {
        List<ParticipantEntity> participants = participantDao.findAllParticipants();
        return participantMapper.mapParticipantList(participants);
    }

    public Participant getParticipant(long id) {
        ParticipantEntity participantEntity = findParticipant(id);
        return participantMapper.mapParticipant(participantEntity);
    }

    private ParticipantEntity findParticipant(long id) {
        ParticipantEntity participantEntity = participantDao.findParticipant(id);

        if (participantEntity == null) {
            throw new NotFoundException("Participant: id=" + id + "=> doesn't exist");
        }

        return participantEntity;
    }

    public void createParticipant(Participant participant) {
        if (!isParticipantExist(participant.getId())) {
            participantDao.saveParticipant(participantMapper.mapParticipant(participant));
        } else {
            throw new AlreadyExistsException("Participant: id=" + participant.getId() + "=> already exist");
        }
    }

    public Participant updateParticipant(long id, Participant participant) {
        ParticipantEntity entity = findParticipant(id);
        participantMapper.updateParticipantEntity(entity, participant);
        return participant;
    }

    public void deleteParticipant(long id) {
        if (isParticipantExist(id)) {
            participantDao.deleteParticipant(id);
        }
    }

}
