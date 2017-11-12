package com.cevex.easyevent.springmvc.app.mapper;

import com.cevex.easyevent.springmvc.app.dao.entity.ExpenseEntity;
import com.cevex.easyevent.springmvc.app.dao.entity.ExpenseFullEntity;
import com.cevex.easyevent.springmvc.app.model.Expense;
import com.cevex.easyevent.springmvc.app.model.ExpenseFull;
import com.cevex.easyevent.springmvc.share.framework.AbstractMapper;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExpenseMapper extends AbstractMapper<Expense, ExpenseEntity> {

    @Autowired
    private ContributionMapper contributionMapper;

    //=========================================================================
    //          DAO -> ModelElement
    //=========================================================================

    @Override
    public Expense mapToModel(ExpenseEntity entity) {
        Expense expense = new Expense();
        updateModel(expense, entity);
        return expense;
    }

    public ExpenseFull mapToModelFull(ExpenseFullEntity entity) {
        ExpenseFull expense = new ExpenseFull();
        updateModel(expense, entity);
        expense.setContributionList(contributionMapper.mapToModelList(entity.getContributionList()));
        return expense;
    }

    private void updateModel(Expense expense, ExpenseEntity entity) {
        expense.setId(entity.getId());
        expense.setLabel(entity.getLabel());
        expense.setDate(entity.getDate().toDateTime());
    }

    //=========================================================================
    //          ModelElement -> DAO
    //=========================================================================

    @Override
    public ExpenseEntity mapToEntity(Expense expense) {
        ExpenseEntity entity = new ExpenseEntity();
        entity.setId(expense.getId());
        updateEntity(entity, expense);
        return entity;
    }

    public ExpenseFullEntity mapToEntityFull(ExpenseFull expense) {
        ExpenseFullEntity entity = new ExpenseFullEntity();
        entity.setId(expense.getId());
        updateEntity(entity, expense);
        entity.setContributionList(contributionMapper.mapToEntityList(expense.getContributionList()));
        return entity;
    }

    @Override
    public void updateEntity(ExpenseEntity entity, Expense model) {
        entity.setLabel(model.getLabel());
        entity.setDate(new LocalDateTime(model.getDate()));
    }
}
