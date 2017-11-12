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
@RequestMapping(value = "/events/{event_id}")
public class EventController extends RestControllerValidator {

    @Autowired
    private EventService eventService;


    //=============================================================================================
    //                          Retrieve Single Event
    //=============================================================================================

    @RequestMapping(
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity<Event> getEvent(@PathVariable("event_id") long id) {
        System.out.println("Fetching Event with id " + id);
        Event event = eventService.getEvent(id);
        return new ResponseEntity<>(event, HttpStatus.OK);
    }

    //=============================================================================================
    //                          Update Single Event
    //=============================================================================================

    @RequestMapping(
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity<Event> updateEvent(@PathVariable("event_id") long id, @RequestBody @Valid Event event, BindingResult bindingResult) {
        System.out.println("Updating Event " + id);
        validateResource(bindingResult);
        Event updatedEvent = eventService.updateEvent(id, event);
        return new ResponseEntity<>(updatedEvent, HttpStatus.OK);
    }

    //=============================================================================================
    //                          Delete an Event
    //=============================================================================================

    @RequestMapping(
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity<Void> deleteEvent(@PathVariable("event_id") long id) {
        System.out.println("Fetching & Deleting Event with id " + id);
        eventService.deleteEvent(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}