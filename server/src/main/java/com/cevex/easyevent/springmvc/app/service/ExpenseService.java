package com.cevex.easyevent.springmvc.app.service;

import com.cevex.easyevent.springmvc.app.dao.ExpenseDao;
import com.cevex.easyevent.springmvc.app.dao.entity.ExpenseEntity;
import com.cevex.easyevent.springmvc.app.mapper.ExpenseMapper;
import com.cevex.easyevent.springmvc.app.model.Expense;
import com.cevex.easyevent.springmvc.share.framework.AbstractService;
import com.cevex.easyevent.springmvc.share.framework.error.exception.AlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ExpenseService extends AbstractService<Expense, ExpenseEntity> {

    //=========================================================================
    //          Constructor
    //=========================================================================

    @Autowired
    public ExpenseService(ExpenseDao expenseDao, ExpenseMapper expenseMapper) {
        super(expenseDao, expenseMapper);
    }

    //=========================================================================
    //          Create
    //=========================================================================

    public void createExpense(Expense expense) {
        if (!super.isExist(expense.getId())) {
            super.create(expense);
        } else {
            throw new AlreadyExistsException("Expense: id=" + expense.getId() + " already exist");
        }
    }

}
