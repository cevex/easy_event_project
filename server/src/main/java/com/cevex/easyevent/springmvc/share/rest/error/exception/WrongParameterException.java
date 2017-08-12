package com.cevex.easyevent.springmvc.share.rest.error.exception;

import org.springframework.validation.BindingResult;

public class WrongParameterException extends RuntimeException {

    private String message;

    private BindingResult bindingResult;

    public WrongParameterException(String message) {
        super(message);
        this.message = message;
    }

    public WrongParameterException(String message, BindingResult bindingResult) {
        this.message = message;
        this.bindingResult = bindingResult;
    }

    public WrongParameterException(BindingResult bindingResult) {
        this.bindingResult = bindingResult;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setBindingResult(BindingResult bindingResult) {
        this.bindingResult = bindingResult;
    }

    public BindingResult getBindingResult() {
        return bindingResult;
    }
}
