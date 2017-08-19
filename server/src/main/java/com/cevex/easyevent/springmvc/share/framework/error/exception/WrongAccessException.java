package com.cevex.easyevent.springmvc.share.framework.error.exception;

public class WrongAccessException extends RuntimeException {

    private String message;

    public WrongAccessException(String message) {
        super(message);
        this.message = message;
    }
}
