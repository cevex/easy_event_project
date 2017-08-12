package com.cevex.easyevent.springmvc.app.controller;

import com.cevex.easyevent.springmvc.share.rest.RestControllerValidator;
import com.cevex.easyevent.springmvc.app.model.Event;
import com.cevex.easyevent.springmvc.app.service.EventService;
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
public class EventController extends RestControllerValidator {

    @Autowired
    private EventService eventService;

    //=============================================================================================
    //                          Retrieve All Event
    //=============================================================================================

    @RequestMapping(value = "/events", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Event>> getAllEvents() {
        List<Event> events = eventService.getAllEvents();
        if (events.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    //=============================================================================================
    //                          Retrieve Single Event
    //=============================================================================================

    @RequestMapping(value = "/events/{event_id}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Event> getEvent(@PathVariable("event_id") long id) {
        System.out.println("Fetching Event with id " + id);
        Event event = eventService.getEvent(id);
        return new ResponseEntity<>(event, HttpStatus.OK);
    }

    //=============================================================================================
    //                          Create Single Event
    //=============================================================================================

    @RequestMapping(value = "/events", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Void> createEvent(@RequestBody @Valid Event event, BindingResult bindingResult, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating Event " + event.getTitle());

        validateResource(bindingResult);

        eventService.createEvent(event);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/events/{id}").buildAndExpand(event.getId()).toUri());

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    //=============================================================================================
    //                          Update Single Event
    //=============================================================================================

    @RequestMapping(value = "/events/{event_id}", method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Event> updateEvent(@PathVariable("event_id") long id, @RequestBody @Valid Event event, BindingResult bindingResult) {
        System.out.println("Updating Event " + id);
        validateResource(bindingResult);
        Event updatedEvent = eventService.updateEvent(id, event);
        return new ResponseEntity<>(updatedEvent, HttpStatus.OK);
    }

    //=============================================================================================
    //                          Delete an Event
    //=============================================================================================

    @RequestMapping(value = "/events/{event_id}", method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Void> deleteEvent(@PathVariable("event_id") long id) {
        System.out.println("Fetching & Deleting Event with id " + id);
        eventService.deleteEvent(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}