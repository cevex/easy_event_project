package com.cevex.easyevent.springmvc.app.service;

import com.cevex.easyevent.springmvc.app.dao.ContributionDao;
import com.cevex.easyevent.springmvc.app.dao.entity.ContributionEntity;
import com.cevex.easyevent.springmvc.app.mapper.ContributionMapper;
import com.cevex.easyevent.springmvc.app.model.Contribution;
import com.cevex.easyevent.springmvc.share.framework.AbstractService;
import com.cevex.easyevent.springmvc.share.framework.error.exception.AlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ContributionService extends AbstractService<Contribution, ContributionEntity> {

    //=========================================================================
    //          Constructor
    //=========================================================================

    @Autowired
    public ContributionService(ContributionDao contributionDao, ContributionMapper contributionMapper) {
        super(contributionDao, contributionMapper);
    }

    //=========================================================================
    //          Create
    //=========================================================================

    public void createContribution(Contribution contribution) {
        if (!super.isExist(contribution.getId())) {
            super.create(contribution);
        } else {
            throw new AlreadyExistsException("Contribution: id=" + contribution.getId() + "=> already exist");
        }
    }

}
