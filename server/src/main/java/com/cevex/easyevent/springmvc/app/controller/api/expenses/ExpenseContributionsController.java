package com.cevex.easyevent.springmvc.app.controller.api.expenses;

import com.cevex.easyevent.springmvc.app.model.ContributionOfExpenses;
import com.cevex.easyevent.springmvc.app.model.Expense;
import com.cevex.easyevent.springmvc.app.model.ExpenseFull;
import com.cevex.easyevent.springmvc.app.model.ParticipantFull;
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
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/expenses/{expense_id}/contributions")
public class ExpenseContributionsController extends RestControllerValidator {

    @Autowired
    private ExpenseService expenseService;

    //=============================================================================================
    //                          Retrieve All Expense
    //=============================================================================================

    @RequestMapping(
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity<List<ContributionOfExpenses>> getContributions(
            @PathVariable("expense_id") Long expense) {
        System.out.println("Fetching Contribution for [event:" + expense + "]");
        //TODO : Implement resource ExpenseContributions
        throw new NotImplementedException();
    }

}