package com.cevex.easyevent.springmvc.share.framework.error.exception;

/**
 * Notify that asked resource already exist.
 */
public class AlreadyExistsException extends ResourceException {

    public AlreadyExistsException(String message) {
        super(message);
    }
}
