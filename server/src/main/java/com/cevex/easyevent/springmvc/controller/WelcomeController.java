package com.cevex.easyevent.springmvc.controller;

import com.cevex.easyevent.springmvc.model.Message;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    @RequestMapping("/")
    public String welcome() {//Welcome page, non-rest
        return "Welcome to the Easy Event Project.";
    }

    @RequestMapping("/welcome/{buddy}")
    public Message message(@PathVariable String buddy) {//REST Endpoint.
        return new Message(buddy, "Hello " + buddy);
    }
}