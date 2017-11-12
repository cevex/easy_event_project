package com.cevex.easyevent.springmvc.app.controller.api.expenses;

import com.cevex.easyevent.springmvc.app.model.Expense;
import com.cevex.easyevent.springmvc.app.model.ExpenseFull;
import com.cevex.easyevent.springmvc.app.service.ExpenseService;
import com.cevex.easyevent.springmvc.share.framework.RestControllerValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/expenses/{expense_id}")
public class ExpenseController extends RestControllerValidator {

    @Autowired
    private ExpenseService expenseService;

    //=============================================================================================
    //                          Retrieve Single Expense
    //=============================================================================================

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Expense> getExpense(
            @PathVariable("expense_id") Long expenseId,
            @RequestParam(value = "full", required = false) Boolean full) {

        System.out.println("Fetching Expense with [id: " + expenseId + "]");

        Expense expense;
        if (full != null && full) {
            expense = expenseService.getFullExpense(expenseId);
        } else {
            expense = expenseService.getExpense(expenseId);
        }

        return new ResponseEntity<>(expense, HttpStatus.OK);
    }

    //=============================================================================================
    //                          Update Single Expense
    //=============================================================================================

    @RequestMapping(method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Expense> updateExpense(
            @PathVariable("expense_id") Long expenseId,
            @RequestBody @Valid ExpenseFull expense, BindingResult bindingResult) {

        System.out.println("Updating Expense with => [id:" + expenseId + "]");
        validateResource(bindingResult);

        Expense updatedExpense;
        if (expense.getContributionList() == null || expense.getContributionList().isEmpty()) {
            updatedExpense = expenseService.updateExpense(expenseId, expense);
        } else {
            updatedExpense = expenseService.updateFullExpense(expenseId, expense);
        }

        return new ResponseEntity<>(updatedExpense, HttpStatus.OK);
    }

    //=============================================================================================
    //                          Delete an Expense
    //=============================================================================================

    @RequestMapping(method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Void> deleteExpense(@PathVariable("expense_id") Long expenseId) {
        System.out.println("Fetching & Deleting Expense with => [id:" + expenseId + "]");
        expenseService.deleteExpense(expenseId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}