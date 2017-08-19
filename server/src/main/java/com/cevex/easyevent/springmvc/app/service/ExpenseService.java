package com.cevex.easyevent.springmvc.app.service;

import com.cevex.easyevent.springmvc.app.dao.ExpenseDao;
import com.cevex.easyevent.springmvc.app.dao.entity.ExpenseEntity;
import com.cevex.easyevent.springmvc.app.mapper.ExpenseMapper;
import com.cevex.easyevent.springmvc.app.model.Expense;
import com.cevex.easyevent.springmvc.share.framework.error.exception.AlreadyExistsException;
import com.cevex.easyevent.springmvc.share.framework.error.exception.NotFoundException;
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
    //          Retrieve List
    //=========================================================================

    /**
     * Retrieve all Expenses of a specific event.
     *
     * @param eventId - The event holding the expenses
     * @return The expenses List if found, null otherwise
     */
    public List<Expense> getExpenseList(Long eventId) {
        List<ExpenseEntity> entityList = this.findEntityList(eventId, null);
        return expenseMapper.mapToModelList(entityList);
    }

    /**
     * Retrieve an element by identifier
     *
     * @return All the Element
     */
    public List<Expense> getExpenseListOfParticipant(Long eventId, String username) {
        List<ExpenseEntity> entityList = this.findEntityList(eventId, username);
        return expenseMapper.mapToModelList(entityList);
    }

    /**
     * Retrieve a expense by name.
     *
     * @param eventId  - Search in this event.
     * @param username - Find expenses for this participant. (OPTIONAL)
     * @return The entity if found
     * @throws NotFoundException - If not found
     */
    private List<ExpenseEntity> findEntityList(Long eventId, String username) {
        List<ExpenseEntity> entityList = username == null ?
                expenseDao.findExpenseList(eventId) :
                expenseDao.findExpenseListOfParticipant(eventId, username);
        validateEntityList(entityList);
        return entityList;
    }


    private void validateEntityList(List<ExpenseEntity> entityList) {
        if (entityList == null || entityList.size() <= 0) {
            throw new NotFoundException("Expense List not found");
        }
    }

    //=========================================================================
    //          Retrieve Single
    //=========================================================================

    /**
     * Retrieve a Expense by username and event.
     *
     * @param eventId   - Search in this event.
     * @param expenseId - Unique identifier for expense
     * @return The expense if found
     */
    public Expense getExpense(Long eventId, Long expenseId) {
        ExpenseEntity entity = findEntity(eventId, expenseId);
        return expenseMapper.mapToModel(entity);
    }

    /**
     * Find a expense entity by name
     *
     * @param eventId   - Search in this event.
     * @param expenseId - Unique identifier for expense
     * @return The entity if found
     * @throws NotFoundException - If not found
     */
    private ExpenseEntity findEntity(Long eventId, Long expenseId) {
        ExpenseEntity entity = expenseDao.findExpense(eventId, expenseId);
        if (entity == null) {
            throw new NotFoundException("No expense with id=" + expenseId + " under the event id=" + eventId);
        }
        return entity;
    }

    //=========================================================================
    //          Create
    //=========================================================================

    /**
     * Create a new expense
     *
     * @param eventId - Identifiers of the element to check
     * @param expense - The expense to create
     * @throws AlreadyExistsException - If username already taken
     */
    public void createExpense(Long eventId, Expense expense) {
        expenseDao.save(expenseMapper.mapToEntity(expense));
    }

    //=========================================================================
    //          Update
    //=========================================================================

    /**
     * Update an existing element
     *
     * @param eventId   - Identifiers of the element to check
     * @param expenseId - Unique identifier for expense
     * @param expense   - The expense to save
     * @return The updated expense.
     * @throws NotFoundException - If not found
     */
    public Expense updateExpense(Long eventId, Long expenseId, Expense expense) {
        ExpenseEntity entity = this.findEntity(eventId, expenseId);
        expenseMapper.updateEntity(entity, expense);
        return expense;
    }

    //=========================================================================
    //          Delete
    //=========================================================================

    /**
     * Delete an existing element
     *
     * @param eventId   - Identifiers of the event to search in
     * @param expenseId - Unique identifier for expense
     * @throws NotFoundException - If not found
     */
    public void deleteExpense(Long eventId, Long expenseId) {
        ExpenseEntity entity = this.findEntity(eventId, expenseId);
        expenseDao.delete(entity);
    }

}
