package com.cevex.easyevent.springmvc.app.controller.error;

import com.cevex.easyevent.springmvc.share.rest.error.model.FieldValidationErrorCause;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;

@Service
public class ValidationErrorMapper {

    public List<FieldValidationErrorCause> mapValidationErrorList(BindingResult bindingResult) {
        List<FieldValidationErrorCause> errorCauseList = new ArrayList<>();

        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            errorCauseList.add(mapValidationError(fieldError));
        }

        return errorCauseList;
    }

    private FieldValidationErrorCause mapValidationError(FieldError fieldError) {
        FieldValidationErrorCause errorCause = new FieldValidationErrorCause();

        errorCause.setCode(0);
        errorCause.setField(fieldError.getField());
        errorCause.setResource(fieldError.getObjectName());
        errorCause.setType(fieldError.getCode());
        errorCause.setMessage(fieldError.getDefaultMessage());

        return errorCause;
    }
}
