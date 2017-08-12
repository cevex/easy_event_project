package com.cevex.easyevent.springmvc.app.mapper;

import com.cevex.easyevent.springmvc.app.dao.entity.ExpenseEntity;
import com.cevex.easyevent.springmvc.app.model.Expense;
import com.cevex.easyevent.springmvc.share.framework.AbstractMapper;
import org.joda.time.LocalDateTime;
import org.springframework.stereotype.Service;

@Service
public class ExpenseMapper extends AbstractMapper<Expense, ExpenseEntity> {

    //=========================================================================
    //          DAO -> Model
    //=========================================================================


    @Override
    public Expense mapToModel(ExpenseEntity entity) {
        Expense expense = new Expense();

        expense.setId(entity.getId());
        expense.setLabel(entity.getLabel());
        expense.setEventId(entity.getEventId());
        expense.setDate(entity.getDate().toDateTime());

        return expense;
    }

    //=========================================================================
    //          Model -> DAO
    //=========================================================================

    @Override
    public void updateEntity(ExpenseEntity entity, Expense model) {
        entity.setEventId(model.getEventId());
        entity.setLabel(model.getLabel());
        entity.setDate(new LocalDateTime(model.getDate()));
    }
}
