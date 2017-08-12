package com.cevex.easyevent.springmvc.common.error.model;

public class ErrorMessage {

    private ErrorCause cause;

    public ErrorMessage(ErrorCause cause) {
        this.cause = cause;
    }

    public ErrorCause getCause() {
        return cause;
    }

    public void setCause(ErrorCause cause) {
        this.cause = cause;
    }
}