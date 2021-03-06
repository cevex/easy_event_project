package com.cevex.easyevent.springmvc.app.controller;

import com.cevex.easyevent.springmvc.app.model.User;
import com.cevex.easyevent.springmvc.share.framework.RestControllerValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "")
public class UserController extends RestControllerValidator {

    //=============================================================================================
    //                          Retrieve Single EventDto
    //=============================================================================================

    @RequestMapping(
            value = "/user/{user_id}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity<User> getUser(@PathVariable("user_id") long id) {
        System.out.println("Fetching User with id " + id);
        User user = new User();
        user.setId(id);
        user.setName("Boby");
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


}