package com.cevex.easyevent.springmvc.share.framework.error.model;

public class ErrorMessage {

    //=========================================================================
    //          Attributes
    //=========================================================================

    private ErrorCause cause;

    //=========================================================================
    //          Constructor
    //=========================================================================

    public ErrorMessage(ErrorCause cause) {
        this.cause = cause;
    }

    //=========================================================================
    //          Getter/Setter
    //=========================================================================

    public ErrorCause getCause() {
        return cause;
    }

    public void setCause(ErrorCause cause) {
        this.cause = cause;
    }
}