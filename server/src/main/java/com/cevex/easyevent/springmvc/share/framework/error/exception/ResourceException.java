package com.cevex.easyevent.springmvc.share.framework.error.exception;

/**
 * Notify that asked resource wasn't found.
 */
public abstract class ResourceException extends RuntimeException {

    protected String message;

    ResourceException() {
    }

    ResourceException(String message) {
        super(message);
        this.message = message;
    }


    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
