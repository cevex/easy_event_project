package com.cevex.easyevent.springmvc.app.service;

import com.cevex.easyevent.springmvc.app.dao.ParticipantDao;
import com.cevex.easyevent.springmvc.app.dao.entity.ParticipantEntity;
import com.cevex.easyevent.springmvc.app.mapper.ParticipantMapper;
import com.cevex.easyevent.springmvc.app.model.Participant;
import com.cevex.easyevent.springmvc.share.framework.AbstractService;
import com.cevex.easyevent.springmvc.share.framework.error.exception.AlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ParticipantService extends AbstractService<Participant, ParticipantEntity> {

    //=========================================================================
    //          Constructor
    //=========================================================================

    @Autowired
    public ParticipantService(ParticipantDao participantDao, ParticipantMapper participantMapper) {
        super(participantDao, participantMapper);
    }

    //=========================================================================
    //          Create
    //=========================================================================

    public void createParticipant(Participant participant) {
        if (!super.isExist(participant.getId())) {
            super.create(participant);
        } else {
            throw new AlreadyExistsException("Participant: id=" + participant.getId() + "=> already exist");
        }
    }

}
