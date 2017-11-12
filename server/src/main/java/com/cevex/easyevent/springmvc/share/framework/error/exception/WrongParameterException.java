package com.cevex.easyevent.springmvc.share.framework.error.exception;

import org.springframework.validation.BindingResult;

/**
 * Notify that necessary parameter to access resource wasn't valid.
 */
public class WrongParameterException extends ResourceException {

    private BindingResult bindingResult;

    public WrongParameterException(String message) {
        super(message);
    }

    public WrongParameterException(String message, BindingResult bindingResult) {
        super(message);
        this.bindingResult = bindingResult;
    }

    public WrongParameterException(BindingResult bindingResult) {
        this.bindingResult = bindingResult;
    }

    public void setBindingResult(BindingResult bindingResult) {
        this.bindingResult = bindingResult;
    }

    public BindingResult getBindingResult() {
        return bindingResult;
    }
}
