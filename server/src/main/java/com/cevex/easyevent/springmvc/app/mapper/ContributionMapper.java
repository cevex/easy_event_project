package com.cevex.easyevent.springmvc.app.mapper;

import com.cevex.easyevent.springmvc.app.dao.entity.ContributionEntity;
import com.cevex.easyevent.springmvc.app.model.Contribution;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContributionMapper {

    //=========================================================================
    //          DAO -> Model
    //=========================================================================

    public List<Contribution> mapContributionList(List<ContributionEntity> entityList) {
        List<Contribution> contributionList = new ArrayList<>();
        for (ContributionEntity entity : entityList) {
            contributionList.add(mapContribution(entity));
        }
        return contributionList;
    }

    public Contribution mapContribution(ContributionEntity entity) {
        Contribution contribution = new Contribution();

        contribution.setId(entity.getId());
        contribution.setAmount(entity.getAmount());
        contribution.setExpenseId(entity.getExpenseId());
        contribution.setParticipantId(entity.getParticipantId());

        return contribution;
    }

    //=========================================================================
    //          Model -> DAO
    //=========================================================================


    public ContributionEntity mapContribution(Contribution contribution) {
        ContributionEntity entity = new ContributionEntity();

        entity.setId(contribution.getId());
        updateContributionEntity(entity, contribution);

        return entity;
    }

    public void updateContributionEntity(ContributionEntity entity, Contribution contribution) {
        entity.setAmount(contribution.getAmount());
        entity.setExpenseId(contribution.getExpenseId());
        entity.setParticipantId(contribution.getParticipantId());
    }
}
