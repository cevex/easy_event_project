package com.cevex.easyevent.springmvc.mapper;

import com.cevex.easyevent.springmvc.dao.entity.ExpenseEntity;
import com.cevex.easyevent.springmvc.model.Expense;
import org.joda.time.LocalDateTime;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExpenseMapper {

    //=========================================================================
    //          DAO -> Model
    //=========================================================================

    public List<Expense> mapExpenseList(List<ExpenseEntity> entityList) {
        List<Expense> expenseList = new ArrayList<>();
        for (ExpenseEntity entity : entityList) {
            expenseList.add(mapExpense(entity));
        }
        return expenseList;
    }

    public Expense mapExpense(ExpenseEntity entity) {
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

    public ExpenseEntity mapExpense(Expense expense) {
        ExpenseEntity entity = new ExpenseEntity();

        entity.setId(expense.getId());
        updateExpenseEntity(entity, expense);

        return entity;
    }

    public void updateExpenseEntity(ExpenseEntity entity, Expense expense) {
        entity.setId(expense.getId());
        entity.setEventId(expense.getEventId());
        entity.setLabel(expense.getLabel());
        entity.setDate(new LocalDateTime(expense.getDate()));
    }
}
