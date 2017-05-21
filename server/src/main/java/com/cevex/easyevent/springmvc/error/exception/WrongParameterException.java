package com.cevex.easyevent.springmvc.error.exception;

import com.cevex.easyevent.springmvc.error.model.ValidationErrorCause;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

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
