package com.cevex.easyevent.springmvc.app.mapper;

import com.cevex.easyevent.springmvc.app.dao.entity.ContributionEntity;
import com.cevex.easyevent.springmvc.app.model.ContributionOfExpenses;
import com.cevex.easyevent.springmvc.share.framework.AbstractMapper;
import org.springframework.stereotype.Service;

@Service
public class ContributionMapper extends AbstractMapper<ContributionOfExpenses, ContributionEntity> {

    //=========================================================================
    //          DAO -> ModelElement
    //=========================================================================

    public ContributionOfExpenses mapToModel(ContributionEntity entity) {
        ContributionOfExpenses contribution = new ContributionOfExpenses();

        contribution.setId(entity.getId());
        contribution.setAmount(entity.getAmount());

        return contribution;
    }

    //=========================================================================
    //          ModelElement -> DAO
    //=========================================================================


    @Override
    public void updateEntity(ContributionEntity entity, ContributionOfExpenses model) {
        entity.setAmount(model.getAmount());
    }
}
