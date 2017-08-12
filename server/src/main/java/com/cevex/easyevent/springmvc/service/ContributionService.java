package com.cevex.easyevent.springmvc.service;

import com.cevex.easyevent.springmvc.dao.ContributionDao;
import com.cevex.easyevent.springmvc.dao.entity.ContributionEntity;
import com.cevex.easyevent.springmvc.common.error.exception.AlreadyExistsException;
import com.cevex.easyevent.springmvc.common.error.exception.NotFoundException;
import com.cevex.easyevent.springmvc.mapper.ContributionMapper;
import com.cevex.easyevent.springmvc.model.Contribution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ContributionService {

    private ContributionDao contributionDao;

    private ContributionMapper contributionMapper;

    @Autowired
    public ContributionService(ContributionDao contributionDao, ContributionMapper contributionMapper) {
        this.contributionDao = contributionDao;
        this.contributionMapper = contributionMapper;
    }

    private boolean isContributionExist(Long id) {
        return id != null && contributionDao.findContribution(id) != null;
    }

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

    public void createContribution(Contribution contribution) {
        if (!isContributionExist(contribution.getId())) {
            contributionDao.saveContribution(contributionMapper.mapContribution(contribution));
        } else {
            throw new AlreadyExistsException("Contribution: id=" + contribution.getId() + "=> already exist");
        }
    }

    public Contribution updateContribution(long id, Contribution contribution) {
        ContributionEntity entity = findContribution(id);
        contributionMapper.updateContributionEntity(entity, contribution);
        return contribution;
    }

    public void deleteContribution(long id) {
        if (isContributionExist(id)) {
            contributionDao.deleteContribution(id);
        }
    }

}
