package com.cevex.easyevent.springmvc.error.mapper;

import com.cevex.easyevent.springmvc.error.model.ValidationErrorCause;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;

@Service
public class ValidationErrorMapper {


    public List<ValidationErrorCause> mapValidationErrorList(BindingResult bindingResult) {
        List<ValidationErrorCause> errorCauseList = new ArrayList<>();

        for(FieldError fieldError : bindingResult.getFieldErrors()) {
            errorCauseList.add(mapValidationError(fieldError));
        }

        return errorCauseList;
    }

    private ValidationErrorCause mapValidationError(FieldError fieldError) {
        ValidationErrorCause errorCause = new ValidationErrorCause();

        errorCause.setCode(0);
        errorCause.setField(fieldError.getField());
        errorCause.setResource(fieldError.getObjectName());
        errorCause.setType(fieldError.getCode());
        errorCause.setMessage(fieldError.getDefaultMessage());

        return errorCause;
    }
}
