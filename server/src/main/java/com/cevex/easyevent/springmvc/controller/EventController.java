package com.cevex.easyevent.springmvc.controller;

import com.cevex.easyevent.springmvc.dao.entity.EventEntity;
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

    //-------------------Retrieve All EventEntity--------------------------------------------------------

    @RequestMapping(value = "/events", method = RequestMethod.GET)
    public ResponseEntity<List<EventEntity>> getAllEvents() {
        List<EventEntity> events = eventService.getAllEvents();
        if (events.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    //-------------------Create an EventEntity--------------------------------------------------------

    @RequestMapping(value = "/events", method = RequestMethod.POST)
    public ResponseEntity<Void> createEvent(@RequestBody EventEntity event, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating EventEntity " + event.getTitle());

        eventService.createEvent(event);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/event/{event_id}").buildAndExpand(event.getId()).toUri());

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    //-------------------Retrieve Single EventEntity--------------------------------------------------------

    @RequestMapping(value = "/events/{event_id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EventEntity> getEvent(@PathVariable("event_id") long id) {
        System.out.println("Fetching EventEntity with id " + id);
        EventEntity event = eventService.getEvent(id);
        return new ResponseEntity<>(event, HttpStatus.OK);
    }


    //------------------- Update an EventEntity --------------------------------------------------------

    @RequestMapping(value = "/events/{event_id}", method = RequestMethod.PUT)
    public ResponseEntity<EventEntity> updateEvent(@PathVariable("event_id") long id, @RequestBody EventEntity event) {
        System.out.println("Updating EventEntity " + id);
        eventService.updateEvent(event);
        return new ResponseEntity<>(event, HttpStatus.OK);
    }

    //------------------- Delete a EventEntity --------------------------------------------------------

    @RequestMapping(value = "/events/{event_id}", method = RequestMethod.DELETE)
    public ResponseEntity<EventEntity> deleteEvent(@PathVariable("event_id") long id) {
        System.out.println("Fetching & Deleting EventEntity with id " + id);
        eventService.deleteEvent(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}