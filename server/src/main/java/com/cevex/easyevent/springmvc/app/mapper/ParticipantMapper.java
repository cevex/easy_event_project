package com.cevex.easyevent.springmvc.app.mapper;

import com.cevex.easyevent.springmvc.app.dao.entity.ParticipantEntity;
import com.cevex.easyevent.springmvc.app.model.Participant;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ParticipantMapper {

    //=========================================================================
    //          DAO -> Model
    //=========================================================================

    public List<Participant> mapParticipantList(List<ParticipantEntity> entityList) {
        List<Participant> participantList = new ArrayList<>();
        for (ParticipantEntity entity : entityList) {
            participantList.add(mapParticipant(entity));
        }
        return participantList;
    }

    public Participant mapParticipant(ParticipantEntity entity) {
        Participant participant = new Participant();

        participant.setId(entity.getId());
        participant.setEventId(entity.getEventId());
        participant.setUsername(entity.getUsername());

        return participant;
    }

    //=========================================================================
    //          Model -> DAO
    //=========================================================================

    public ParticipantEntity mapParticipant(Participant participant) {
        ParticipantEntity entity = new ParticipantEntity();

        entity.setId(participant.getId());
        updateParticipantEntity(entity, participant);

        return entity;
    }

    public void updateParticipantEntity(ParticipantEntity entity, Participant participant) {
        entity.setEventId(participant.getEventId());
        entity.setUsername(participant.getUsername());
    }
}
