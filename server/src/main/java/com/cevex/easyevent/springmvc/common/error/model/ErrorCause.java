package com.cevex.easyevent.springmvc.common.error.model;

public class ErrorCause {
    private String message;

    public ErrorCause() {
    }

    public ErrorCause(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}