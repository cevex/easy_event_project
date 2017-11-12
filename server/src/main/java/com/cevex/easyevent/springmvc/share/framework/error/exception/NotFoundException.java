package com.cevex.easyevent.springmvc.share.framework.error.exception;

/**
 * Notify that asked resource wasn't found.
 */
public class NotFoundException extends ResourceException {

    public NotFoundException(String message) {
        super(message);
    }

}
