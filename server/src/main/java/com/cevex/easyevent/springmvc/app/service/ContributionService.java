package com.cevex.easyevent.springmvc.app.service;

import com.cevex.easyevent.springmvc.app.dao.ContributionDao;
import com.cevex.easyevent.springmvc.app.dao.entity.ContributionEntity;
import com.cevex.easyevent.springmvc.app.mapper.ContributionMapper;
import com.cevex.easyevent.springmvc.app.model.Contribution;
import com.cevex.easyevent.springmvc.share.rest.error.exception.AlreadyExistsException;
import com.cevex.easyevent.springmvc.share.rest.error.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ContributionService {

    //=========================================================================
    //          Attributes
    //=========================================================================

    private ContributionDao contributionDao;

    private ContributionMapper contributionMapper;

    //=========================================================================
    //          Constructor
    //=========================================================================

    @Autowired
    public ContributionService(ContributionDao contributionDao, ContributionMapper contributionMapper) {
        this.contributionDao = contributionDao;
        this.contributionMapper = contributionMapper;
    }

    //=========================================================================
    //          Control
    //=========================================================================

    private boolean isContributionExist(Long id) {
        return id != null && this.findContribution(id) != null;
    }

    //=========================================================================
    //          Retrieve
    //=========================================================================

    public List<Contribution> getAllContributions() {
        List<ContributionEntity> contributions = contributionDao.findAllContributions();
        return contributionMapper.mapContributionList(contributions);
    }

    public Contribution getContribution(long id) {
        ContributionEntity contributionEntity = findContribution(id);
        return contributionMapper.mapContribution(contributionEntity);
    }

    private ContributionEntity findContribution(long id) {
        ContributionEntity contributionEntity = contributionDao.findContribution(id);

        if (contributionEntity == null) {
            throw new NotFoundException("Contribution: id=" + id + "=> doesn't exist");
        }

        return contributionEntity;
    }

    //=========================================================================
    //          Create
    //=========================================================================

    public void createContribution(Contribution contribution) {
        if (!isContributionExist(contribution.getId())) {
            contributionDao.saveContribution(contributionMapper.mapContribution(contribution));
        } else {
            throw new AlreadyExistsException("Contribution: id=" + contribution.getId() + "=> already exist");
        }
    }

    //=========================================================================
    //          Update
    //=========================================================================

    public Contribution updateContribution(long id, Contribution contribution) {
        ContributionEntity entity = findContribution(id);
        contributionMapper.updateContributionEntity(entity, contribution);
        return contribution;
    }

    //=========================================================================
    //          Delete
    //=========================================================================

    public void deleteContribution(long id) {
        if (isContributionExist(id)) {
            contributionDao.deleteContribution(id);
        }
    }

}
