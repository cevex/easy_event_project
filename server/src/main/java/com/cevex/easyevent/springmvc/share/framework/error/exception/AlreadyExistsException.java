package com.cevex.easyevent.springmvc.share.framework.error.exception;

/**
 * Created by cedric on 16/05/17.
 */
public class AlreadyExistsException extends RuntimeException {

    private String message;

    public AlreadyExistsException(String message) {
        super(message);
        this.message = message;
    }
}
