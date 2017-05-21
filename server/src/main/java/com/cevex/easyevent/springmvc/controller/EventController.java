package com.cevex.easyevent.springmvc.controller;

import com.cevex.easyevent.springmvc.dao.entity.EventEntity;
import com.cevex.easyevent.springmvc.mapper.EventMapper;
import com.cevex.easyevent.springmvc.model.Event;
import com.cevex.easyevent.springmvc.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping(value = "")
public class EventController {

    @Autowired
    private EventService eventService;

    @Autowired
    private EventMapper eventMapper;

    //=============================================================================================
    //                          Retrieve All Event
    //=============================================================================================

    @RequestMapping(value = "/events", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Event>> getAllEvents() {
        List<EventEntity> events = eventService.getAllEvents();
        if (events.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(eventMapper.mapEventList(events), HttpStatus.OK);
    }

    //=============================================================================================
    //                          Retrieve Single Event
    //=============================================================================================

    @RequestMapping(value = "/events/{event_id}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Event> getEvent(@PathVariable("event_id") long id) {
        System.out.println("Fetching Event with id " + id);
        EventEntity event = eventService.getEvent(id);
        return new ResponseEntity<>(eventMapper.mapEvent(event), HttpStatus.OK);
    }

    //=============================================================================================
    //                          Create Single Event
    //=============================================================================================

    @RequestMapping(value = "/events", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Void> createEvent(@RequestBody Event event, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating Event " + event.getTitle());

        eventService.createEvent(eventMapper.mapEvent(event));

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/event/{event_id}").buildAndExpand(event.getId()).toUri());

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    //=============================================================================================
    //                          Update Single Event
    //=============================================================================================

    @RequestMapping(value = "/events/{event_id}", method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Event> updateEvent(@PathVariable("event_id") long id, @RequestBody Event event) {
        System.out.println("Updating Event " + id);
        eventService.updateEvent(eventMapper.mapEvent(event));
        return new ResponseEntity<>(event, HttpStatus.OK);
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