package com.cevex.easyevent.springmvc.app.controller;

import com.cevex.easyevent.springmvc.app.model.Expense;
import com.cevex.easyevent.springmvc.app.service.ExpenseService;
import com.cevex.easyevent.springmvc.share.rest.RestControllerValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping(value = "")
public class ExpenseController extends RestControllerValidator {

    @Autowired
    private ExpenseService expenseService;

    //=============================================================================================
    //                          Retrieve All Expense
    //=============================================================================================

    @RequestMapping(
            value = "/expenses", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity<List<Expense>> getAllExpenses() {
        List<Expense> expenses = expenseService.getAllExpenses();
        if (expenses.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(expenses, HttpStatus.OK);
    }

    //=============================================================================================
    //                          Retrieve Single Expense
    //=============================================================================================

    @RequestMapping(
            value = "/expenses/{expense_id}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity<Expense> getExpense(@PathVariable("expense_id") long id) {
        System.out.println("Fetching Expense with id " + id);
        Expense expense = expenseService.getExpense(id);
        return new ResponseEntity<>(expense, HttpStatus.OK);
    }

    //=============================================================================================
    //                          Create Single Expense
    //=============================================================================================

    @RequestMapping(
            value = "/expenses", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity<Void> createExpense(@RequestBody @Valid Expense expense, BindingResult bindingResult, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating Expense " + expense.getId());

        validateResource(bindingResult);

        expenseService.createExpense(expense);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/expenses/{id}").buildAndExpand(expense.getId()).toUri());

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    //=============================================================================================
    //                          Update Single Expense
    //=============================================================================================

    @RequestMapping(
            value = "/expenses/{expense_id}", method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity<Expense> updateExpense(@PathVariable("expense_id") long id, @RequestBody @Valid Expense expense, BindingResult bindingResult) {
        System.out.println("Updating Expense " + id);
        validateResource(bindingResult);
        Expense updatedExpense = expenseService.updateExpense(id, expense);
        return new ResponseEntity<>(updatedExpense, HttpStatus.OK);
    }

    //=============================================================================================
    //                          Delete an Expense
    //=============================================================================================

    @RequestMapping(
            value = "/expenses/{expense_id}", method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity<Void> deleteExpense(@PathVariable("expense_id") long id) {
        System.out.println("Fetching & Deleting Expense with id " + id);
        expenseService.deleteExpense(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}