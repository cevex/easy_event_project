package com.cevex.easyevent.springmvc.app.controller;

import com.cevex.easyevent.springmvc.app.model.Expense;
import com.cevex.easyevent.springmvc.app.service.ExpenseService;
import com.cevex.easyevent.springmvc.share.framework.RestControllerValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/events/{event_id}")
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
    public ResponseEntity<List<Expense>> getExpenseList(@PathVariable("event_id") Long eventId) {

        System.out.println("Fetching Expense => [event:" + eventId + "]");

        List<Expense> expenses = expenseService.getExpenseList(eventId);
        if (expenses.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(expenses, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/participants/{username}/expenses", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity<List<Expense>> getExpenseListForParticipant(@PathVariable("event_id") Long eventId,
                                                                      @PathVariable("username") String username) {

        System.out.println("Fetching Expense for participant => [event:" + eventId + "; username:" + username + "]");

        List<Expense> expenses = expenseService.getExpenseListOfParticipant(eventId, username);
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
    public ResponseEntity<Expense> getExpense(@PathVariable("event_id") Long eventId,
                                              @PathVariable("expense_id") Long expenseId) {
        System.out.println("Fetching Expense with id => [event:" + eventId + "; expense:" + expenseId + "]");
        Expense expense = expenseService.getExpense(eventId, expenseId);
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
    public ResponseEntity<Void> createExpense(@PathVariable("event_id") long eventId,
                                              @RequestBody @Valid Expense expense,
                                              BindingResult bindingResult, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating Expense => [event:" + eventId + "]");

        validateResource(bindingResult);
        expenseService.createExpense(eventId, expense);

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
    public ResponseEntity<Expense> updateExpense(@PathVariable("event_id") Long eventId,
                                                 @PathVariable("expense_id") Long expenseId,
                                                 @RequestBody @Valid Expense expense, BindingResult bindingResult) {

        System.out.println("Updating Expense with id => [event:" + eventId + "; expense:" + expenseId + "]");
        validateResource(bindingResult);
        Expense updatedExpense = expenseService.updateExpense(eventId, expenseId, expense);

        return new ResponseEntity<>(updatedExpense, HttpStatus.OK);
    }

    //=============================================================================================
    //                          Delete an Expense
    //=============================================================================================

    @RequestMapping(
            value = "/expenses/{expense_id}", method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity<Void> deleteExpense(@PathVariable("event_id") Long eventId,
                                              @PathVariable("expense_id") Long expenseId) {
        System.out.println("Fetching & Deleting Expense with id => [event:" + eventId + "; expense:" + expenseId + "]");
        expenseService.deleteExpense(eventId, expenseId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}