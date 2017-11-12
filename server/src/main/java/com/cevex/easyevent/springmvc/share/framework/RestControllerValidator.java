package com.cevex.easyevent.springmvc.share.framework;


import com.cevex.easyevent.springmvc.share.framework.error.exception.WrongParameterException;
import org.springframework.validation.BindingResult;

public class RestControllerValidator {

    protected void validateResource(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new WrongParameterException(bindingResult);
        }
    }
}
