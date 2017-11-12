package com.cevex.easyevent.springmvc.share.framework.error.exception;

/**
 * Notify that asked resource wasn't found.
 */
public class EmptyException extends ResourceException {

    public EmptyException(String message) {
        super(message);
    }
}
