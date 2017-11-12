package com.cevex.easyevent.springmvc.app.service;

import com.cevex.easyevent.springmvc.app.dao.ExpenseDao;
import com.cevex.easyevent.springmvc.app.dao.entity.ExpenseEntity;
import com.cevex.easyevent.springmvc.app.mapper.ExpenseMapper;
import com.cevex.easyevent.springmvc.app.model.Expense;
import com.cevex.easyevent.springmvc.app.model.ExpenseFull;
import com.cevex.easyevent.springmvc.share.framework.error.exception.AlreadyExistsException;
import com.cevex.easyevent.springmvc.share.framework.error.exception.EmptyException;
import com.cevex.easyevent.springmvc.share.framework.error.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

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
    //          Retrieve List
    //=========================================================================

    /**
     * Retrieve all Expenses of a specific event.
     *
     * @param eventId - The event holding the expenses
     * @return The expenses List if found, null otherwise
     */
    public List<Expense> getExpenseList(Long eventId) {
        List<ExpenseEntity> entityList = expenseDao.findList(eventId);
        if (entityList == null || entityList.size() <= 0) {
            throw new EmptyException("No expenses for [eventId= " + eventId + "]");
        }
        return expenseMapper.mapToModelList(entityList);
    }

    //=========================================================================
    //          Retrieve Single
    //=========================================================================

    /**
     * Retrieve a Expense by username and event.
     *
     * @param expenseId - Unique identifier for expense
     * @return The expense if found
     */
    public Expense getExpense(Long expenseId) {
        ExpenseEntity entity = expenseDao.find(expenseId);
        if (entity == null) {
            throw new NotFoundException("No expense with id=" + expenseId);
        }
        return expenseMapper.mapToModel(entity);
    }

    /**
     * Retrieve an Expense by username and event.
     *
     * @param expenseId - Unique identifier for expense
     * @return The expense if found
     */
    public Expense getFullExpense(Long expenseId) {
        //TODO : Implement service getFullExpense
        throw new NotImplementedException();
    }

    //=========================================================================
    //          Create
    //=========================================================================

    /**
     * Create a new expense.
     *
     * @param eventId - Identifiers of the element to check
     * @param expense - The expense to create
     * @throws AlreadyExistsException - If username already taken
     */
    public void createExpense(Long eventId, Expense expense) {
        ExpenseEntity entity = expenseMapper.mapToEntity(expense);
        entity.setEventId(eventId);
        expenseDao.save(entity);
    }

    //=========================================================================
    //          Update
    //=========================================================================

    /**
     * Update an existing element
     *
     * @param expenseId - Unique identifier for expense
     * @param expense   - The expense to save
     * @return The updated expense.
     * @throws NotFoundException - If not found
     */
    public Expense updateExpense(Long expenseId, Expense expense) {
        ExpenseEntity entity = expenseDao.find(expenseId);
        expenseMapper.updateEntity(entity, expense);
        return expense;
    }

    /**
     * Update an existing element
     *
     * @param expenseId - Unique identifier for expense
     * @param expense   - The expense to save
     * @return The updated expense.
     * @throws NotFoundException - If not found
     */
    public ExpenseFull updateFullExpense(Long expenseId, Expense expense) {
        //TODO : Implement service updateFullExpense
        throw new NotImplementedException();
    }

    //=========================================================================
    //          Delete
    //=========================================================================

    /**
     * Delete an existing element
     *
     * @param expenseId - Unique identifier for expense
     * @throws NotFoundException - If not found
     */
    public void deleteExpense(Long expenseId) {
        ExpenseEntity entity = expenseDao.find(expenseId);
        expenseDao.delete(entity);
    }

}
