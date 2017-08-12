package com.cevex.easyevent.springmvc.app.mapper;

import com.cevex.easyevent.springmvc.app.dao.entity.ContributionEntity;
import com.cevex.easyevent.springmvc.app.model.Contribution;
import com.cevex.easyevent.springmvc.share.framework.AbstractMapper;
import org.springframework.stereotype.Service;

@Service
public class ContributionMapper extends AbstractMapper<Contribution, ContributionEntity> {

    //=========================================================================
    //          DAO -> Model
    //=========================================================================

    @Override
    public Contribution mapToModel(ContributionEntity entity) {
        Contribution contribution = new Contribution();

        contribution.setAmount(entity.getAmount());
        contribution.setExpenseId(entity.getExpenseId());
        contribution.setParticipantId(entity.getParticipantId());

        return contribution;
    }

    //=========================================================================
    //          Model -> DAO
    //=========================================================================

    @Override
    public void updateEntity(ContributionEntity entity, Contribution model) {
        entity.setAmount(model.getAmount());
    }
}
