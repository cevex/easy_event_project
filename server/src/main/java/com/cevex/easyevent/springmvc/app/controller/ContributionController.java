package com.cevex.easyevent.springmvc.app.controller;

import com.cevex.easyevent.springmvc.app.model.Contribution;
import com.cevex.easyevent.springmvc.app.service.ContributionService;
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
public class ContributionController extends RestControllerValidator {

    @Autowired
    private ContributionService contributionService;

    //=============================================================================================
    //                          Retrieve All Contributions
    //=============================================================================================

    @RequestMapping(
            value = "/contributions", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity<List<Contribution>> getAllContributions() {
        List<Contribution> contributions = contributionService.getAllContributions();
        if (contributions.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(contributions, HttpStatus.OK);
    }

    //=============================================================================================
    //                          Retrieve Single Contribution
    //=============================================================================================

    @RequestMapping(
            value = "/contributions/{contribution_id}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity<Contribution> getContribution(@PathVariable("contribution_id") long id) {
        System.out.println("Fetching Contribution with id " + id);
        Contribution contribution = contributionService.getContribution(id);
        return new ResponseEntity<>(contribution, HttpStatus.OK);
    }

    //=============================================================================================
    //                          Create Single Contribution
    //=============================================================================================

    @RequestMapping(
            value = "/contributions", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity<Void> createContribution(@RequestBody @Valid Contribution contribution, BindingResult bindingResult, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating Contribution " + contribution.getId());

        validateResource(bindingResult);

        contributionService.createContribution(contribution);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/contributions/{id}").buildAndExpand(contribution.getId()).toUri());

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    //=============================================================================================
    //                          Update Single Contribution
    //=============================================================================================

    @RequestMapping(
            value = "/contributions/{contribution_id}", method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity<Contribution> updateContribution(@PathVariable("contribution_id") long id, @RequestBody @Valid Contribution contribution, BindingResult bindingResult) {
        System.out.println("Updating Contribution " + id);
        validateResource(bindingResult);
        Contribution updatedContribution = contributionService.updateContribution(id, contribution);
        return new ResponseEntity<>(updatedContribution, HttpStatus.OK);
    }

    //=============================================================================================
    //                          Delete an Contribution
    //=============================================================================================

    @RequestMapping(
            value = "/contributions/{contribution_id}", method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity<Void> deleteContribution(@PathVariable("contribution_id") long id) {
        System.out.println("Fetching & Deleting Contribution with id " + id);
        contributionService.deleteContribution(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}