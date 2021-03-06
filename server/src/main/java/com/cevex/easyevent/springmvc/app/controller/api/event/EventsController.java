package com.cevex.easyevent.springmvc.app.controller.api.event;

import com.cevex.easyevent.springmvc.app.model.Event;
import com.cevex.easyevent.springmvc.app.service.EventService;
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
@RequestMapping(value = "/events")
public class EventsController extends RestControllerValidator {

    @Autowired
    private EventService eventService;

    //=============================================================================================
    //                          Retrieve All EventDto
    //=============================================================================================

    @RequestMapping(
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity<List<Event>> getAllEvents() {
        List<Event> events = eventService.getEventList();
        if (events.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    //=============================================================================================
    //                          Create EventDto
    //=============================================================================================

    @RequestMapping(
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity<Void> createEvent(@RequestBody @Valid Event event, BindingResult bindingResult, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating EventDto " + event.getTitle());

        validateResource(bindingResult);

        eventService.createEvent(event);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/events/{id}").buildAndExpand(event.getId()).toUri());

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }
}