package com.cevex.easyevent.springmvc.app.mapper;

import com.cevex.easyevent.springmvc.app.dao.entity.ParticipantEntity;
import com.cevex.easyevent.springmvc.app.model.Participant;
import com.cevex.easyevent.springmvc.share.framework.AbstractMapper;
import org.springframework.stereotype.Service;

@Service
public class ParticipantMapper extends AbstractMapper<Participant, ParticipantEntity> {

    //=========================================================================
    //          DAO -> ModelElement
    //=========================================================================

    @Override
    public Participant mapToModel(ParticipantEntity entity) {
        Participant participant = new Participant();

        participant.setId(entity.getId());
        participant.setUsername(entity.getUsername());

        return participant;
    }

    //=========================================================================
    //          ModelElement -> DAO
    //=========================================================================

    @Override
    public void updateEntity(ParticipantEntity entity, Participant model) {
        entity.setUsername(model.getUsername());

    }
}
