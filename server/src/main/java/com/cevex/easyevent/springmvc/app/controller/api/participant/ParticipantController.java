package com.cevex.easyevent.springmvc.app.controller.api.participant;

import com.cevex.easyevent.springmvc.app.model.Participant;
import com.cevex.easyevent.springmvc.app.service.ParticipantService;
import com.cevex.easyevent.springmvc.share.framework.RestControllerValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/participants/{participant_id}")
public class ParticipantController extends RestControllerValidator {

    @Autowired
    private ParticipantService participantService;

    //=============================================================================================
    //                          Retrieve Single Participant
    //=============================================================================================

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Participant> getParticipant(
            @PathVariable("participant_id") Long participantId,
            @RequestParam(value = "full", required = false) Boolean full) {
        System.out.println("Fetching Participant with [id:" + participantId + "]");

        Participant participant;
        if (full != null && full) {
            participant = participantService.getFullParticipant(participantId);
        } else {
            participant = participantService.getParticipant(participantId);
        }

        return new ResponseEntity<>(participant, HttpStatus.OK);
    }

    //=============================================================================================
    //                          Update Single Participant
    //=============================================================================================

    @RequestMapping(method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Participant> updateParticipant(@PathVariable("participant_id") Long participantId,
                                                         @RequestBody @Valid Participant participant,
                                                         BindingResult bindingResult) {
        System.out.println("Updating Participant with [id:" + participantId + "]");
        validateResource(bindingResult);
        Participant updatedParticipant = participantService.updateParticipant(participantId, participant);
        return new ResponseEntity<>(updatedParticipant, HttpStatus.OK);
    }

    //=============================================================================================
    //                          Delete an Participant
    //=============================================================================================

    @RequestMapping(method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Void> deleteParticipant(@PathVariable("participant_id") Long participantId) {
        System.out.println("Fetching & Deleting Participant with [id:" + participantId + "]");
        participantService.deleteParticipant(participantId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}