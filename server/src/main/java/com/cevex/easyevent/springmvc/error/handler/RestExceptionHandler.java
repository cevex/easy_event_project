package com.cevex.easyevent.springmvc.error.handler;

import com.cevex.easyevent.springmvc.error.exception.AlreadyExistsException;
import com.cevex.easyevent.springmvc.error.exception.NotFoundException;
import com.cevex.easyevent.springmvc.model.Message;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {NotFoundException.class})
    protected ResponseEntity<Message> handleNotFound(RuntimeException ex, WebRequest request) {
        Message message = new Message("cause","Not found");
        return new ResponseEntity<>(message,  HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {AlreadyExistsException.class})
    protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
        Message message = new Message("cause","Already exist");
        return new ResponseEntity<>(message,  HttpStatus.NOT_FOUND);
    }
}
