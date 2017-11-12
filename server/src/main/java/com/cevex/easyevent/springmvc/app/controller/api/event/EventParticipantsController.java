package com.cevex.easyevent.springmvc.app.controller.api.event;

import com.cevex.easyevent.springmvc.app.model.Participant;
import com.cevex.easyevent.springmvc.app.model.ParticipantFull;
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
@RequestMapping(value = "/events/{event_id}/participants")
public class EventParticipantsController extends RestControllerValidator {

    @Autowired
    private ParticipantService participantService;

    //=============================================================================================
    //                          Retrieve All EventDto
    //=============================================================================================

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Participant>> getParticipants(@PathVariable("event_id") Long eventId) {
        List<Participant> events = participantService.getParticipantList(eventId);
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    //=============================================================================================
    //                          Create Single Participant
    //=============================================================================================

    @RequestMapping(
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity<Void> createParticipant(@PathVariable("event_id") long eventId,
                                                  @RequestBody @Valid Participant participant,
                                                  BindingResult bindingResult, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating Participant for [event:" + eventId + "]");

        validateResource(bindingResult);
        participantService.createParticipant(eventId, participant);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/participants/{id}").buildAndExpand(participant.getId()).toUri());

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }
}