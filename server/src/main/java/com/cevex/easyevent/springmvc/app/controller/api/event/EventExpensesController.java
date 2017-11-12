package com.cevex.easyevent.springmvc.app.controller.api.event;

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
@RequestMapping(value = "/events/{event_id}/expenses")
public class EventExpensesController extends RestControllerValidator {

    @Autowired
    private ExpenseService expenseService;

    //=============================================================================================
    //                          Retrieve All Event
    //=============================================================================================

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Expense>> getExpenses(@PathVariable("event_id") Long eventId) {
        System.out.println("Fetching Expense For => [event:" + eventId + "]");
        List<Expense> events = expenseService.getExpenseList(eventId);
        if (events.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    //=============================================================================================
    //                          Create Single Expense
    //=============================================================================================

    @RequestMapping(
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity<Void> createExpense(@PathVariable("event_id") long eventId,
                                              @RequestBody @Valid Expense expense,
                                              BindingResult bindingResult, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating Expense For => [event:" + eventId + "]");

        validateResource(bindingResult);
        expenseService.createExpense(eventId, expense);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/expenses/{id}").buildAndExpand(expense.getId()).toUri());

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }
}