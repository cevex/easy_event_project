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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping(value = "")
public class ParticipantController extends RestControllerValidator {

    @Autowired
    private ParticipantService participantService;

    //=============================================================================================
    //                          Retrieve All Participant
    //=============================================================================================

    @RequestMapping(
            value = "/participants", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity<List<Participant>> getAllParticipants() {
        List<Participant> participants = participantService.getAll();
        if (participants.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(participants, HttpStatus.OK);
    }

    //=============================================================================================
    //                          Retrieve Single Participant
    //=============================================================================================

    @RequestMapping(
            value = "/participants/{participant_id}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity<Participant> getParticipant(@PathVariable("participant_id") long id) {
        System.out.println("Fetching Participant with id " + id);
        Participant participant = participantService.get(id);
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
    public ResponseEntity<Void> createParticipant(@RequestBody @Valid Participant participant, BindingResult bindingResult, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating Participant " + participant.getId());

        validateResource(bindingResult);

        participantService.createParticipant(participant);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/participants/{id}").buildAndExpand(participant.getId()).toUri());

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    //=============================================================================================
    //                          Update Single Participant
    //=============================================================================================

    @RequestMapping(
            value = "/participants/{participant_id}", method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity<Participant> updateParticipant(@PathVariable("participant_id") long id, @RequestBody @Valid Participant participant, BindingResult bindingResult) {
        System.out.println("Updating Participant " + id);
        validateResource(bindingResult);
        Participant updatedParticipant = participantService.update(id, participant);
        return new ResponseEntity<>(updatedParticipant, HttpStatus.OK);
    }

    //=============================================================================================
    //                          Delete an Participant
    //=============================================================================================

    @RequestMapping(
            value = "/participants/{participant_id}", method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity<Void> deleteParticipant(@PathVariable("participant_id") long id) {
        System.out.println("Fetching & Deleting Participant with id " + id);
        participantService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}