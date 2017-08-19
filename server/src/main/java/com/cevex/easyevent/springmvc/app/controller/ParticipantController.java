package com.cevex.easyevent.springmvc.app.controller;

import com.cevex.easyevent.springmvc.app.model.Participant;
import com.cevex.easyevent.springmvc.app.service.ParticipantService;
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
public class ParticipantController extends RestControllerValidator {

    @Autowired
    private ParticipantService participantService;

    //=============================================================================================
    //                          Retrieve List of Participant
    //=============================================================================================

    @RequestMapping(
            value = "/participants", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity<List<Participant>> getParticipantList(@PathVariable("event_id") long eventId) {

        System.out.println("Fetching Participant => [event:" + eventId + "]");

        List<Participant> participants = participantService.getParticipantList(eventId);
        if (participants.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(participants, HttpStatus.OK);
    }


    @RequestMapping(
            value = "/expenses/{expense_id}/participants", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity<List<Participant>> getParticipantListOfExpense(@PathVariable("event_id") long eventId,
                                                                         @PathVariable("expense_id") long expenseId) {
        System.out.println("Fetching Participant => [event:" + eventId + "; expense:" + expenseId + "]");

        List<Participant> participants = participantService.getParticipantListOfExpense(eventId, expenseId);
        if (participants.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(participants, HttpStatus.OK);
    }

    //=============================================================================================
    //                          Retrieve Single Participant
    //=============================================================================================

    @RequestMapping(
            value = "/participants/{participant_username}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity<Participant> getParticipant(@PathVariable("event_id") long eventId,
                                                      @PathVariable("participant_username") String username) {
        System.out.println("Fetching Participant with id => [event:" + eventId + "; participant:" + username + "]");
        Participant participant = participantService.getParticipant(eventId, username);
        return new ResponseEntity<>(participant, HttpStatus.OK);
    }

    //=============================================================================================
    //                          Create Single Participant
    //=============================================================================================

    @RequestMapping(
            value = "/participants", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity<Void> createParticipant(@PathVariable("event_id") long eventId,
                                                  @RequestBody @Valid Participant participant,
                                                  BindingResult bindingResult, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating Participant => [event:" + eventId + "]");

        validateResource(bindingResult);
        participantService.createParticipant(eventId, participant);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/participants/{id}").buildAndExpand(participant.getId()).toUri());

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    //=============================================================================================
    //                          Update Single Participant
    //=============================================================================================

    @RequestMapping(
            value = "/participants/{username}", method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity<Participant> updateParticipant(@PathVariable("event_id") long eventId,
                                                         @PathVariable("username") String username,
                                                         @RequestBody @Valid Participant participant,
                                                         BindingResult bindingResult) {

        System.out.println("Updating Participant with id => [event:" + eventId + "; participant:" + username + "]");
        validateResource(bindingResult);
        Participant updatedParticipant = participantService.updateParticipant(eventId, username, participant);

        return new ResponseEntity<>(updatedParticipant, HttpStatus.OK);
    }

    //=============================================================================================
    //                          Delete an Participant
    //=============================================================================================

    @RequestMapping(
            value = "/participants/{participant_id}", method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity<Void> deleteParticipant(@PathVariable("event_id") long eventId,
                                                  @PathVariable("username") String username) {
        System.out.println("Fetching & Deleting Participant with id => [event:" + eventId + "; participant:" + username + "]");
        participantService.deleteParticipant(eventId, username);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}