package com.cevex.easyevent.springmvc.app.controller;

import com.cevex.easyevent.springmvc.app.model.Message;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    private final String WELCOME = "Welcome to the Easy Event Project.";

    @RequestMapping("/")
    public String welcome() {
        return WELCOME;
    }

    @RequestMapping(
            value = "/welcome/{buddy}",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public Message message(@PathVariable String buddy) {
        return new Message(buddy, "Hello " + buddy + ". " + WELCOME);
    }
}