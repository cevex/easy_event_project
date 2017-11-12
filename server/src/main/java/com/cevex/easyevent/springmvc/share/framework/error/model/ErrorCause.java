package com.cevex.easyevent.springmvc.share.framework.error.model;

public class ErrorCause {

    //=========================================================================
    //          Attributes
    //=========================================================================

    private String type;
    private String message;

    //=========================================================================
    //          Constructor
    //=========================================================================

    public ErrorCause() {
    }

    public ErrorCause(String type, String message) {
        this.type = type;
        this.message = message;
    }

    //=========================================================================
    //          Getter/Setter
    //=========================================================================

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}