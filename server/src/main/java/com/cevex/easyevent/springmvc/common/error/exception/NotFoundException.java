package com.cevex.easyevent.springmvc.common.error.exception;

/**
 * Created by cedric on 16/05/17.
 */
public class NotFoundException extends RuntimeException {

    private String message;

    public NotFoundException(String message) {
        super(message);
        this.message = message;
    }
}
