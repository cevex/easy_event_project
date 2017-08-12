package com.cevex.easyevent.springmvc.app.service;

import com.cevex.easyevent.springmvc.app.dao.ExpenseDao;
import com.cevex.easyevent.springmvc.app.dao.entity.ExpenseEntity;
import com.cevex.easyevent.springmvc.app.mapper.ExpenseMapper;
import com.cevex.easyevent.springmvc.app.model.Expense;
import com.cevex.easyevent.springmvc.share.rest.error.exception.AlreadyExistsException;
import com.cevex.easyevent.springmvc.share.rest.error.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ExpenseService {

    //=========================================================================
    //          Attributes
    //=========================================================================

    private ExpenseDao expenseDao;

    private ExpenseMapper expenseMapper;

    //=========================================================================
    //          Constructor
    //=========================================================================

    @Autowired
    public ExpenseService(ExpenseDao expenseDao, ExpenseMapper expenseMapper) {
        this.expenseDao = expenseDao;
        this.expenseMapper = expenseMapper;
    }

    //=========================================================================
    //          Control
    //=========================================================================

    private boolean isExpenseExist(Long id) {
        return id != null && this.findExpense(id) != null;
    }

    //=========================================================================
    //          Retrieve
    //=========================================================================

    public List<Expense> getAllExpenses() {
        List<ExpenseEntity> expenses = expenseDao.findAllExpenses();
        return expenseMapper.mapExpenseList(expenses);
    }

    public Expense getExpense(long id) {
        ExpenseEntity expenseEntity = findExpense(id);
        return expenseMapper.mapExpense(expenseEntity);
    }

    private ExpenseEntity findExpense(long id) {
        ExpenseEntity expenseEntity = expenseDao.findExpense(id);

        if (expenseEntity == null) {
            throw new NotFoundException("Expense: id=" + id + "=> doesn't exist");
        }

        return expenseEntity;
    }

    //=========================================================================
    //          Create
    //=========================================================================

    public void createExpense(Expense expense) {
        if (!isExpenseExist(expense.getId())) {
            expenseDao.saveExpense(expenseMapper.mapExpense(expense));
        } else {
            throw new AlreadyExistsException("Expense: id=" + expense.getId() + "=> already exist");
        }
    }

    //=========================================================================
    //          Update
    //=========================================================================

    public Expense updateExpense(long id, Expense expense) {
        ExpenseEntity entity = findExpense(id);
        expenseMapper.updateExpenseEntity(entity, expense);
        return expense;
    }

    //=========================================================================
    //          Delete
    //=========================================================================

    public void deleteExpense(long id) {
        if (isExpenseExist(id)) {
            expenseDao.deleteExpense(id);
        }
    }

}
