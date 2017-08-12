package com.cevex.easyevent.springmvc.common;


import com.cevex.easyevent.springmvc.common.error.exception.WrongParameterException;
import org.springframework.validation.BindingResult;

public class RestControllerValidator {

    protected void validateResource(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new WrongParameterException(bindingResult);
        }
    }
}
