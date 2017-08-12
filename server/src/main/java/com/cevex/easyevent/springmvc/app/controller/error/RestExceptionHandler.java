package com.cevex.easyevent.springmvc.app.controller.error;

import com.cevex.easyevent.springmvc.share.rest.error.exception.AlreadyExistsException;
import com.cevex.easyevent.springmvc.share.rest.error.exception.NotFoundException;
import com.cevex.easyevent.springmvc.share.rest.error.exception.WrongParameterException;
import com.cevex.easyevent.springmvc.share.rest.error.model.ErrorCause;
import com.cevex.easyevent.springmvc.share.rest.error.model.ErrorMessage;
import com.cevex.easyevent.springmvc.share.rest.error.model.FieldValidationErrorCause;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private ValidationErrorMapper validationErrorMapper;

    //=========================================================================
    //          Wrong Parameter
    //=========================================================================

    @ExceptionHandler(value = {WrongParameterException.class})
    protected ResponseEntity<List<FieldValidationErrorCause>> handleWrongParameter(RuntimeException ex, WebRequest request) {
        BindingResult validationResult = ((WrongParameterException) ex).getBindingResult();

        return new ResponseEntity<>(validationErrorMapper.mapValidationErrorList(validationResult),
                HttpStatus.BAD_REQUEST);
    }

    //=========================================================================
    //         Ressource Not Found
    //=========================================================================

    @ExceptionHandler(value = {NotFoundException.class})
    protected ResponseEntity<ErrorMessage> handleNotFound(RuntimeException ex, WebRequest request) {
        ErrorMessage errorMessage = new ErrorMessage(new ErrorCause("Not found", ex.getMessage()));
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    //=========================================================================
    //         Ressource Already Exist
    //=========================================================================

    @ExceptionHandler(value = {AlreadyExistsException.class})
    protected ResponseEntity<ErrorMessage> handleConflict(RuntimeException ex, WebRequest request) {
        ErrorMessage errorMessage = new ErrorMessage(new ErrorCause("Already exist", ex.getMessage()));
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }
}
